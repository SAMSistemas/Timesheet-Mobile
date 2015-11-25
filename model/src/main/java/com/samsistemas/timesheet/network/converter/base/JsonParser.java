package com.samsistemas.timesheet.network.converter.base;

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
    T convert(@NonNull U json) throws JSONException;
}
