package com.samsistemas.timesheet.model;

import java.util.Date;

/**
 * @author jonatan.salas
 */
public class JobLog {

    /**
     *
     */
    private long id;

    /**
     *
     */
    private Project project;

    /**
     *
     */
    private Person person;

    /**
     *
     */
    private TaskType taskType;

    /**
     *
     */
    private String hours;

    /**
     *
     */
    private Date workDate;

    /**
     *
     */
    private int solicitude;

    /**
     *
     */
    private String observations;

    /**
     *
     */
    public JobLog() { }

    /**
     *
     * @param id
     * @return
     */
    public JobLog setId(long id) {
        this.id = id;
        return this;
    }

    /**
     *
     * @param project
     * @return
     */
    public JobLog setProject(Project project) {
        this.project = project;
        return this;
    }

    /**
     *
     * @param person
     * @return
     */
    public JobLog setPerson(Person person) {
        this.person = person;
        return this;
    }

    /**
     *
     * @param taskType
     * @return
     */
    public JobLog setTaskType(TaskType taskType) {
        this.taskType = taskType;
        return this;
    }

    /**
     *
     * @param hours
     * @return
     */
    public JobLog setHours(String hours) {
        this.hours = hours;
        return this;
    }

    /**
     *
     * @param workDate
     * @return
     */
    public JobLog setWorkDate(Date workDate) {
        this.workDate = workDate;
        return this;
    }

    /**
     *
     * @param solicitude
     * @return
     */
    public JobLog setSolicitude(int solicitude) {
        this.solicitude = solicitude;
        return this;
    }

    /**
     *
     * @param observations
     * @return
     */
    public JobLog setObservations(String observations) {
        this.observations = observations;
        return this;
    }

    /**
     *
     * @return
     */
    public long getId() {
        return id;
    }

    /**
     *
     * @return
     */
    public Project getProject() {
        return project;
    }

    /**
     *
     * @return
     */
    public Person getPerson() {
        return person;
    }

    /**
     *
     * @return
     */
    public TaskType getTaskType() {
        return taskType;
    }

    /**
     *
     * @return
     */
    public String getHours() {
        return hours;
    }

    /**
     *
     * @return
     */
    public Date getWorkDate() {
        return workDate;
    }

    /**
     *
     * @return
     */
    public int getSolicitude() {
        return solicitude;
    }

    /**
     *
     * @return
     */
    public String getObservations() {
        return observations;
    }
}
