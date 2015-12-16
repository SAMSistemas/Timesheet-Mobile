package com.samsistemas.timesheet.network.converter;

import com.samsistemas.timesheet.entity.PersonEntity;
import com.samsistemas.timesheet.network.converter.base.JSONConverter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.samsistemas.timesheet.util.JSONObjectKeys.ID;
import static com.samsistemas.timesheet.util.JSONObjectKeys.LAST_NAME;
import static com.samsistemas.timesheet.util.JSONObjectKeys.NAME;
import static com.samsistemas.timesheet.util.JSONObjectKeys.WORK_POSITION;

/**
 * @author jonatan.salas
 */
public class PersonConverter implements JSONConverter<PersonEntity> {
    private static PersonConverter instance = null;

    /**
     * Private constructor
     */
    private PersonConverter() { }

    @Override
    public PersonEntity asObject(JSONObject object) throws JSONException {
        final JSONObject jsonWorkPosition = object.getJSONObject(WORK_POSITION);
        PersonEntity entity = new PersonEntity();

        entity.setId(object.getLong(ID));
        entity.setName(object.getString(NAME))
              .setLastName(object.getString(LAST_NAME))
              .setWorkPositionId(jsonWorkPosition.getLong(ID))
              .setPicture(null)
              .setEnabled(true);

        return entity;
    }

    @Override
    public List<PersonEntity> asList(JSONArray array) throws JSONException {
        List<PersonEntity> personEntities = new ArrayList<>(array.length());

        for (int i = 0; i < array.length(); i++) {
            JSONObject jsonObject = array.getJSONObject(i);
            personEntities.add(asObject(jsonObject));
        }

        return personEntities;
    }

    /**
     * Method that gets a singleton instance.
     *
     * @return a singleton object.
     */
    public static PersonConverter newInstance() {
        if (null == instance) {
            instance = new PersonConverter();
        }
        return instance;
    }
}
