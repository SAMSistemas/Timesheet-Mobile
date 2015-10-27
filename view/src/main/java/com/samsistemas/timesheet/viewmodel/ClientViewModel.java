package com.samsistemas.timesheet.viewmodel;

import android.support.annotation.NonNull;

import com.samsistemas.timesheet.model.Client;

/**
 * @author jonatan.salas
 */
public class ClientViewModel {
    private long clientId;
    private String clientName;

    /**
     *
     * @param client
     */
    public ClientViewModel(@NonNull Client client) {
        this.clientId = client.getClientId();
        this.clientName = client.getName();
    }

    public ClientViewModel setClientId(long clientId) {
        this.clientId = clientId;
        return this;
    }

    public ClientViewModel setClientName(String clientName) {
        this.clientName = clientName;
        return this;
    }

    public long getClientId() {
        return clientId;
    }

    public String getClientName() {
        return clientName;
    }
}
