package com.samsistemas.timesheet.network.converter;

import android.support.annotation.NonNull;
import android.util.Log;

import static com.samsistemas.timesheet.constant.JSONConst.*;
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
public final class JobLogEntityParser implements JsonParser<JobLogEntity, JSONObject> {
    private static final String TAG = JobLogEntityParser.class.getSimpleName();
    private static final String DATE_TEMPLATE = "dd-MM-yyyy";

    private static JobLogEntityParser instance = null;

    private JobLogEntityParser() { }

    @Override
    public JobLogEntity convert(@NonNull JSONObject json) throws JSONException {
        JSONObject jsonPerson = json.getJSONObject(PERSON);
        JSONObject jsonProject = json.getJSONObject(PROJECT);
        JSONObject jsonTaskType = json.getJSONObject(TASK_TYPE);

        String dateString = json.getString(DATE);
        Date date = new Date();

        try {
            date = new SimpleDateFormat(DATE_TEMPLATE, Locale.getDefault()).parse(dateString);
        } catch (ParseException ex) {
            Log.e(TAG, ex.getMessage(), ex.getCause());
        }

        JobLogEntity jobLogEntity = new JobLogEntity();
        jobLogEntity.setId(json.getLong(ID));
        jobLogEntity.setPersonId(jsonPerson.getLong(ID))
                    .setProjectId(jsonProject.getLong(ID))
                    .setTaskTypeId(jsonTaskType.getLong(ID))
                    .setHours(json.getString(HOURS))
                    .setObservations(json.getString(OBSERVATION))
                    .setSolicitude(json.getInt(SOLICITUDE))
                    .setWorkDate(date);

        return jobLogEntity;
    }

    public static JobLogEntityParser newInstance() {
        if (null == instance) {
            instance = new JobLogEntityParser();
        }
        return instance;
    }
}
