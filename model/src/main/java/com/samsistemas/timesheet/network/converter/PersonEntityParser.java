package com.samsistemas.timesheet.network.converter;

import android.content.Context;
import android.support.annotation.NonNull;

import com.samsistemas.timesheet.data.R;
import com.samsistemas.timesheet.entity.PersonEntity;
import com.samsistemas.timesheet.network.converter.base.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author jonatan.salas
 */
public class PersonEntityParser implements JsonParser<PersonEntity, JSONObject> {
    protected static PersonEntityParser instance = null;

    protected PersonEntityParser() {}

    @Override
    public PersonEntity convert(@NonNull Context context, @NonNull JSONObject jsonObject) throws JSONException {
        final PersonEntity personEntity = new PersonEntity();
        final JSONObject jsonWorkPosition = jsonObject.getJSONObject(context.getString(R.string.work_position));

        personEntity.setPersonId(jsonObject.getLong(context.getString(R.string.id)))
                    .setName(jsonObject.getString(context.getString(R.string.name)))
                    .setLastName(jsonObject.getString(context.getString(R.string.last_name)))
                    .setWorkPositionId(jsonWorkPosition.getLong(context.getString(R.string.id)))
                    .setPicture(null)
                    .setEnabled(true);

        return personEntity;
    }

    public static PersonEntityParser newInstance() {
        if(null == instance)
            instance = new PersonEntityParser();
        return instance;
    }
}
