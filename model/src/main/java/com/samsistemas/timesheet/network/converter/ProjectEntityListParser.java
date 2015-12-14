package com.samsistemas.timesheet.network.converter;

import android.support.annotation.NonNull;
import android.util.Log;

import static com.samsistemas.timesheet.util.JSONObjectKeys.*;
import com.samsistemas.timesheet.entity.ProjectEntity;
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
public final class ProjectEntityListParser implements JsonParser<List<ProjectEntity>, JSONArray> {
    private static final String TAG = ProjectEntityListParser.class.getSimpleName();
    private static final String DATE_TEMPLATE = "dd-MM-yyyy";
    private static ProjectEntityListParser instance = null;

    private ProjectEntityListParser() { }

    @Override
    public List<ProjectEntity> convert(@NonNull JSONArray jsonArray) throws JSONException {
        List<ProjectEntity> projectEntities = new ArrayList<>(jsonArray.length());

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonProject = jsonArray.getJSONObject(i);
            JSONObject jsonClient = jsonProject.getJSONObject(CLIENT);

            String dateString = jsonProject.getString(START_DATE);
            Date date = new Date();

            try {
                date = new SimpleDateFormat(DATE_TEMPLATE, Locale.getDefault()).parse(dateString);
            } catch (ParseException ex) {
                Log.e(TAG, ex.getMessage(), ex.getCause());
            }

            ProjectEntity entity = new ProjectEntity();

            entity.setId(jsonProject.getLong(ID));
            entity.setClientId(jsonClient.getLong(ID))
                         .setName(jsonProject.getString(NAME))
                         .setShortName(jsonProject.getString(SHORT_NAME))
                         .setStartDate(date)
                         .setEnabled(jsonProject.getBoolean(ENABLED));

            projectEntities.add(entity);
        }

        return projectEntities;
    }

    public static ProjectEntityListParser newInstance() {
        if (null == instance) {
            instance = new ProjectEntityListParser();
        }
        return instance;
    }
}
