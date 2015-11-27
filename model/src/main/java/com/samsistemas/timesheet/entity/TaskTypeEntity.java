package com.samsistemas.timesheet.entity;

import com.samsistemas.timesheet.entity.base.Entity;

/**
 * Domain class that represents the data contained in the table TaskType.
 *
 * @author jonatan.salas
 */
public class TaskTypeEntity extends Entity {
    private String name;
    private boolean enabled;

    public TaskTypeEntity() {}

    /** Attribute setters and getters **/
    public TaskTypeEntity setName(String name) {
        this.name = name;
        return this;
    }

    public TaskTypeEntity setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public String getName() {
        return name;
    }

    public boolean isEnabled() {
        return enabled;
    }
}
