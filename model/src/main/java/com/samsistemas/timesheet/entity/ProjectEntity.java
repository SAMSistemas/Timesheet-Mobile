package com.samsistemas.timesheet.entity;

import com.samsistemas.timesheet.entity.base.Entity;

import java.util.Date;

/**
 * Domain class that represents the data contained in the table Project.
 *
 * @author jonatan.salas
 */
public class ProjectEntity extends Entity {
    private long clientId;
    private String name;
    private String shortName;
    private Date startDate;
    private boolean enabled;

    public ProjectEntity() {}

    /**
     * Attribute setters and getters
     **/
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