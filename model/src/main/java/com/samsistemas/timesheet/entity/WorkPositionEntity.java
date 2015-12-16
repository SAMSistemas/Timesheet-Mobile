package com.samsistemas.timesheet.entity;

import com.samsistemas.timesheet.entity.base.Entity;

/**
 * Entity that represents a WorkPosition table row content
 *
 * @author jonatan.salas
 */
public class WorkPositionEntity extends Entity {

    /**
     * The description of the WorkPosition
     */
    private String description;

    /**
     * Public Constructor
     */
    public WorkPositionEntity() { }

    /**
     * Setter as builder pattern
     *
     * @param description an string representing a description
     * @return a WorkPositionEntity object
     */
    public WorkPositionEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * Getter for description
     *
     * @return a String representation of description
     */
    public String getDescription() {
        return description;
    }
}
