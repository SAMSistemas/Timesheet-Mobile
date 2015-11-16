package com.samsistemas.timesheet.model;

import java.util.Date;

/**
 * @author jonatan.salas
 */
public class JobLog {
    private long id;
    private Project project;
    private Person person;
    private TaskType taskType;
    private String hours;
    private Date workDate;
    private int solicitude;
    private String observations;

    public JobLog() {}

    public JobLog setId(long id) {
        this.id = id;
        return this;
    }

    public JobLog setProject(Project project) {
        this.project = project;
        return this;
    }

    public JobLog setPerson(Person person) {
        this.person = person;
        return this;
    }

    public JobLog setTaskType(TaskType taskType) {
        this.taskType = taskType;
        return this;
    }

    public JobLog setHours(String hours) {
        this.hours = hours;
        return this;
    }

    public JobLog setWorkDate(Date workDate) {
        this.workDate = workDate;
        return this;
    }

    public JobLog setSolicitude(int solicitude) {
        this.solicitude = solicitude;
        return this;
    }

    public JobLog setObservations(String observations) {
        this.observations = observations;
        return this;
    }

    public long getId() {
        return id;
    }

    public Project getProject() {
        return project;
    }

    public Person getPerson() {
        return person;
    }

    public TaskType getTaskType() {
        return taskType;
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
