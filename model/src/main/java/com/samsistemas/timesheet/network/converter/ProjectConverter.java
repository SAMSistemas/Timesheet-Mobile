package com.samsistemas.timesheet.network.converter;

import android.content.Context;
import android.support.annotation.NonNull;

import com.samsistemas.timesheet.entity.ProjectEntity;
import com.samsistemas.timesheet.network.converter.base.JsonConverter;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;

/**
 * @author jonatan.salas
 */
public class ProjectConverter implements JsonConverter<List<ProjectEntity>, JSONArray> {
    protected static ProjectConverter instance = null;

    protected ProjectConverter() {}

    @Override
    public List<ProjectEntity> convert(@NonNull Context context, @NonNull JSONArray jsonArray) throws JSONException {
        return null;
    }

    public static ProjectConverter newInstance() {
        if(null == instance)
            instance = new ProjectConverter();
        return instance;
    }
}
