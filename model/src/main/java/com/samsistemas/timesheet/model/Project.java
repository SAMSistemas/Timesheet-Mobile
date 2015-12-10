package com.samsistemas.timesheet.model;

import java.util.Date;

/**
 * @author jonatan.salas
 */
public class Project {
    private long id;
    private Client client;
    private String name;
    private String shortName;
    private Date startDate;
    private boolean enabled;

    public Project() { }

    public Project setId(long id) {
        this.id = id;
        return this;
    }

    public Project setClient(Client client) {
        this.client = client;
        return this;
    }

    public Project setName(String name) {
        this.name = name;
        return this;
    }

    public Project setShortName(String shortName) {
        this.shortName = shortName;
        return this;
    }

    public Project setStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }

    public Project setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public long getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public String getName() {
        return name;
    }

    public String getShortName() {
        return shortName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public boolean isEnabled() {
        return enabled;
    }
}
