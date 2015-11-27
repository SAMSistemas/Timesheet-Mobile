package com.samsistemas.timesheet.entity;

import com.samsistemas.timesheet.entity.base.Entity;

import java.util.Date;

/**
 * Domain class that represents the data contained in the table JobLog.
 *
 * @author jonatan.salas
 */
public class JobLogEntity extends Entity {
    private long projectId;
    private long personId;
    private long taskTypeId;
    private String hours;
    private Date workDate;
    private int solicitude;
    private String observations;

    public JobLogEntity() {}

    /**
     * Attributes setters and getters
     **/
    public JobLogEntity setProjectId(long projectId) {
        this.projectId = projectId;
        return this;
    }

    public JobLogEntity setPersonId(long personId) {
        this.personId = personId;
        return this;
    }

    public JobLogEntity setTaskTypeId(long taskTypeId) {
        this.taskTypeId = taskTypeId;
        return this;
    }

    public JobLogEntity setHours(String hours) {
        this.hours = hours;
        return this;
    }

    public JobLogEntity setWorkDate(Date workDate) {
        this.workDate = workDate;
        return this;
    }

    public JobLogEntity setSolicitude(int solicitude) {
        this.solicitude = solicitude;
        return this;
    }

    public JobLogEntity setObservations(String observations) {
        this.observations = observations;
        return this;
    }

    public long getProjectId() {
        return projectId;
    }

    public long getPersonId() {
        return personId;
    }

    public long getTaskTypeId() {
        return taskTypeId;
    }

    public String getHours() {
        return hours;
    }

    public Date getWorkDate() {
        return workDate;
    }

    public int getSolicitude() {
        return solicitude;
    }

    public String getObservations() {
        return observations;
    }

}
