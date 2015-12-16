package com.samsistemas.timesheet.model;

/**
 * Pojo class representing a TaskType
 *
 * @author jonatan.salas
 */
public class TaskType {

    /**
     * The id of the task type
     */
    private long id;

    /**
     * The name of the task type
     */
    private String name;

    /**
     * A boolean flag indicating if this is still active
     */
    private boolean enabled;

    /**
     * Public constructor
     */
    public TaskType() { }

    /**
     * Setter as builder pattern
     *
     * @param id the id of the task type
     * @return a TaskType object
     */
    public TaskType setId(long id) {
        this.id = id;
        return this;
    }

    /**
     * Setter as builder pattern
     *
     * @param name the name of the task type
     * @return a TaskType object
     */
    public TaskType setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Setter as builder pattern
     *
     * @param enabled the flag that indicates if this is still active
     * @return a TaskType object
     */
    public TaskType setEnabled(boolean enabled) {
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
     * Getter for enabled
     *
     * @return the value of the enabled field
     */
    public boolean isEnabled() {
        return enabled;
    }
}
