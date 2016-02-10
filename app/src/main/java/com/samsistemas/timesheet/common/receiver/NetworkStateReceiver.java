package com.samsistemas.timesheet.common.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;

import com.samsistemas.timesheet.common.utility.NetworkUtil;

/**
 * @author jonatan.salas
 */
public class NetworkStateReceiver extends BroadcastReceiver {
    public static final String CONNECTIVITY_CHANGE_ACTION = "android.net.conn.CONNECTIVITY_CHANGE";
    private final OnNetworkStateReceived mNetworkStateReceived;

    public NetworkStateReceiver(@NonNull OnNetworkStateReceived networkStateReceived) {
        this.mNetworkStateReceived = networkStateReceived;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        final boolean isConnected = NetworkUtil.isConnected(context);
        mNetworkStateReceived.checkNetworkState(context, isConnected);
    }

    /**
     *
     *
     * @author jonatan.salas
     */
    public interface OnNetworkStateReceived {

        /**
         *
         * @param context
         * @param isConnected
         */
        @UiThread
        void checkNetworkState(Context context, Boolean isConnected);
    }
}
