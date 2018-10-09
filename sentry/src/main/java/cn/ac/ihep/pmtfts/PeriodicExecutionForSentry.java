package cn.ac.ihep.pmtfts;

import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.inject.Inject;

import gov.lbl.nest.sentry.Sentry;

/**
 * This case executes the sentry activities periodically.
 * 
 * @author patton
 */
@Stateless
public class PeriodicExecutionForSentry {

    // public static final member data

    // protected static final member data

    // static final member data

    // private static final member data

    // private static member data

    // private instance member data

    /**
     * The {@link Sentry} instances used by this object.
     */
    @Inject
    private Sentry sentry;

    // constructors

    // instance member method (alphabetic)

    /**
     * Watches for rapid tasks.
     */
    @Schedule(second = "*/10",
              minute = "*",
              hour = "*",
              persistent = false)
    public void watchEveryFewSeconds() {
        sentry.executeTask("rapid");
    }

    /**
     * Watches for frequent tasks.
     */
    @Schedule(hour = "*",
              minute = "*/1",
              persistent = false)
    public void watchEveryMinute() {
        sentry.executeTask("frequent");
    }

    /**
     * Watches for nightly tasks (at 2am).
     */
    // @Schedule(dayOfWeek = "*", hour = "2", persistent = false)
    public void watchEveryNight() {
        sentry.executeTask("nightly");
    }

    // static member methods (alphabetic)

    // Description of this object.
    // @Override
    // public String toString() {}

    // public static void main(String args[]) {}
}
