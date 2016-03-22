package com.samsistemas.timesheet.common.adapter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import com.samsistemas.timesheet.domain.TaskType;

import java.lang.reflect.Type;

/**
 * @author jonatan.salas
 */
public final class TaskTypeAdapter implements JsonDeserializer<TaskType> {
    private static TaskTypeAdapter adapter = null;

    private TaskTypeAdapter() { }

    @Override
    public TaskType deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return null;
    }

    public static TaskTypeAdapter getInstance() {
        return (null == adapter) ? adapter = new TaskTypeAdapter() : adapter;
    }
}
