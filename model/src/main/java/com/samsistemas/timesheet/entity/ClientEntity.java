package com.samsistemas.timesheet.entity;

import com.samsistemas.timesheet.entity.base.Entity;

/**
 * Domain class that represents the data contained in the table Client.
 *
 * @author jonatan.salas
 */
public class ClientEntity extends Entity {
    private String name;
    private String shortName;
    private boolean enabled;

    public ClientEntity() {}

    /** Attributes setters and getters **/
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
