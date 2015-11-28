package com.samsistemas.timesheet.facade;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.samsistemas.timesheet.controller.Controller;
import com.samsistemas.timesheet.entity.ClientEntity;
import com.samsistemas.timesheet.facade.base.Facade;
import com.samsistemas.timesheet.factory.ControllerFactory;
import com.samsistemas.timesheet.helper.UriHelper;
import com.samsistemas.timesheet.model.Client;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jonatan.salas
 */
public class ClientFacade implements Facade<Client> {
    private static ClientFacade instance = null;
    protected Controller<ClientEntity> clientController;

    protected ClientFacade() {
        this.clientController = ControllerFactory.getClientController();
    }

    @Override
    public Client findById(@NonNull Context context, long id) {
        final Uri uri = UriHelper.buildClientUriWithId(context, id);
        final ClientEntity entity = clientController.get(context, uri);
        final Client client = new Client();

        client.setId(entity.getId())
              .setName(entity.getName())
              .setShortName(entity.getShortName())
              .setEnabled(entity.isEnabled());

        return client;
    }

    @Override
    public List<Client> findAll(@NonNull Context context) {
        final Uri uri = UriHelper.buildClientUri(context);
        final List<ClientEntity> clientEntities = clientController.listAll(context, uri);
        final List<Client> clients = new ArrayList<>(clientEntities.size());
        final Client client = new Client();
        ClientEntity entity;

        for(int i = 0; i < clientEntities.size(); i++) {
            entity = clientEntities.get(i);

            client.setId(entity.getId())
                    .setName(entity.getName())
                    .setShortName(entity.getShortName())
                    .setEnabled(entity.isEnabled());

            clients.add(client);
        }

        return clients;
    }

    @Override
    public boolean insert(@NonNull Context context, Client client) {
        final Uri uri = UriHelper.buildClientUri(context);
        final ClientEntity entity = new ClientEntity();

        entity.setId(client.getId());
        entity.setName(client.getName())
              .setShortName(client.getShortName())
              .setEnabled(client.isEnabled());

        return clientController.insert(context, entity, uri);
    }

    @Override
    public boolean update(@NonNull Context context, Client client) {
        final Uri uri = UriHelper.buildClientUri(context);
        final ClientEntity entity = new ClientEntity();

        entity.setId(client.getId());
        entity.setName(client.getName())
              .setShortName(client.getShortName())
              .setEnabled(client.isEnabled());

        return clientController.update(context, entity, uri);
    }

    @Override
    public boolean deleteById(@NonNull Context context, long id) {
        final Uri uri = UriHelper.buildClientUri(context);
        return clientController.delete(context, uri, id);
    }

    public static ClientFacade newInstance() {
        if(null == instance)
            instance = new ClientFacade();
        return instance;
    }
}
