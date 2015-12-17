package com.samsistemas.timesheet.network.converter;

import android.support.annotation.NonNull;
import android.util.Log;

import com.samsistemas.timesheet.entity.ProjectEntity;
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

import static com.samsistemas.timesheet.util.JSONObjectKeys.CLIENT;
import static com.samsistemas.timesheet.util.JSONObjectKeys.ENABLED;
import static com.samsistemas.timesheet.util.JSONObjectKeys.ID;
import static com.samsistemas.timesheet.util.JSONObjectKeys.NAME;
import static com.samsistemas.timesheet.util.JSONObjectKeys.SHORT_NAME;
import static com.samsistemas.timesheet.util.JSONObjectKeys.START_DATE;

/**
 * @author jonatan.salas
 */
public final class ProjectEntityConverter implements JSONConverter<ProjectEntity> {
    private static final String LOG_TAG = ProjectEntityConverter.class.getSimpleName();
    private static final String DATE_TEMPLATE = "yyyy-MM-dd";
    private static ProjectEntityConverter instance = null;

    /**
     * Private constructor
     */
    private ProjectEntityConverter() { }

    @Override
    public ProjectEntity asObject(@NonNull JSONObject object) throws JSONException {
        JSONObject jsonClient = object.getJSONObject(CLIENT);

        String dateString = object.getString(START_DATE);
        Date date = new Date();

        try {
            date = new SimpleDateFormat(DATE_TEMPLATE, Locale.getDefault()).parse(dateString);
        } catch (ParseException ex) {
            Log.e(LOG_TAG, ex.getMessage(), ex.getCause());
        }

        ProjectEntity entity = new ProjectEntity();

        entity.setId(object.getLong(ID));
        entity.setClientId(jsonClient.getLong(ID))
                .setName(object.getString(NAME))
                .setShortName(object.getString(SHORT_NAME))
                .setStartDate(date)
                .setEnabled(object.getBoolean(ENABLED));

        return entity;
    }

    @Override
    public List<ProjectEntity> asList(@NonNull JSONArray array) throws JSONException {
        List<ProjectEntity> projectEntities = new ArrayList<>(array.length());

        for (int i = 0; i < array.length(); i++) {
            JSONObject jsonProject = array.getJSONObject(i);
            projectEntities.add(asObject(jsonProject));
        }

        return projectEntities;
    }

    /**
     * Method that gets a singleton instance.
     *
     * @return a singleton object.
     */
    public static ProjectEntityConverter newInstance() {
        if (null == instance) {
            instance = new ProjectEntityConverter();
        }
        return instance;
    }
}
