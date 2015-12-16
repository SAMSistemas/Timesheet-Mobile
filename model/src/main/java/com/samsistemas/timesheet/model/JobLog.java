package com.samsistemas.timesheet.model;

import java.util.Date;

/**
 * Pojo class representing a Client
 *
 * @author jonatan.salas
 */
public class JobLog {

    /**
     * The id of the JobLog
     */
    private long id;

    /**
     * The project associated to this JobLog
     */
    private Project project;

    /**
     * The person associated to this Person
     */
    private Person person;

    /**
     * The taskType associated to this TaskType
     */
    private TaskType taskType;

    /**
     * The hours dedicated to the JobLog
     */
    private String hours;

    /**
     * The date work of the JobLog
     */
    private Date workDate;

    /**
     * The number of solicitude for this JobLog
     */
    private int solicitude;

    /**
     * The observations of the JobLog
     */
    private String observations;

    /**
     * Public constructor
     */
    public JobLog() { }

    /**
     * Setter as builder pattern
     *
     * @param id the id of the JobLog
     * @return a JobLog object
     */
    public JobLog setId(long id) {
        this.id = id;
        return this;
    }

    /**
     * Setter as builder pattern
     *
     * @param project the project associated to this JobLog
     * @return a JobLog object
     */
    public JobLog setProject(Project project) {
        this.project = project;
        return this;
    }

    /**
     * Setter as builder pattern
     *
     * @param person the person associated to this JobLog
     * @return a JobLog object
     */
    public JobLog setPerson(Person person) {
        this.person = person;
        return this;
    }

    /**
     * Setter as builder pattern
     *
     * @param taskType the task type associated to this JobLog
     * @return a JobLog object
     */
    public JobLog setTaskType(TaskType taskType) {
        this.taskType = taskType;
        return this;
    }

    /**
     * Setter as builder pattern
     *
     * @param hours the hours dedicated to this JobLog
     * @return a JobLog object
     */
    public JobLog setHours(String hours) {
        this.hours = hours;
        return this;
    }

    /**
     * Setter as builder pattern
     *
     * @param workDate the date of work
     * @return a JobLog object
     */
    public JobLog setWorkDate(Date workDate) {
        this.workDate = workDate;
        return this;
    }

    /**
     * Setter as builder pattern
     *
     * @param solicitude the solicitude number associated to this JobLog
     * @return a JobLog object
     */
    public JobLog setSolicitude(int solicitude) {
        this.solicitude = solicitude;
        return this;
    }

    /**
     * Setter as builder pattern
     *
     * @param observations the observations of the JobLog
     * @return a JobLog object
     */
    public JobLog setObservations(String observations) {
        this.observations = observations;
        return this;
    }

    /**
     * Getter for id
     *
     * @return the value of the id field
     */
    public long getId() {
        return id;
    }

    /**
     * Getter for project
     *
     * @return the value of the project field
     */
    public Project getProject() {
        return project;
    }

    /**
     * Getter for person
     *
     * @return the value of the person field
     */
    public Person getPerson() {
        return person;
    }

    /**
     * Getter for task type
     *
     * @return the value of the taskType field
     */
    public TaskType getTaskType() {
        return taskType;
    }

    /**
     * Getter for hours
     *
     * @return the value of the hours field
     */
    public String getHours() {
        return hours;
    }

    /**
     * Getter for workDate
     *
     * @return the value of the workDate field
     */
    public Date getWorkDate() {
        return workDate;
    }

    /**
     * Getter for solicitude
     *
     * @return the value of the solicitude field
     */
    public int getSolicitude() {
        return solicitude;
    }

    /**
     * Getter for observations
     *
     * @return the value of the observations field
     */
    public String getObservations() {
        return observations;
    }
}
