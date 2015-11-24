package com.samsistemas.timesheet.network.converter;

import android.content.Context;
import android.support.annotation.NonNull;

import com.samsistemas.timesheet.constant.JSONConst;
import com.samsistemas.timesheet.entity.JobLogEntity;
import com.samsistemas.timesheet.network.converter.base.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jonatan.salas
 */
public class JobLogEntityListParser implements JsonParser<List<JobLogEntity>, JSONArray>, JSONConst {
    protected static JobLogEntityListParser instance = null;
    protected JobLogEntityParser parser;

    protected JobLogEntityListParser() {
        this.parser = JobLogEntityParser.newInstance();
    }

    @Override
    public List<JobLogEntity> convert(@NonNull Context context, @NonNull JSONArray jsonArray) throws JSONException {
        final List<JobLogEntity> jobLogEntities = new ArrayList<>(jsonArray.length());

        for(int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonJobLog = jsonArray.getJSONObject(i);
            JobLogEntity jobLogEntity = parser.convert(context, jsonJobLog);
            jobLogEntities.add(i, jobLogEntity);
        }

        return jobLogEntities;
    }

    public static JobLogEntityListParser newInstance() {
        if(null == instance)
            instance = new JobLogEntityListParser();
        return instance;
    }
}
