package com.samsistemas.timesheet.network.converter.base;

import android.support.annotation.NonNull;

import com.samsistemas.timesheet.entity.base.Entity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 *
 * @author jonatan.salas
 * @param <T>
 */
public interface JSONConverter<T extends Entity> {

    /**
     *
     * @param object
     * @return
     * @throws JSONException
     */
    T asObject(@NonNull JSONObject object) throws JSONException;

    /**
     *
     * @param array
     * @return
     * @throws JSONException
     */
    List<T> asList(@NonNull JSONArray array) throws JSONException;
}
