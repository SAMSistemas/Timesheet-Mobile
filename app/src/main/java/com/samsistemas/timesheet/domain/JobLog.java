package com.samsistemas.timesheet.domain;

import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;
import com.orm.dsl.NotNull;

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
    @NotNull
    @SerializedName("id")
    private Long serverId;

    /**
     * The project associated to this JobLog
     */
    @NotNull
    @SerializedName("project")
    private Project project;

    /**
     * The person associated to this Person
     */
    @NotNull
    @SerializedName("person")
    private Person person;

    /**
     * The taskType associated to this TaskType
     */
    @NotNull
    @SerializedName("task_type")
    private TaskType taskType;

    /**
     * The hours dedicated to the JobLog
     */
    @NotNull
    @SerializedName("hours")
    private String hours;

    /**
     * The date work of the JobLog
     */
    @NotNull
    @SerializedName("date")
    private Date workDate;

    /**
     * The number of solicitude for this JobLog
     */
    @NotNull
    @SerializedName("solicitude")
    private int solicitude;

    /**
     * The observations of the JobLog
     */
    @NotNull
    @SerializedName("observation")
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
