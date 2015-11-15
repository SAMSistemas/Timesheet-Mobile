package com.samsistemas.timesheet.entity;

/**
 * Domain class that represents the data contained in the table Client.
 *
 * @author jonatan.salas
 */
public class ClientEntity {
    private long clientId;
    private String name;
    private String shortName;
    private boolean enabled;

    public ClientEntity() {}

    /** Attributes setters and getters **/
    public ClientEntity setClientId(long clientId) {
        this.clientId = clientId;
        return this;
    }

    public ClientEntity setName(String name) {
        this.name = name;
        return this;
    }

    public ClientEntity setShortName(String shortName) {
        this.shortName = shortName;
        return this;
    }

    public ClientEntity setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public long getClientId() {
        return clientId;
    }

    public String getName() {
        return name;
    }

    public String getShortName() {
        return shortName;
    }

    public boolean isEnabled() {
        return enabled;
    }

}
