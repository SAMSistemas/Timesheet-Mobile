package com.samsistemas.timesheet.network.converter;

import android.content.Context;
import android.support.annotation.NonNull;

import com.samsistemas.timesheet.entity.WorkPositionEntity;
import com.samsistemas.timesheet.network.converter.base.JsonConverter;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author jonatan.salas
 */
public class WorkPositionConverter implements JsonConverter<WorkPositionEntity, JSONObject> {
    protected static WorkPositionConverter instance = null;

    protected WorkPositionConverter() {}

    @Override
    public WorkPositionEntity convert(@NonNull Context context, @NonNull JSONObject jsonObject) throws JSONException {
        return null;
    }

    public static WorkPositionConverter newInstance() {
        if(null == instance)
            instance = new WorkPositionConverter();
        return instance;
    }
}
