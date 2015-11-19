package com.samsistemas.timesheet.network.converter;

import android.content.Context;
import android.support.annotation.NonNull;

import com.samsistemas.timesheet.data.R;
import com.samsistemas.timesheet.entity.TaskTypeEntity;
import com.samsistemas.timesheet.network.converter.base.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jonatan.salas
 */
public class TaskTypeEntityListParser implements JsonParser<List<TaskTypeEntity>, JSONArray> {
    protected static TaskTypeEntityListParser instance = null;

    protected TaskTypeEntityListParser() {}

    @Override
    public List<TaskTypeEntity> convert(@NonNull Context context, @NonNull JSONArray jsonArray) throws JSONException {
        final List<TaskTypeEntity> taskTypeEntities = new ArrayList<>(jsonArray.length());

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonTaskType = jsonArray.getJSONObject(i);

            TaskTypeEntity taskTypeEntity = new TaskTypeEntity();
            taskTypeEntity.setTaskTypeId(jsonTaskType.getLong(context.getString(R.string.id)))
                          .setName(jsonTaskType.getString(context.getString(R.string.name)))
                          .setEnabled(true);

            taskTypeEntities.add(i, taskTypeEntity);
        }

        return taskTypeEntities;
    }

    public static TaskTypeEntityListParser newInstance() {
        if(null == instance)
            instance = new TaskTypeEntityListParser();
        return instance;
    }
}
