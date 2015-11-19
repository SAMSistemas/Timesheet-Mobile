package com.samsistemas.timesheet.network.converter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.samsistemas.timesheet.data.R;
import com.samsistemas.timesheet.entity.JobLogEntity;
import com.samsistemas.timesheet.network.converter.base.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * @author jonatan.salas
 */
public class JobLogEntityListParser implements JsonParser<List<JobLogEntity>, JSONArray> {
    protected static final String TAG = JobLogEntityListParser.class.getSimpleName();
    protected static final String DATE_TEMPLATE = "dd-MM-yyyy";
    protected static JobLogEntityListParser instance = null;

    protected JobLogEntityListParser() {}

    @Override
    public List<JobLogEntity> convert(@NonNull Context context, @NonNull JSONArray jsonArray) throws JSONException {
        final List<JobLogEntity> jobLogEntities = new ArrayList<>(jsonArray.length());

        for(int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonJobLog = jsonArray.getJSONObject(i);
            JSONObject jsonPerson = jsonJobLog.getJSONObject(context.getString(R.string.person));
            JSONObject jsonProject = jsonJobLog.getJSONObject(context.getString(R.string.project));
            JSONObject jsonTaskType = jsonJobLog.getJSONObject(context.getString(R.string.task_type));

            String dateString = jsonJobLog.getString(context.getString(R.string.date));
            Date date = new Date();

            try {
                date = new SimpleDateFormat(DATE_TEMPLATE, Locale.getDefault()).parse(dateString);
            } catch (ParseException ex) {
                Log.e(TAG, ex.getMessage(), ex.getCause());
            }

            JobLogEntity jobLogEntity = new JobLogEntity();
            jobLogEntity.setJobLogId(jsonJobLog.getLong(context.getString(R.string.id)))
                        .setPersonId(jsonPerson.getLong(context.getString(R.string.id)))
                        .setProjectId(jsonProject.getLong(context.getString(R.string.id)))
                        .setTaskTypeId(jsonTaskType.getLong(context.getString(R.string.id)))
                        .setHours(jsonJobLog.getString(context.getString(R.string.hours)))
                        .setObservations(jsonJobLog.getString(context.getString(R.string.observation)))
                        .setSolicitude(jsonJobLog.getInt(context.getString(R.string.solicitude)))
                        .setWorkDate(date);

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
