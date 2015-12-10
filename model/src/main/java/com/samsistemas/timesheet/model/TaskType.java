package com.samsistemas.timesheet.model;

/**
 * @author jonatan.salas
 */
public class TaskType {
    private long id;
    private String name;
    private boolean enabled;

    public TaskType() { }

    public TaskType setId(long id) {
        this.id = id;
        return this;
    }

    public TaskType setName(String name) {
        this.name = name;
        return this;
    }

    public TaskType setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isEnabled() {
        return enabled;
    }
}
