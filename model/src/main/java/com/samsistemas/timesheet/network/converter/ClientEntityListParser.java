package com.samsistemas.timesheet.network.converter;

import android.content.Context;
import android.support.annotation.NonNull;

import com.samsistemas.timesheet.constant.JSONConstants;
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
public class ClientEntityListParser implements JsonParser<List<ClientEntity>, JSONArray>, JSONConstants {
    protected static ClientEntityListParser instance = null;

    protected ClientEntityListParser() {}

    @Override
    public List<ClientEntity> convert(@NonNull Context context, @NonNull JSONArray jsonArray) throws JSONException {
        final List<ClientEntity> clientEntities = new ArrayList<>(jsonArray.length());

        for(int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonProject = jsonArray.getJSONObject(i);
            JSONObject jsonClient = jsonProject.getJSONObject(CLIENT);

            ClientEntity clientEntity = new ClientEntity();
            clientEntity.setClientId(jsonClient.getLong(ID))
                        .setName(jsonClient.getString(NAME))
                        .setShortName(jsonClient.getString(SHORT_NAME))
                        .setEnabled(jsonClient.getBoolean(ENABLED));

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
