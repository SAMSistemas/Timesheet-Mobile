package com.samsistemas.timesheet.entity;

import com.samsistemas.timesheet.entity.base.Entity;

/**
 * Entity class that represents a Client.
 *
 * @author jonatan.salas
 */
public class ClientEntity extends Entity {

    /**
     *  The client name.
     */
    private String name;

    /**
     *  The short name used to recognize the Client.
     */
    private String shortName;

    /**
     *  This flag verify if this client is still active.
     */
    private boolean enabled;

    /**
     * Default constructor.
     */
    public ClientEntity() { }

    /**
     * Name setter as builder pattern
     *
     * @param name the name of the client
     * @return a ClientEntity with name set
     */
    public ClientEntity setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * ShortName setter as builder pattern
     *
     * @param shortName the short name of the client
     * @return a ClientEntity with short name set
     */
    public ClientEntity setShortName(String shortName) {
        this.shortName = shortName;
        return this;
    }

    /**
     * Enabled setter as builder pattern
     *
     * @param enabled flag to verify if the client is still active
     * @return a ClientEntity with enable flag set
     */
    public ClientEntity setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    /**
     * Name getter
     *
     * @return the name of this client
     */
    public String getName() {
        return name;
    }

    /**
     * ShortName getter
     *
     * @return the shortName of the client
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * Enabled getter
     *
     * @return the value of the flag enabled
     */
    public boolean isEnabled() {
        return enabled;
    }

}
