package com.samsistemas.timesheet.entity;

import com.samsistemas.timesheet.entity.base.Entity;

/**
 * @author jonatan.salas
 */
public class WorkPositionEntity extends Entity {
    private String description;

    public WorkPositionEntity() {}

    /** Attributes setters and getters **/
    public WorkPositionEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getDescription() {
        return description;
    }
}
