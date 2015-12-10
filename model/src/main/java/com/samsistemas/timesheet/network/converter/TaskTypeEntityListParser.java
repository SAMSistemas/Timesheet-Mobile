package com.samsistemas.timesheet.network.converter;

import android.support.annotation.NonNull;

import static com.samsistemas.timesheet.constant.JSONConst.*;
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
public final class TaskTypeEntityListParser implements JsonParser<List<TaskTypeEntity>, JSONArray> {
    private static TaskTypeEntityListParser instance = null;

    private TaskTypeEntityListParser() {}

    @Override
    public List<TaskTypeEntity> convert(@NonNull JSONArray jsonArray) throws JSONException {
        final List<TaskTypeEntity> taskTypeEntities = new ArrayList<>(jsonArray.length());

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonTaskType = jsonArray.getJSONObject(i);

            TaskTypeEntity taskTypeEntity = new TaskTypeEntity();
            taskTypeEntity.setId(jsonTaskType.getLong(ID));
            taskTypeEntity.setName(jsonTaskType.getString(NAME))
                          .setEnabled(true);

            taskTypeEntities.add(i, taskTypeEntity);
        }

        return taskTypeEntities;
    }

    public static TaskTypeEntityListParser newInstance() {
        if (null == instance) {
            instance = new TaskTypeEntityListParser();
        }
        return instance;
    }
}
