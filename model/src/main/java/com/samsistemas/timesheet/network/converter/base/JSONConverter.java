package com.samsistemas.timesheet.network.converter.base;

import android.support.annotation.NonNull;

import com.samsistemas.timesheet.entity.base.Entity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Interface used for JSON conversion
 *
 * @author jonatan.salas
 * @param <T> class that extends Entity
 */
public interface JSONConverter<T extends Entity> {

    /**
     * Method that converts a JSONObject as a T class object.
     *
     * @param object JSONObject to convert
     * @return a T object
     * @throws JSONException exception thrown if JSON is malformed or can't read any field
     */
    T asObject(@NonNull JSONObject object) throws JSONException;

    /**
     * Method that converts a JSONArray as a List of T class object.
     *
     * @param array JSONArray to convert
     * @return a List of T object
     * @throws JSONException exception thrown if JSON is malformed or can't read any field
     */
    List<T> asList(@NonNull JSONArray array) throws JSONException;
}
