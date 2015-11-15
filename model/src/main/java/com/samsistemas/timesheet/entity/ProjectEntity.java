package com.samsistemas.timesheet.entity;

import java.util.Date;

/**
 * Domain class that represents the data contained in the table Project.
 *
 * @author jonatan.salas
 */
public class ProjectEntity {
    private long projectId;
    private long clientId;
    private String name;
    private String shortName;
    private Date startDate;
    private boolean enabled;

    public ProjectEntity() {}

    /**
     * Attribute setters and getters
     **/
    public ProjectEntity setProjectId(long projectId) {
        this.projectId = projectId;
        return this;
    }

    public ProjectEntity setClientId(long clientId) {
        this.clientId = clientId;
        return this;
    }

    public ProjectEntity setName(String name) {
        this.name = name;
        return this;
    }

    public ProjectEntity setShortName(String shortName) {
        this.shortName = shortName;
        return this;
    }

    public ProjectEntity setStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }

    public ProjectEntity setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public long getProjectId() {
        return projectId;
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

    public Date getStartDate() {
        return startDate;
    }

    public boolean isEnabled() {
        return enabled;
    }
}