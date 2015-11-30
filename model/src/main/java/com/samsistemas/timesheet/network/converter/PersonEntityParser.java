package com.samsistemas.timesheet.network.converter;

import android.support.annotation.NonNull;

import com.samsistemas.timesheet.constant.JSONConst;
import com.samsistemas.timesheet.entity.PersonEntity;
import com.samsistemas.timesheet.network.converter.base.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author jonatan.salas
 */
public class PersonEntityParser implements JsonParser<PersonEntity, JSONObject>, JSONConst {
    private static PersonEntityParser instance = null;

    private PersonEntityParser() {}

    @Override
    public PersonEntity convert(@NonNull JSONObject jsonObject) throws JSONException {
        final PersonEntity personEntity = new PersonEntity();
        final JSONObject jsonWorkPosition = jsonObject.getJSONObject(WORK_POSITION);

        personEntity.setId(jsonObject.getLong(ID));
        personEntity.setName(jsonObject.getString(NAME))
                    .setLastName(jsonObject.getString(LAST_NAME))
                    .setWorkPositionId(jsonWorkPosition.getLong(ID))
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
