package com.samsistemas.timesheet.viewmodel;

import android.support.annotation.NonNull;

import com.samsistemas.timesheet.entity.ClientEntity;

/**
 * @author jonatan.salas
 */
public class ClientViewModel {
    private long clientId;
    private String clientName;

    /**
     *
     * @param clientEntity
     */
    public ClientViewModel(@NonNull ClientEntity clientEntity) {
        this.clientId = clientEntity.getClientId();
        this.clientName = clientEntity.getName();
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
