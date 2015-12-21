package com.samsistemas.timesheet.model;

/**
 * Pojo class representing a Client
 *
 * @author jonatan.salas
 */
public class Client {

    /**
     * The id of the client
     */
    private long id;

    /**
     * The name of the client
     */
    private String name;

    /**
     * The short name of the client
     */
    private String shortName;

    /**
     * Flag that indicates if the client is still active
     */
    private boolean enabled;

    /**
     * Public Constructor
     */
    public Client() { }

    /**
     * Setter as builder pattern
     *
     * @param id the id of the client
     * @return a Client object
     */
    public Client setId(long id) {
        this.id = id;
        return this;
    }

    /**
     * Setter as builder pattern
     *
     * @param name the name of the client
     * @return a Client object
     */
    public Client setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Setter as builder pattern
     *
     * @param shortName the short name of the client
     * @return a Client object
     */
    public Client setShortName(String shortName) {
        this.shortName = shortName;
        return this;
    }

    /**
     * Setter as builder pattern
     *
     * @param enabled a boolean flag indicating that this Client is still active
     * @return a Client object
     */
    public Client setEnabled(boolean enabled) {
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
     * @return the value of the shortName field
     */
    public String getShortName() {
        return shortName;
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
