package com.samsistemas.timesheet.commons.util;

import android.os.StrictMode;

/**
 * Class used to detect some performance problems, and exception that application avoid.
 *
 * @author jonatan.salas
 */
public final class DevUtil {

    private DevUtil() { }

    /**
     * Method used to check different troubles.
     */
    public static void enableStrictModeChecker() {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                    .detectDiskReads()
                    .detectDiskWrites()
                    .detectAll()   // or .detectAll() for all detectable problems
                    .penaltyLog()
                    .build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                    .detectLeakedSqlLiteObjects()
                    .penaltyLog()
                    .penaltyDeath()
                    .build());
    }
}
