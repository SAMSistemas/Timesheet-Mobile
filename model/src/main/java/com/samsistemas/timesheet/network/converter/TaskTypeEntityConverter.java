package com.samsistemas.timesheet.network.converter;

import com.samsistemas.timesheet.entity.TaskTypeEntity;
import com.samsistemas.timesheet.network.converter.base.JSONConverter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.samsistemas.timesheet.util.JSONObjectKeys.ID;
import static com.samsistemas.timesheet.util.JSONObjectKeys.NAME;

/**
 * @author jonatan.salas
 */
public final class TaskTypeEntityConverter implements JSONConverter<TaskTypeEntity> {
    private static TaskTypeEntityConverter instance = null;

    /**
     * Private constructor
     */
    private TaskTypeEntityConverter() { }

    @Override
    public TaskTypeEntity asObject(JSONObject object) throws JSONException {
        TaskTypeEntity entity = new TaskTypeEntity();

        entity.setId(object.getLong(ID));
        entity.setName(object.getString(NAME))
              .setEnabled(true);

        return entity;
    }

    @Override
    public List<TaskTypeEntity> asList(JSONArray array) throws JSONException {
        final List<TaskTypeEntity> taskTypeEntities = new ArrayList<>(array.length());

        for (int i = 0; i < array.length(); i++) {
            JSONObject jsonTaskType = array.getJSONObject(i);
            taskTypeEntities.add(asObject(jsonTaskType));
        }

        return taskTypeEntities;
    }

    /**
     * Method that gets a singleton instance.
     *
     * @return a singleton object.
     */
    public static TaskTypeEntityConverter newInstance() {
        if (null == instance) {
            instance = new TaskTypeEntityConverter();
        }
        return instance;
    }
}
