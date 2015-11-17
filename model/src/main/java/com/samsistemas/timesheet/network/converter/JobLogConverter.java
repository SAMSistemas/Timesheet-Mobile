package com.samsistemas.timesheet.network.converter;

import android.content.Context;
import android.support.annotation.NonNull;

import com.samsistemas.timesheet.entity.JobLogEntity;
import com.samsistemas.timesheet.network.converter.base.JsonConverter;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;

/**
 * @author jonatan.salas
 */
public class JobLogConverter implements JsonConverter<List<JobLogEntity>, JSONArray> {
    protected static JobLogConverter instance = null;

    protected JobLogConverter() {}

    @Override
    public List<JobLogEntity> convert(@NonNull Context context, @NonNull JSONArray jsonArray) throws JSONException {
        return null;
    }

    public static JobLogConverter newInstance() {
        if(null == instance)
            instance = new JobLogConverter();
        return instance;
    }
}
