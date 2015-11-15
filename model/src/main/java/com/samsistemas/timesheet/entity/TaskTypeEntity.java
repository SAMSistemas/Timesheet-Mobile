package com.samsistemas.timesheet.entity;

/**
 * Domain class that represents the data contained in the table TaskType.
 *
 * @author jonatan.salas
 */
public class TaskTypeEntity {
    private long taskTypeId;
    private String name;
    private boolean enabled;

    public TaskTypeEntity() {}

    /** Attribute setters and getters **/
    public TaskTypeEntity setTaskTypeId(long taskTypeId) {
        this.taskTypeId = taskTypeId;
        return this;
    }

    public TaskTypeEntity setName(String name) {
        this.name = name;
        return this;
    }

    public TaskTypeEntity setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public long getTaskTypeId() {
        return taskTypeId;
    }

    public String getName() {
        return name;
    }

    public boolean isEnabled() {
        return enabled;
    }
}
