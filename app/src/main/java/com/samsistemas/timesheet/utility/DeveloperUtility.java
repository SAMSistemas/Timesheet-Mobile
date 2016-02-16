package com.samsistemas.timesheet.utility;

import android.os.StrictMode;

/**
 * Class used to detect some performance problems, and exception that application avoid.
 *
 * @author jonatan.salas
 */
public final class DeveloperUtility {

    private DeveloperUtility() { }

    public static void enableStrictModeApi(boolean enabled) {
        if (enabled) {
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
}
