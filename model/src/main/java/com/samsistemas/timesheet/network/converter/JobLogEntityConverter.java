package com.samsistemas.timesheet.network.converter;

import android.support.annotation.NonNull;
import android.util.Log;

import com.samsistemas.timesheet.entity.JobLogEntity;
import com.samsistemas.timesheet.network.converter.base.JSONConverter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.samsistemas.timesheet.util.JSONObjectKeys.DATE;
import static com.samsistemas.timesheet.util.JSONObjectKeys.HOURS;
import static com.samsistemas.timesheet.util.JSONObjectKeys.ID;
import static com.samsistemas.timesheet.util.JSONObjectKeys.OBSERVATION;
import static com.samsistemas.timesheet.util.JSONObjectKeys.PERSON;
import static com.samsistemas.timesheet.util.JSONObjectKeys.PROJECT;
import static com.samsistemas.timesheet.util.JSONObjectKeys.SOLICITUDE;
import static com.samsistemas.timesheet.util.JSONObjectKeys.TASK_TYPE;

/**
 * @author jonatan.salas
 */
public final class JobLogEntityConverter implements JSONConverter<JobLogEntity> {
    private static final String LOG_TAG = JobLogEntityConverter.class.getSimpleName();
    private static final String DATE_TEMPLATE = "dd-MM-yyyy";
    private static JobLogEntityConverter instance = null;

    /**
     * Private constructor
     */
    private JobLogEntityConverter() { }

    @Override
    public JobLogEntity asObject(@NonNull JSONObject object) throws JSONException {
        JSONObject jsonPerson = object.getJSONObject(PERSON);
        JSONObject jsonProject = object.getJSONObject(PROJECT);
        JSONObject jsonTaskType = object.getJSONObject(TASK_TYPE);

        String dateString = object.getString(DATE);
        Date date = new Date();

        try {
            date = new SimpleDateFormat(DATE_TEMPLATE, Locale.getDefault()).parse(dateString);
        } catch (ParseException ex) {
            Log.e(LOG_TAG, ex.getMessage(), ex.getCause());
        }

        JobLogEntity entity = new JobLogEntity();

        entity.setId(object.getLong(ID));
        entity.setPersonId(jsonPerson.getLong(ID))
              .setProjectId(jsonProject.getLong(ID))
              .setTaskTypeId(jsonTaskType.getLong(ID))
              .setHours(object.getString(HOURS))
              .setObservations(object.getString(OBSERVATION))
              .setSolicitude(object.getInt(SOLICITUDE))
              .setWorkDate(date);

        return entity;
    }

    @Override
    public List<JobLogEntity> asList(@NonNull JSONArray array) throws JSONException {
        final List<JobLogEntity> jobLogEntities = new ArrayList<>(array.length());

        for (int i = 0; i < array.length(); i++) {
            JSONObject jsonObject = array.getJSONObject(i);
            jobLogEntities.add(asObject(jsonObject));
        }

        return jobLogEntities;
    }

    /**
     * Method that gets a singleton instance.
     *
     * @return a singleton object.
     */
    public static JobLogEntityConverter newInstance() {
        if (null == instance) {
            instance = new JobLogEntityConverter();
        }
        return instance;
    }
}
