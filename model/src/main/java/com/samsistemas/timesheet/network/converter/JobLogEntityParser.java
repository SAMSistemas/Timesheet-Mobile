package com.samsistemas.timesheet.network.converter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.samsistemas.timesheet.data.R;
import com.samsistemas.timesheet.entity.JobLogEntity;
import com.samsistemas.timesheet.network.converter.base.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author jonatan.salas
 */
public class JobLogEntityParser implements JsonParser<JobLogEntity, JSONObject> {
    protected static final String TAG = JobLogEntityParser.class.getSimpleName();
    protected static final String DATE_TEMPLATE = "dd-MM-yyyy";

    private static JobLogEntityParser instance = null;

    protected JobLogEntityParser() {}

    @Override
    public JobLogEntity convert(@NonNull Context context, @NonNull JSONObject json) throws JSONException {
        JSONObject jsonPerson = json.getJSONObject(context.getString(R.string.person));
        JSONObject jsonProject = json.getJSONObject(context.getString(R.string.project));
        JSONObject jsonTaskType = json.getJSONObject(context.getString(R.string.task_type));

        String dateString = json.getString(context.getString(R.string.date));
        Date date = new Date();

        try {
            date = new SimpleDateFormat(DATE_TEMPLATE, Locale.getDefault()).parse(dateString);
        } catch (ParseException ex) {
            Log.e(TAG, ex.getMessage(), ex.getCause());
        }

        JobLogEntity jobLogEntity = new JobLogEntity();
        jobLogEntity.setJobLogId(json.getLong(context.getString(R.string.id)))
                .setPersonId(jsonPerson.getLong(context.getString(R.string.id)))
                .setProjectId(jsonProject.getLong(context.getString(R.string.id)))
                .setTaskTypeId(jsonTaskType.getLong(context.getString(R.string.id)))
                .setHours(json.getString(context.getString(R.string.hours)))
                .setObservations(json.getString(context.getString(R.string.observation)))
                .setSolicitude(json.getInt(context.getString(R.string.solicitude)))
                .setWorkDate(date);

        return jobLogEntity;
    }

    public static JobLogEntityParser newInstance() {
        if(null == instance)
            instance = new JobLogEntityParser();
        return instance;
    }
}
