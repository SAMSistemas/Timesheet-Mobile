package com.samsistemas.timesheet.entity;

import com.samsistemas.timesheet.entity.base.Entity;

/**
 * Domain class that represents the data contained in the table TaskType.
 *
 * @author jonatan.salas
 */
public class TaskTypeEntity extends Entity {

    /**
     * The name of the TaskType
     */
    private String name;

    /**
     * Flag that indicates if this is still active
     */
    private boolean enabled;

    /**
     * Public Constructor
     */
    public TaskTypeEntity() { }

    /**
     * Setter as builder pattern
     *
     * @param name the name of the TaskType as String
     * @return a TaskTypeEntity object
     */
    public TaskTypeEntity setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Setter as builder pattern
     *
     * @param enabled a flag that indicates if this is still active
     * @return a TaskTypeEntity object
     */
    public TaskTypeEntity setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    /**
     * Getter for name
     *
     * @return a String representation of name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for enabled
     *
     * @return the boolean value of enabled
     */
    public boolean isEnabled() {
        return enabled;
    }
}
