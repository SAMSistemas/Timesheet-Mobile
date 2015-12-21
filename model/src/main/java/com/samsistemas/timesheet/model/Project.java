package com.samsistemas.timesheet.model;

import java.util.Date;

/**
 * Pojo class representing a Project
 *
 * @author jonatan.salas
 */
public class Project {

    /**
     * The id of the Project
     */
    private long id;

    /**
     * The Client object associated to this Project
     */
    private Client client;

    /**
     * The name of the project
     */
    private String name;

    /**
     * The short name of the project
     */
    private String shortName;

    /**
     * The start date of the project
     */
    private Date startDate;

    /**
     * The flag that verifies if this is still active
     */
    private boolean enabled;

    /**
     * Public Constructor
     */
    public Project() { }

    /**
     * Setter as builder pattern
     *
     * @param id the id of the project
     * @return a Project object
     */
    public Project setId(long id) {
        this.id = id;
        return this;
    }

    /**
     * Setter as builder pattern
     *
     * @param client the client associated to this project
     * @return a Project object
     */
    public Project setClient(Client client) {
        this.client = client;
        return this;
    }

    /**
     * Setter as builder pattern
     *
     * @param name the name of the project
     * @return a Project object
     */
    public Project setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Setter as builder pattern
     *
     * @param shortName the short name of the project
     * @return a Project object
     */
    public Project setShortName(String shortName) {
        this.shortName = shortName;
        return this;
    }

    /**
     * Setter as builder pattern
     *
     * @param startDate the start date of the project
     * @return a Project object
     */
    public Project setStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }

    /**
     * Setter as builder pattern
     *
     * @param enabled a boolean flag that verifies if this is still active
     * @return a Project object
     */
    public Project setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    /**
     * Getter for id
     *
     * @return the value of the id field
     */
    public long getId() {
        return id;
    }

    /**
     * Getter for client
     *
     * @return the value of the client field
     */
    public Client getClient() {
        return client;
    }

    /**
     * Getter for name
     *
     * @return the value of the name field
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for short name
     *
     * @return the value of the short name field
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * Getter for the start date
     *
     * @return the value of the startDate field
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
