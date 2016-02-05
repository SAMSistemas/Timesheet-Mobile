package com.samsistemas.timesheet.common.utility;

/**
 * @author jonatan.salas
 */
public final class ThreadUtility {
    private static final Object lock = new Object();

    private ThreadUtility() { }

    public static void sleep(long millis) {
        try {
            synchronized (lock) {
                lock.wait(millis);
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
