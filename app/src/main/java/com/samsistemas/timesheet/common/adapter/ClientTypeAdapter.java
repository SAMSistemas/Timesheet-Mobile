package com.samsistemas.timesheet.common.adapter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import com.samsistemas.timesheet.domain.Client;

import java.lang.reflect.Type;

/**
 * @author jonatan.salas
 */
public final class ClientTypeAdapter implements JsonDeserializer<Client> {
    private static ClientTypeAdapter adapter = null;

    private ClientTypeAdapter() { }

    @Override
    public Client deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return null;
    }

    public static ClientTypeAdapter getInstance() {
        return (null == adapter) ? adapter = new ClientTypeAdapter() : adapter;
    }
}
