package cn.ac.ihep.pmtfts;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.ejb.Singleton;
import javax.enterprise.inject.Produces;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.xml.bind.JAXBException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gov.lbl.nest.jee.monitor.InstrumentationDB;
import gov.lbl.nest.jee.watching.WatcherDB;
import gov.lbl.nest.sentry.Configuration;
import gov.lbl.nest.sentry.ImplementationString;
import gov.lbl.nest.sentry.MailSession;
import gov.lbl.nest.sentry.SentryDB;
import gov.lbl.nest.sentry.load.Application;
import gov.lbl.nest.sentry.load.ApplicationLoads;
import gov.lbl.nest.sentry.load.ComponentStatus;

/**
 * This class provides resources to be injected into the application.
 * 
 * @author patton
 */
@Singleton
public class ResourcesForSentry {

    // public static final member data

    // protected static final member data

    // static final member data

    // private static final member data

    /**
     * The {@link Logger} used by this class.
     */
    private static final Logger LOG = LoggerFactory.getLogger(ResourcesForSentry.class);

    /**
     * The name of the property containing the email credentials.
     */
    private static final String MAIL_CREDENTIALS_PROPERTY = "mail.credentials";

    /**
     * The name of the properties file contains the mail settings
     */
    private static final String MAIL_PROPERTIES = "mail.properties";

    /**
     * The name of the property containing the email credentials.
     */
    private static final String MAIL_USER_PROPERTY = "mail.user";

    // private static member data

    // private instance member data

    /**
     * The {@link Configuration} containing the configuration settings for
     * Sentry.
     */
    private Configuration configuration;

    /**
     * Expose an entity manager using the resource producer pattern
     */
    @PersistenceContext(unitName = "sentry")
    private EntityManager entityManager;

    /**
     * The loads within the Sentry system.
     */
    private ApplicationLoads loads;

    /**
     * The {@link Session} to be used by the Sentry system.
     */
    private Session session;

    /**
     * The version String containing this project's release.
     */
    private static String version;

    // constructors

    /**
     * Creates an instance of this class.
     */
    public ResourcesForSentry() {
        getVersion();
    }

    // instance member method (alphabetic)

    /**
     * Returns the configuration settings for Sentry.
     * 
     * @return the configuration settings for Sentry.
     */
    @Produces
    public Configuration getConfiguration() {
        if (null == configuration) {
            try {
                configuration = Configuration.load();
                LOG.info("Read configuration for \"" + "Sentry"
                         + "\" from \""
                         + configuration.getFile()
                         + "\"");
            } catch (JAXBException e1) {
                LOG.error("Could not parse configuration file for \"" + "Sentry"
                          + "\"");
                e1.printStackTrace();
            }

        }
        return configuration;
    }

    /**
     * Returns the {@link EntityManager} instance used by this application.
     * 
     * @return the {@link EntityManager} instance used by this application.
     */
    @Produces
    @InstrumentationDB
    @SentryDB
    @WatcherDB
    public EntityManager getEntityManager() {
        return entityManager;
    }

    /**
     * Returns the implementation version.
     * 
     * @return the implementation version.
     */
    @SuppressWarnings("static-method")
    @Produces
    @ImplementationString
    public String getImplementationString() {
        getVersion();
        return version;
    }

    /**
     * Returns the loads within the Sentry system.
     * 
     * @return the loads within the Sentry system.
     */
    @Produces
    public ApplicationLoads getLoads() {
        if (null == loads) {
            loads = new ApplicationLoads();
            loads.setLocation("acmeinc");
            loads.setTimestamp(new Date());
            final Application application = new Application();
            application.setName("Sentry");
            if (getConfiguration().isSuspended()) {
                application.setState("SUSPENDED");
            } else {
                application.setState("RUNNING");
            }
            loads.setApplication(application);
            final List<ComponentStatus> statuses = new ArrayList<ComponentStatus>(1);
            final ComponentStatus status = new ComponentStatus();
            status.setName("Syphon");
            status.setActive(Boolean.TRUE);
            statuses.add(status);
            loads.setLoads(statuses);
        }
        return loads;
    }

    // static member methods (alphabetic)

    /**
     * Returns the {@link Session} to be used by the Sentry system.
     * 
     * @return the {@link Session} to be used by the Sentry system.
     */
    @Produces
    @MailSession
    public Session getSession() {
        if (null == session) {
            Properties props = new Properties();
            try {
                final String propertiesFile = getConfiguration().getMailProperties();
                final File fileToUse;
                if (null == propertiesFile) {
                    fileToUse = new File(configuration.getDirectory(),
                                         MAIL_PROPERTIES);
                } else {
                    final File mailProperties = new File(propertiesFile);
                    if (mailProperties.isAbsolute()) {
                        fileToUse = mailProperties;
                    } else {
                        fileToUse = new File(configuration.getDirectory(),
                                             mailProperties.getPath());
                    }
                }
                props.load(new FileInputStream(fileToUse));
                final String mailUser = props.getProperty(MAIL_USER_PROPERTY);
                final String mailPassword = props.getProperty(MAIL_CREDENTIALS_PROPERTY);
                session = Session.getInstance(props,
                                              new Authenticator() {
                                                  @Override
                                                  protected PasswordAuthentication getPasswordAuthentication() {
                                                      return new PasswordAuthentication(mailUser,
                                                                                        mailPassword);
                                                  }
                                              });
                LOG.info("Read mail properties for \"Sentry\" from \"" + fileToUse.getPath()
                         + "\"");
            } catch (FileNotFoundException e) {
                session = Session.getInstance(new Properties());
            } catch (IOException e) {
                e.printStackTrace();
            }
            final Set<String> names = props.stringPropertyNames();
            if (names.isEmpty()) {
                LOG.warn("No mail properties were specified for \"Sentry\" so will execute in standalone mode");
            } else {
                LOG.info("The following mail properties were specified for \"Sentry\"");
                for (String name : names) {
                    if (MAIL_CREDENTIALS_PROPERTY.equals(name)) {
                        LOG.info("  " + name
                                 + " = "
                                 + "<provided>");
                    } else {
                        LOG.info("  " + name
                                 + " = "
                                 + props.getProperty(name));
                    }
                }
            }

        }
        return session;
    }

    /**
     * Returns the version String containing this project's release.
     * 
     * @return the version String containing this project's release.
     */
    private static synchronized String getVersion() {
        if (null == version) {
            Properties props = new Properties();
            try {
                final InputStream is = ResourcesForSentry.class.getResourceAsStream("/META-INF/maven/gov.lbl.nest/sentry/pom.properties");
                if (null == is) {
                    LOG.warn("No version file found in WAR, probably not built with Maven");
                    version = "Unknown";
                    return version;
                }
                props.load(is);
                version = props.getProperty("version",
                                            "Unknown");
                LOG.info("Using version " + version
                         + " of artifact \""
                         + props.getProperty("groupId",
                                             "-")
                         + ":"
                         + props.getProperty("artifactId",
                                             "-")
                         + "\"");
            } catch (IOException e) {
                version = "Unknown";
                e.printStackTrace();
            }
        }
        return version;
    }

    // Description of this object.
    // @Override
    // public String toString() {}

    // public static void main(String args[]) {}
}
