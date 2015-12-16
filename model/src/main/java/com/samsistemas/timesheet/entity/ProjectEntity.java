package com.samsistemas.timesheet.entity;

import com.samsistemas.timesheet.entity.base.Entity;

import java.util.Date;

/**
 * Domain class that represents the data contained in the table Project.
 *
 * @author jonatan.salas
 */
public class ProjectEntity extends Entity {

    /**
     *
     */
    private long clientId;

    /**
     *
     */
    private String name;

    /**
     *
     */
    private String shortName;

    /**
     *
     */
    private Date startDate;

    /**
     *
     */
    private boolean enabled;

    /**
     *
     */
    public ProjectEntity() { }

    /**
     *
     * @param clientId
     * @return
     */
    public ProjectEntity setClientId(long clientId) {
        this.clientId = clientId;
        return this;
    }

    /**
     *
     * @param name
     * @return
     */
    public ProjectEntity setName(String name) {
        this.name = name;
        return this;
    }

    /**
     *
     * @param shortName
     * @return
     */
    public ProjectEntity setShortName(String shortName) {
        this.shortName = shortName;
        return this;
    }

    /**
     *
     * @param startDate
     * @return
     */
    public ProjectEntity setStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }

    /**
     *
     * @param enabled
     * @return
     */
    public ProjectEntity setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    /**
     *
     * @return
     */
    public long getClientId() {
        return clientId;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return
     */
    public String getShortName() {
        return shortName;
    }

    /**
     *
     * @return
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     *
     * @return
     */
    public boolean isEnabled() {
        return enabled;
    }
}