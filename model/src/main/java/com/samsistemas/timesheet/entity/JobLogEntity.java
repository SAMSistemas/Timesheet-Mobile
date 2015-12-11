package com.samsistemas.timesheet.entity;

import com.samsistemas.timesheet.entity.base.Entity;

import java.util.Date;

/**
 * Entity that represents a JobLog.
 *
 * @author jonatan.salas
 */
public class JobLogEntity extends Entity {

    /**
     * The id of the project to reference
     */
    private long projectId;

    /**
     * The id of the person to reference
     */
    private long personId;

    /**
     * The id of the taskType to reference
     */
    private long taskTypeId;

    /**
     * The hours dedicated to some task
     */
    private String hours;

    /**
     * The date when you work on some task
     */
    private Date workDate;

    /**
     * The solicitude number of the task you worked on
     */
    private int solicitude;

    /**
     * The observations associated to the task.
     */
    private String observations;

    /**
     * Default Constructor.
     */
    public JobLogEntity() { }

    /**
     * ProjectId setter as builder pattern
     *
     * @param projectId the id of the project to reference
     * @return a JobLogEntity object
     */
    public JobLogEntity setProjectId(long projectId) {
        this.projectId = projectId;
        return this;
    }

    /**
     * PersonId setter as builder pattern
     *
     * @param personId the id of the person to reference
     * @return a JobLogEntity object
     */
    public JobLogEntity setPersonId(long personId) {
        this.personId = personId;
        return this;
    }

    /**
     * TaskTypeId setter as builder pattern
     *
     * @param taskTypeId the id of the tasktype to reference
     * @return a JobLogEntity object
     */
    public JobLogEntity setTaskTypeId(long taskTypeId) {
        this.taskTypeId = taskTypeId;
        return this;
    }

    /**
     * Hours setter as builder pattern
     *
     * @param hours the hours dedicated to some task
     * @return a JobLogEntity object
     */
    public JobLogEntity setHours(String hours) {
        this.hours = hours;
        return this;
    }

    /**
     * WorkDate setter as builder pattern
     *
     * @param workDate the date when you work on some task
     * @return a JobLogEntity object
     */
    public JobLogEntity setWorkDate(Date workDate) {
        this.workDate = workDate;
        return this;
    }

    /**
     * Solicitude setter as builder pattern
     *
     * @param solicitude number of solicitude for the task you worked on
     * @return a JobLogEntity object
     */
    public JobLogEntity setSolicitude(int solicitude) {
        this.solicitude = solicitude;
        return this;
    }

    /**
     * Observation setter as builder pattern
     *
     * @param observations the observations to some task you have done.
     * @return a JobLogEntity object
     */
    public JobLogEntity setObservations(String observations) {
        this.observations = observations;
        return this;
    }

    /**
     * ProjectId getter
     *
     * @return the value of projectId
     */
    public long getProjectId() {
        return projectId;
    }

    /**
     * PersonId getter
     *
     * @return the value of personId
     */
    public long getPersonId() {
        return personId;
    }

    /**
     * TaskTypeId getter
     *
     * @return the value of taskTypeId
     */
    public long getTaskTypeId() {
        return taskTypeId;
    }

    /**
     * Hours getter
     *
     * @return the value of hours
     */
    public String getHours() {
        return hours;
    }

    /**
     * WorkDate getter
     *
     * @return the value of workDate
     */
    public Date getWorkDate() {
        return workDate;
    }

    /**
     * Solicitude getter
     *
     * @return the value of solicitude
     */
    public int getSolicitude() {
        return solicitude;
    }

    /**
     * Observations getter
     *
     * @return the value of observations
     */
    public String getObservations() {
        return observations;
    }

}
