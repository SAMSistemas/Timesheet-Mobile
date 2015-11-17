package com.samsistemas.timesheet.network.converter;

import android.content.Context;
import android.support.annotation.NonNull;

import com.samsistemas.timesheet.entity.TaskTypeEntity;
import com.samsistemas.timesheet.network.converter.base.JsonConverter;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;

/**
 * @author jonatan.salas
 */
public class TaskTypeConverter implements JsonConverter<List<TaskTypeEntity>, JSONArray> {
    protected static TaskTypeConverter instance = null;

    protected TaskTypeConverter() {}

    @Override
    public List<TaskTypeEntity> convert(@NonNull Context context, @NonNull JSONArray jsonArray) throws JSONException {
        return null;
    }

    public static TaskTypeConverter newInstance() {
        if(null == instance)
            instance = new TaskTypeConverter();
        return instance;
    }
}
