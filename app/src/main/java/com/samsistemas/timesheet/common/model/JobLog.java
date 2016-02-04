package com.samsistemas.timesheet.common.model;

import com.orm.SugarRecord;
import com.orm.dsl.NotNull;
import com.orm.dsl.Unique;

import org.parceler.Parcel;

import java.util.Date;

/**
 * Pojo class representing a Client
 *
 * @author jonatan.salas
 */
@Parcel
public class JobLog extends SugarRecord {

    /**
     * The id of the JobLog stored in the server
     */
    @NotNull @Unique
    private Long serverId;

    /**
     * The project associated to this JobLog
     */
    @NotNull
    private Project project;

    /**
     * The person associated to this Person
     */
    @NotNull
    private Person person;

    /**
     * The taskType associated to this TaskType
     */
    @NotNull
    private TaskType taskType;

    /**
     * The hours dedicated to the JobLog
     */
    @NotNull
    private String hours;

    /**
     * The date work of the JobLog
     */
    @NotNull
    private Date workDate;

    /**
     * The number of solicitude for this JobLog
     */
    @NotNull
    private int solicitude;

    /**
     * The observations of the JobLog
     */
    @NotNull
    private String observations;

    /**
     * Public constructor
     */
    public JobLog() { }

    public Long getServerId() {
        return serverId;
    }

    public JobLog setServerId(Long serverId) {
        this.serverId = serverId;
        return this;
    }

    public Project getProject() {
        return project;
    }

    public JobLog setProject(Project project) {
        this.project = project;
        return this;
    }

    public Person getPerson() {
        return person;
    }

    public JobLog setPerson(Person person) {
        this.person = person;
        return this;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public JobLog setTaskType(TaskType taskType) {
        this.taskType = taskType;
        return this;
    }

    public String getHours() {
        return hours;
    }

    public JobLog setHours(String hours) {
        this.hours = hours;
        return this;
    }

    public Date getWorkDate() {
        return workDate;
    }

    public JobLog setWorkDate(Date workDate) {
        this.workDate = workDate;
        return this;
    }

    public int getSolicitude() {
        return solicitude;
    }

    public JobLog setSolicitude(int solicitude) {
        this.solicitude = solicitude;
        return this;
    }

    public String getObservations() {
        return observations;
    }

    public JobLog setObservations(String observations) {
        this.observations = observations;
        return this;
    }
}
