package com.samsistemas.timesheet.network.converter;

import android.content.Context;
import android.support.annotation.NonNull;

import com.samsistemas.timesheet.data.R;
import com.samsistemas.timesheet.entity.ClientEntity;
import com.samsistemas.timesheet.network.converter.base.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jonatan.salas
 */
public class ClientEntityListParser implements JsonParser<List<ClientEntity>, JSONArray> {
    protected static ClientEntityListParser instance = null;

    protected ClientEntityListParser() {}

    @Override
    public List<ClientEntity> convert(@NonNull Context context, @NonNull JSONArray jsonArray) throws JSONException {
        final List<ClientEntity> clientEntities = new ArrayList<>(jsonArray.length());
        final ClientEntity clientEntity = new ClientEntity();

        for(int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonClient = jsonArray.getJSONObject(i);

            clientEntity.setClientId(jsonClient.getLong(context.getString(R.string.id)))
                        .setName(jsonClient.getString(context.getString(R.string.name)))
                        .setShortName(jsonClient.getString(context.getString(R.string.short_name)))
                        .setEnabled(jsonClient.getBoolean(context.getString(R.string.enabled)));

            clientEntities.add(i, clientEntity);
        }

        return clientEntities;
    }

    public static ClientEntityListParser newInstance() {
        if(null == instance)
            instance = new ClientEntityListParser();
        return instance;
    }
}
