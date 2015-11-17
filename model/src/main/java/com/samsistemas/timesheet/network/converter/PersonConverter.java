package com.samsistemas.timesheet.network.converter;

import android.content.Context;
import android.support.annotation.NonNull;

import com.samsistemas.timesheet.entity.PersonEntity;
import com.samsistemas.timesheet.network.converter.base.JsonConverter;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author jonatan.salas
 */
public class PersonConverter implements JsonConverter<PersonEntity, JSONObject> {
    protected static PersonConverter instance = null;

    protected PersonConverter() {}

    @Override
    public PersonEntity convert(@NonNull Context context, @NonNull JSONObject jsonObject) throws JSONException {
        return null;
    }

    public static PersonConverter newInstance() {
        if(null == instance)
            instance = new PersonConverter();
        return instance;
    }
}
