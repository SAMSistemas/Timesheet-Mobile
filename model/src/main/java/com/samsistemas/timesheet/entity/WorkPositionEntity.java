package com.samsistemas.timesheet.entity;

import com.samsistemas.timesheet.entity.base.Entity;

/**
 * @author jonatan.salas
 */
public class WorkPositionEntity extends Entity {

    /**
     *
     */
    private String description;

    /**
     *
     */
    public WorkPositionEntity() { }

    /**
     *
     * @param description
     * @return
     */
    public WorkPositionEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     *
     * @return
     */
    public String getDescription() {
        return description;
    }
}
