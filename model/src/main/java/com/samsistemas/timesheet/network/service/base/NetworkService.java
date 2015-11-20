package com.samsistemas.timesheet.network.service.base;

import android.content.Context;
import android.support.annotation.NonNull;

import org.json.JSONException;

/**
 * @author jonatan.salas
 */
public interface NetworkService<T, U> {

    /**
     *
     * @param context
     * @param response
     * @param customArgs
     */
    U parseNetworkResponse(@NonNull Context context, T response, U customArgs) throws JSONException;
}
