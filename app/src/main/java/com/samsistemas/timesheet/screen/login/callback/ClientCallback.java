package com.samsistemas.timesheet.screen.login.callback;

import android.support.annotation.NonNull;

import com.samsistemas.timesheet.domain.Client;
import com.samsistemas.timesheet.screen.login.callback.base.BaseCallback;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * @author jonatan.salas
 */
public class ClientCallback extends BaseCallback<Client> {
    private final String clientName;

    public ClientCallback(@NonNull String username, @NonNull String password, @NonNull String clientName) {
        super(username, password);
        this.clientName = clientName;
    }

    @Override
    public Client execute() {
        final Call<List<Client>> response = getService().findClientsByName(clientName);
        List<Client> clients = null;

        try {
            final Response<List<Client>> resp = response.execute();
            clients = resp.body();
        } catch (IOException ex){
            ex.printStackTrace();
        }

        return (null != clients) ? clients.get(0) : null;
    }
}
