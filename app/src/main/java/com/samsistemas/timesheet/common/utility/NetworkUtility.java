package com.samsistemas.timesheet.common.utility;

import android.content.Context;
import android.net.ConnectivityManager;
import android.support.annotation.NonNull;

/**
 * Utility class that provides methods to work with Networks.
 *
 * @author jonatan.salas
 */
public final class NetworkUtility {

    private NetworkUtility() { }

    /***
     * Method that verifies if active networks are connected.
     *
     * @param context - the context used to get the system service.
     * @return true if is connected or connecting. False in the other hand.
     */
    public static synchronized boolean isConnected(@NonNull final Context context) {
        final String service = Context.CONNECTIVITY_SERVICE;
        final ConnectivityManager manager = (ConnectivityManager) context.getSystemService(service);

        return isMobileActive(manager) || isWirelessActive(manager);
    }

    /***
     *
     * @param manager
     * @return
     */
    @SuppressWarnings("deprecation")
    private static boolean isMobileActive(@NonNull final ConnectivityManager manager) {
        return manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnectedOrConnecting();
    }

    /***
     *
     * @param manager
     * @return
     */
    @SuppressWarnings("deprecation")
    private static boolean isWirelessActive(@NonNull final ConnectivityManager manager) {
        return manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnectedOrConnecting();
    }
}
