package com.samsistemas.timesheet.network.converter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.samsistemas.timesheet.data.R;
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
public class ProjectEntityListParser implements JsonParser<List<ProjectEntity>, JSONArray> {
    protected static final String TAG = ProjectEntityListParser.class.getSimpleName();
    protected static final String DATE_TEMPLATE = "dd-MM-yyyy";
    protected static ProjectEntityListParser instance = null;

    protected ProjectEntityListParser() {}

    @Override
    public List<ProjectEntity> convert(@NonNull Context context, @NonNull JSONArray jsonArray) throws JSONException {
        final List<ProjectEntity> projectEntities = new ArrayList<>(jsonArray.length());
        final ProjectEntity projectEntity = new ProjectEntity();

        for(int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonProject = jsonArray.getJSONObject(i);
            JSONObject jsonClient = jsonProject.getJSONObject(context.getString(R.string.client_name));

            String dateString = jsonProject.getString(context.getString(R.string.start_date));
            Date date = new Date();

            try {
                date = new SimpleDateFormat(DATE_TEMPLATE, Locale.getDefault()).parse(dateString);
            } catch (ParseException ex) {
                Log.e(TAG, ex.getMessage(), ex.getCause());
            }

            projectEntity.setProjectId(jsonProject.getLong(context.getString(R.string.id)))
                         .setClientId(jsonClient.getLong(context.getString(R.string.id)))
                         .setName(jsonProject.getString(context.getString(R.string.name)))
                         .setShortName(jsonProject.getString(context.getString(R.string.short_name)))
                         .setStartDate(date)
                         .setEnabled(jsonProject.getBoolean(context.getString(R.string.enabled)));

            projectEntities.add(i, projectEntity);
        }

        return projectEntities;
    }

    public static ProjectEntityListParser newInstance() {
        if(null == instance)
            instance = new ProjectEntityListParser();
        return instance;
    }
}
