package com.samsistemas.timesheet.model;

/**
 * @author jonatan.salas
 */
public class Client {

    /**
     *
     */
    private long id;

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
    private boolean enabled;

    /**
     *
     */
    public Client() { }

    /**
     *
     * @param id
     * @return
     */
    public Client setId(long id) {
        this.id = id;
        return this;
    }

    /**
     *
     * @param name
     * @return
     */
    public Client setName(String name) {
        this.name = name;
        return this;
    }

    /**
     *
     * @param shortName
     * @return
     */
    public Client setShortName(String shortName) {
        this.shortName = shortName;
        return this;
    }

    /**
     *
     * @param enabled
     * @return
     */
    public Client setEnabled(boolean enabled) {
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
    public boolean isEnabled() {
        return enabled;
    }
}
