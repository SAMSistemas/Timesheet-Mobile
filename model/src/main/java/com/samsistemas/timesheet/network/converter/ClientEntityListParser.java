package com.samsistemas.timesheet.network.converter;

import android.support.annotation.NonNull;

import static com.samsistemas.timesheet.constant.JSONConst.*;
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
public final class ClientEntityListParser implements JsonParser<List<ClientEntity>, JSONArray> {
    private static ClientEntityListParser instance = null;

    private ClientEntityListParser() { }

    @Override
    public List<ClientEntity> convert(@NonNull JSONArray jsonArray) throws JSONException {
        final List<ClientEntity> clientEntities = new ArrayList<>(jsonArray.length());

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonProject = jsonArray.getJSONObject(i);
            JSONObject jsonClient = jsonProject.getJSONObject(CLIENT);

            ClientEntity clientEntity = new ClientEntity();

            clientEntity.setId(jsonClient.getLong(ID));
            clientEntity.setName(jsonClient.getString(NAME))
                        .setShortName(jsonClient.getString(SHORT_NAME))
                        .setEnabled(jsonClient.getBoolean(ENABLED));

            clientEntities.add(i, clientEntity);
        }

        return clientEntities;
    }

    public static ClientEntityListParser newInstance() {
        if (null == instance) {
            instance = new ClientEntityListParser();
        }
        return instance;
    }
}
