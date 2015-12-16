package com.samsistemas.timesheet.network.converter;

import com.samsistemas.timesheet.entity.ClientEntity;
import com.samsistemas.timesheet.network.converter.base.JSONConverter;

import static com.samsistemas.timesheet.util.JSONObjectKeys.ID;
import static com.samsistemas.timesheet.util.JSONObjectKeys.NAME;
import static com.samsistemas.timesheet.util.JSONObjectKeys.SHORT_NAME;
import static com.samsistemas.timesheet.util.JSONObjectKeys.ENABLED;
import static com.samsistemas.timesheet.util.JSONObjectKeys.CLIENT;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jonatan.salas
 */
public class ClientConverter implements JSONConverter<ClientEntity> {
    private static ClientConverter instance = null;

    /**
     * Private Constructor
     */
    private ClientConverter() { }

    @Override
    public ClientEntity asObject(JSONObject object) throws JSONException {
        ClientEntity entity = new ClientEntity();

        entity.setId(object.getLong(ID));
        entity.setName(object.getString(NAME))
              .setShortName(object.getString(SHORT_NAME))
              .setEnabled(object.getBoolean(ENABLED));
        
        return entity;
    }

    @Override
    public List<ClientEntity> asList(JSONArray array) throws JSONException {
        List<ClientEntity> clientEntities = new ArrayList<>(array.length());

        for (int i = 0; i < array.length(); i++) {
            JSONObject jsonProject = array.getJSONObject(i);
            JSONObject jsonClient = jsonProject.getJSONObject(CLIENT);

            clientEntities.add(asObject(jsonClient));
        }

        return clientEntities;
    }

    /**
     * Method that gets a singleton instance.
     *
     * @return a singleton object.
     */
    public static ClientConverter newInstance() {
        if (null == instance) {
            instance = new ClientConverter();
        }
        return instance;
    }
}
