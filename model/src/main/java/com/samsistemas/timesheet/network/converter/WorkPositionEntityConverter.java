package com.samsistemas.timesheet.network.converter;

import android.support.annotation.NonNull;

import com.samsistemas.timesheet.entity.WorkPositionEntity;
import com.samsistemas.timesheet.network.converter.base.JSONConverter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.samsistemas.timesheet.util.JSONObjectKeys.DESCRIPTION;
import static com.samsistemas.timesheet.util.JSONObjectKeys.ID;
import static com.samsistemas.timesheet.util.JSONObjectKeys.WORK_POSITION;

/**
 * @author jonatan.salas
 */
public final class WorkPositionEntityConverter implements JSONConverter<WorkPositionEntity> {
    private static WorkPositionEntityConverter instance = null;

    /**
     * Private constructor
     */
    private WorkPositionEntityConverter() { }

    @Override
    public WorkPositionEntity asObject(@NonNull JSONObject object) throws JSONException {
        final JSONObject jsonWorkPosition = object.getJSONObject(WORK_POSITION);
        WorkPositionEntity entity = new WorkPositionEntity();

        entity.setId(jsonWorkPosition.getLong(ID));
        entity.setDescription(jsonWorkPosition.getString(DESCRIPTION));

        return entity;
    }

    @Override
    public List<WorkPositionEntity> asList(@NonNull JSONArray array) throws JSONException {
        List<WorkPositionEntity> workPositionEntities = new ArrayList<>(array.length());

        for (int i = 0; i < array.length(); i++) {
            JSONObject json = array.getJSONObject(i);
            workPositionEntities.add(asObject(json));
        }

        return workPositionEntities;
    }

    /**
     * Method that gets a singleton instance.
     *
     * @return a singleton object.
     */
    public static WorkPositionEntityConverter newInstance() {
        if (null == instance) {
            instance = new WorkPositionEntityConverter();
        }
        return instance;
    }
}
