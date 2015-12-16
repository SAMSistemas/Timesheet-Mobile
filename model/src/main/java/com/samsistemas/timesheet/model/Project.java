package com.samsistemas.timesheet.model;

import java.util.Date;

/**
 * @author jonatan.salas
 */
public class Project {

    /**
     *
     */
    private long id;

    /**
     *
     */
    private Client client;

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
    public Project() { }

    /**
     *
     * @param id
     * @return
     */
    public Project setId(long id) {
        this.id = id;
        return this;
    }

    /**
     *
     * @param client
     * @return
     */
    public Project setClient(Client client) {
        this.client = client;
        return this;
    }

    /**
     *
     * @param name
     * @return
     */
    public Project setName(String name) {
        this.name = name;
        return this;
    }

    /**
     *
     * @param shortName
     * @return
     */
    public Project setShortName(String shortName) {
        this.shortName = shortName;
        return this;
    }

    /**
     *
     * @param startDate
     * @return
     */
    public Project setStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }

    /**
     *
     * @param enabled
     * @return
     */
    public Project setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    /**
     *
     * @return
     */
    public long getId() {
        return id;
    }

    /**
     *
     * @return
     */
    public Client getClient() {
        return client;
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
