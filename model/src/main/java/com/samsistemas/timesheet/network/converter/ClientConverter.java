package com.samsistemas.timesheet.network.converter;

import android.content.Context;
import android.support.annotation.NonNull;

import com.samsistemas.timesheet.entity.ClientEntity;
import com.samsistemas.timesheet.network.converter.base.JsonConverter;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;

/**
 * @author jonatan.salas
 */
public class ClientConverter implements JsonConverter<List<ClientEntity>, JSONArray> {
    protected static ClientConverter instance = null;

    protected ClientConverter() {}

    @Override
    public List<ClientEntity> convert(@NonNull Context context, @NonNull JSONArray jsonArray) throws JSONException {
        return null;
    }

    public static ClientConverter newInstance() {
        if(null == instance)
            instance = new ClientConverter();
        return instance;
    }
}
