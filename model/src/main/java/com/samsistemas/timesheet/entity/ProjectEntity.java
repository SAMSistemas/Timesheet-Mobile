package com.samsistemas.timesheet.entity;

import com.samsistemas.timesheet.entity.base.Entity;

import java.util.Date;

/**
 * Entity that represents a Project table row content
 *
 * @author jonatan.salas
 */
public class ProjectEntity extends Entity {

    /**
     * The id of the client to reference
     */
    private long clientId;

    /**
     * The name of the project
     */
    private String name;

    /**
     * The short name of the project
     */
    private String shortName;

    /**
     * The startDate of the project
     */
    private Date startDate;

    /**
     * A boolean flag indicating if this is still active
     */
    private boolean enabled;

    /**
     * Public Constructor
     */
    public ProjectEntity() { }

    /**
     * Setter as builder pattern
     *
     * @param clientId the id of the client to reference
     * @return a ProjectEntity object
     */
    public ProjectEntity setClientId(long clientId) {
        this.clientId = clientId;
        return this;
    }

    /**
     * Setter as builder pattern
     *
     * @param name the name of the project
     * @return a ProjectEntity object
     */
    public ProjectEntity setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Setter as builder pattern
     *
     * @param shortName the short name of the project
     * @return a ProjectEntity object
     */
    public ProjectEntity setShortName(String shortName) {
        this.shortName = shortName;
        return this;
    }

    /**
     * Setter as builder pattern
     *
     * @param startDate the start date of the project
     * @return a ProjectEntity object
     */
    public ProjectEntity setStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }

    /**
     * Setter as builder pattern
     *
     * @param enabled a boolean flag that indicate if this is still active
     * @return a ProjectEntity object
     */
    public ProjectEntity setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    /**
     * Getter for ClientId
     *
     * @return the value of the clientId field
     */
    public long getClientId() {
        return clientId;
    }

    /**
     * Getter for Name
     *
     * @return the value of the name field
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for ShortName
     *
     * @return the value of the short name field
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * Getter for Start Date
     *
     * @return the value of the Start Date
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Getter for enabled
     *
     * @return the value of the enabled field
     */
    public boolean isEnabled() {
        return enabled;
    }
}