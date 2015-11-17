package com.samsistemas.timesheet.network.converter.base;

import android.content.Context;
import android.support.annotation.NonNull;

import org.json.JSONException;

/**
 * @author jonatan.salas
 * @param <T>
 * @param <U>
 */
public interface JsonParser<T, U> {

    /**
     *
     * @param json
     * @return
     * @throws JSONException
     */
    T convert(@NonNull Context context, @NonNull U json) throws JSONException;
}
