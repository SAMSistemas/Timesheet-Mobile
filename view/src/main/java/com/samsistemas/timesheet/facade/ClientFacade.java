package com.samsistemas.timesheet.facade;

import android.content.Context;
import android.support.annotation.NonNull;

import com.samsistemas.timesheet.controller.base.BaseController;
import com.samsistemas.timesheet.entity.ClientEntity;
import com.samsistemas.timesheet.facade.base.Facade;
import com.samsistemas.timesheet.factory.ControllerFactory;
import com.samsistemas.timesheet.model.Client;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jonatan.salas
 */
public class ClientFacade implements Facade<Client> {
    private static ClientFacade instance = null;
    protected BaseController<ClientEntity> clientController;

    protected ClientFacade() {
        this.clientController = ControllerFactory.getClientController();
    }

    @Override
    public Client findById(@NonNull Context context, long id) {
        final ClientEntity entity = clientController.get(context, id);
        final Client client = new Client();

        client.setId(entity.getClientId())
              .setName(entity.getName())
              .setShortName(entity.getShortName())
              .setEnabled(entity.isEnabled());

        return client;
    }

    @Override
    public List<Client> findAll(@NonNull Context context) {
        final List<ClientEntity> clientEntities = clientController.listAll(context);
        final List<Client> clients = new ArrayList<>(clientEntities.size());
        final Client client = new Client();
        ClientEntity entity;

        for(int i = 0; i < clientEntities.size(); i++) {
            entity = clientEntities.get(i);

            client.setId(entity.getClientId())
                    .setName(entity.getName())
                    .setShortName(entity.getShortName())
                    .setEnabled(entity.isEnabled());

            clients.add(client);
        }

        return clients;
    }

    @Override
    public boolean insert(@NonNull Context context, Client client) {
        final ClientEntity entity = new ClientEntity();

        entity.setClientId(client.getId())
              .setName(client.getName())
              .setShortName(client.getShortName())
              .setEnabled(client.isEnabled());

        return clientController.insert(context, entity);
    }

    @Override
    public boolean update(@NonNull Context context, Client client) {
        final ClientEntity entity = new ClientEntity();

        entity.setClientId(client.getId())
                .setName(client.getName())
                .setShortName(client.getShortName())
                .setEnabled(client.isEnabled());

        return clientController.update(context, entity);
    }

    @Override
    public boolean deleteById(@NonNull Context context, long id) {
        return clientController.delete(context, id);
    }

    public static ClientFacade newInstance() {
        if(null == instance)
            instance = new ClientFacade();
        return instance;
    }
}
