package cn.ac.ihep.pmtfts.spade.execute;

import gov.lbl.nest.spade.Spade;

import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This case executes activities periodically.
 * 
 * @author patton
 */
@Stateless
public class PeriodicExecutionForSpade {

    // public static final member data

    // protected static final member data

    // static final member data

    // private static final member data

    /**
     * The {@link Logger} used by this class.
     */
    private static final Logger LOG = LoggerFactory.getLogger(PeriodicExecutionForSpade.class);

    /**
     * The delay before automatically reshipping postponed files.
     */
    private static final long POSTPONED_DELAY_IN_SECS = 3L * 60 * 60;

    // private static member data

    // private instance member data

    /**
     * The {@link Spade} instance used by this object.
     */
    @Inject
    private Spade spade;

    // constructors

    // instance member method (alphabetic)

    /**
     * Scans for new file in the local dropboxes.
     */
    @Schedule(hour = "*", minute = "*/1", persistent = false)
    public void localScan() {
        try {
            if (!spade.isFetching()) {
                return;
            }
            if (spade.localScan()) {
                LOG.info("Successfully completed automatic scan for \"" + spade.getName()
                         + "\" for new files in the local dropboxes");
            } else {
                LOG.info("Automatic scan for \"" + spade.getName()
                         + "\" for new files in the local dropboxes failed, it may already be in progress");
            }
        } catch (Exception e) {
            LOG.error("Failed when automatically scanning for \"" + spade.getName()
                      + "\" for new files in the local dropboxes");
            e.printStackTrace();
        }
    }

    /**
     * Scans for new file in the transfer dropboxes.
     */
    @Schedule(hour = "*", minute = "*/5", persistent = false)
    public void inboundScan() {
        try {
            if (!spade.isReceiving()) {
                return;
            }
            if (spade.inboundScan()) {
                LOG.info("Successfully completed automatic scan for \"" + spade.getName()
                         + "\" for new files in the inbound dropboxes");
            } else {
                LOG.info("Automatic scan for \"" + spade.getName()
                         + "\" for new files in the inbound dropboxes failed, it may already be in progress");
            }
        } catch (Exception e) {
            LOG.error("Failed when automatically scanning for \"" + spade.getName()
                      + "\" for new files in the inbound dropboxes");
            e.printStackTrace();
        }
    }

    /**
     * Scans for files in the output spool waiting to be archived.
     */
    @Schedule(hour = "*/4", persistent = false)
    public void outputSpool() {
        try {
            if (spade.isSuspended() || !spade.isArchiving()) {
                return;
            }
            if (spade.outputSpool(null)) {
                LOG.info("Successfully completed automatic scan for \"" + spade.getName()
                         + "\" to archive spooled files");
            } else {
                LOG.info("Automatic scan for \"" + spade.getName()
                         + "\" to archive spooled files failed, it may already be in progress");
            }
        } catch (Exception e) {
            LOG.error("Failed when automatically scanning for \"" + spade.getName()
                      + "\" to archive spooled files");
            e.printStackTrace();
        }
    }

    /**
     * Scans for new email messages.
     */
    @Schedule(hour = "*", minute = "*/15", persistent = false)
    public void readEmail() {
        try {
            if (spade.isSuspended()) {
                return;
            }
            if (spade.readEmail()) {
                LOG.info("Successfully completed automatic scan for \"" + spade.getName()
                         + "\" for new email messages");
            } else {
                LOG.info("Automatic scan for \"" + spade.getName()
                         + "\" for new mail messages failed, it may already be in progress");
            }
        } catch (Exception e) {
            LOG.error("Failed when automatically scanning for \"" + spade.getName()
                      + "\" for new email messages");
            e.printStackTrace();
        }
    }

    /**
     * Scans for retries of postponed shipments.
     */
    // @Schedule(hour = "*", minute = "*/15", persistent = false)
    public void reshipScan() {
        try {
            if (spade.isSuspended()) {
                return;
            }
            if (spade.retryScan(POSTPONED_DELAY_IN_SECS)) {
                LOG.info("Successfully completed automatic scan for \"" + spade.getName()
                         + "\" to reship postponed files");
            } else {
                LOG.info("Automatic scan for \"" + spade.getName()
                         + "\" to reship postponed files failed, it may already be in progress");
            }
        } catch (Exception e) {
            LOG.error("Failed when automatically scanning for \"" + spade.getName()
                      + "\" to reship postponed files");
            e.printStackTrace();
        }
    }

    /**
     * Sends any stored confirmations.
     */
    @Schedule(hour = "*", minute = "*/10", persistent = false)
    public void sendConfirmations() {
        try {
            if (spade.isSuspended()) {
                return;
            }
            if (spade.sendConfirmations()) {
                LOG.info("Successfully completed automatic sending of any pending confirmations for \"" + spade.getName()
                         + "\"");
            } else {
                LOG.info("No pending confirmations for \"" + spade.getName()
                         + "\" have been sent");
            }
        } catch (Exception e) {
            LOG.error("Failed when automatically sending out confirmations for \"" + spade.getName()
                      + "\"");
            e.printStackTrace();
        }
    }

    /**
     * Sends any stored confirmations.
     */
    @Schedule(hour = "*", minute = "*/10", persistent = false)
    public void sendNotifications() {
        try {
            if (spade.sendNotifications()) {
                LOG.info("Successfully completed automatic sending of any pending notification for \"" + spade.getName()
                         + "\"");
            } else {
                LOG.info("No pending notification for \"" + spade.getName()
                         + "\" have been sent");
            }
        } catch (Exception e) {
            LOG.error("Failed when automatically sending out confirmations for \"" + spade.getName()
                      + "\"");
            e.printStackTrace();
        }
    }

    // static member methods (alphabetic)

    // Description of this object.
    // @Override
    // public String toString() {}

    // public static void main(String args[]) {}
}
