package cn.ac.ihep.pmtfts;

import gov.lbl.nest.jee.watching.Watcher;
import gov.lbl.nest.sentry.Configuration;
import gov.lbl.nest.sentry.Context;
import gov.lbl.nest.sentry.MailSession;
import gov.lbl.nest.sentry.SentryDB;
import gov.lbl.nest.sentry.actions.WaitingItemsManager;

import java.io.File;
import java.util.Properties;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.mail.Session;
import javax.persistence.EntityManager;

/**
 * This class supplied the execution context for Actions in the dyb-sentry
 * deployment.
 * 
 * @author patton
 */
@Stateless
public class ContextImpl implements
                        Context {

    // public static final member data

    /**
     * The name of the mount points {@link Properties} instance.
     */
    public static final String MOUNT_POINTS = "mountPoints";

    // protected static final member data

    // static final member data

    // private static final member data

    // private static member data

    // private instance member data

    /**
     * The {@link Configuration} instance used with the application.
     */
    @Inject
    private Configuration configuration;

    /**
     * The {@link EntityManager} used by the application.
     */
    @Inject
    @SentryDB
    private EntityManager entityManager;

    /**
     * The {@link Sesson} used by the application.
     */
    @Inject
    @MailSession
    private Session session;

    /**
     * The {@link WaitingItemsManager} instance used by the application.
     */
    @Inject
    private WaitingItemsManager waitingFilesManager;

    /**
     * The {@link Watcher} used by the application.
     */
    @Inject
    private Watcher watcher;

    // constructors

    // instance member method (alphabetic)

    @Override
    public File getConfigDir() {
        return configuration.getDirectory();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getInstance(Class<T> clazz) {
        if (clazz.equals(EntityManager.class)) {
            return (T) entityManager;
        } else if (clazz.equals(Session.class)) {
            return (T) session;
        } else if (clazz.equals(Watcher.class)) {
            return (T) watcher;
        } else if (clazz.equals(WaitingItemsManager.class)) {
            return (T) waitingFilesManager;
        }
        return null;
    }

    @Override
    public <T> T getInstance(String name,
                             Class<T> clazz) {
        return null;
    }
    // static member methods (alphabetic)

    // Description of this object.
    // @Override
    // public String toString() {}

    // public static void main(String args[]) {}
}
