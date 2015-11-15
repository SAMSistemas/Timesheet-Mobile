package com.samsistemas.timesheet.entity;

/**
 * @author jonatan.salas
 */
public class WorkPositionEntity {
    private long workPositionId;
    private String description;

    public WorkPositionEntity() {}

    /** Attributes setters and getters **/
    public WorkPositionEntity setWorkPositionId(long workPositionId) {
        this.workPositionId = workPositionId;
        return this;
    }

    public WorkPositionEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public long getWorkPositionId() {
        return workPositionId;
    }

    public String getDescription() {
        return description;
    }

}
