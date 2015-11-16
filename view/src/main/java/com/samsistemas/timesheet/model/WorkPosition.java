package com.samsistemas.timesheet.model;

/**
 * @author jonatan.salas
 */
public class WorkPosition {
    private long id;
    private String description;

    public WorkPosition() {}

    public WorkPosition setId(long id) {
        this.id = id;
        return this;
    }

    public WorkPosition setDescription(String description) {
        this.description = description;
        return this;
    }

    public long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}
