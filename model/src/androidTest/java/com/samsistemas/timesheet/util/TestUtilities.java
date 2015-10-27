package com.samsistemas.timesheet.util;

import android.content.ContentValues;
import android.content.Context;
import android.support.annotation.NonNull;

import com.samsistemas.timesheet.model.Client;
import com.samsistemas.timesheet.model.JobLog;
import com.samsistemas.timesheet.model.Person;
import com.samsistemas.timesheet.model.Project;
import com.samsistemas.timesheet.model.TaskForPosition;
import com.samsistemas.timesheet.model.TaskType;
import com.samsistemas.timesheet.model.WorkPosition;

import junit.framework.Assert;

import java.util.Date;

/**
 * @author jonatan.salas
 */
public class TestUtilities {

    /**
     * @param context
     * @return
     */
    public static ContentValues getClient(@NonNull Context context) {
        return new Client()
                .setClientId(1)
                .setName("fulanito")
                .setShortName("fn")
                .setEnabled(true)
                .asContentValues(context);
    }

    /**
     * @param context
     * @return
     */
    public static ContentValues getWorkPosition(@NonNull Context context) {
        return new WorkPosition()
                .setWorkPositionId(1)
                .setDescription("Developer")
                .asContentValues(context);
    }

    /**
     * @param context
     * @return
     */
    public static ContentValues getPerson(@NonNull Context context, long id) {
        return new Person()
                .setPersonId(1)
                .setName("Jonatan")
                .setLastName("Salas")
                .setUsername("JONATANS")
                .setPassword("JONATANS")
                .setWorkPositionId(id)
                .setPicture(null)
                .setEnabled(true)
                .asContentValues(context);
    }

    /**
     * @param context
     * @return
     */
    public static ContentValues getTaskType(@NonNull Context context) {
        return new TaskType()
                .setTaskTypeId(1)
                .setName("Programación")
                .setEnabled(true)
                .asContentValues(context);
    }

    /**
     * @param context
     * @return
     */
    public static ContentValues getTaskForPosition(@NonNull Context context) {
        return new TaskForPosition()
                .setWorkPositionId(1)
                .setTaskTypeId(1)
                .asContentValues(context);
    }

    /**
     * @param context
     * @param clientId
     * @return
     */
    public static ContentValues getProject(@NonNull Context context, long clientId) {
        return new Project()
                .setProjectId(1)
                .setClientId(clientId)
                .setName("Timesheet - carga de horas")
                .setShortName("cdh")
                .setStartDate(new Date(System.currentTimeMillis()))
                .setEnabled(true)
                .asContentValues(context);
    }

    /**
     * @param context
     * @param projectId
     * @param personId
     * @param taskTypeId
     * @return
     */
    public static ContentValues getJobLog(@NonNull Context context, long projectId, long personId, long taskTypeId) {
        return new JobLog()
                .setJobLogId(1)
                .setProjectId(projectId)
                .setPersonId(personId)
                .setTaskTypeId(taskTypeId)
                .setWorkDate(new Date(System.currentTimeMillis()))
                .setHours("6")
                .setSolicitude(33034)
                .setObservations("lalala")
                .asContentValues(context);
    }

    /**
     * @return
     */
    public static Client getClient() {
        return new Client()
                .setClientId(1)
                .setName("fulanito")
                .setShortName("fn")
                .setEnabled(true);
    }

    /**
     *
     * @return
     */
    public static WorkPosition getWorkPosition() {
        return new WorkPosition()
                .setWorkPositionId(0)
                .setDescription("Developer");
    }

    /**
     *
     * @return
     */
    public static Person getPerson(long workPositionId) {
        return new Person()
                .setPersonId(1)
                .setName("Jonatan")
                .setLastName("Salas")
                .setUsername("JONATANS")
                .setPassword("JONATANS")
                .setWorkPositionId(workPositionId)
                .setPicture(null)
                .setEnabled(true);
    }

    /**
     *
     * @return
     */
    public static TaskType getTaskType() {
        return new TaskType()
                .setTaskTypeId(1)
                .setName("Programación")
                .setEnabled(true);
    }

    /**
     *
     * @return
     */
    public static TaskForPosition getTaskForPosition() {
        return new TaskForPosition()
                .setWorkPositionId(1)
                .setTaskTypeId(1);
    }

    /**
     *
     * @param clientId
     * @return
     */
    public static Project getProject(long clientId) {
        return new Project()
                .setProjectId(1)
                .setClientId(clientId)
                .setName("Timesheet - carga de horas")
                .setShortName("cdh")
                .setStartDate(new Date(System.currentTimeMillis()))
                .setEnabled(true);
    }

    /**
     *
     * @param projectId
     * @param personId
     * @param taskTypeId
     * @return
     */
    public static JobLog getJobLog(long projectId, long personId, long taskTypeId) {
        return new JobLog()
                .setJobLogId(1)
                .setProjectId(projectId)
                .setPersonId(personId)
                .setTaskTypeId(taskTypeId)
                .setWorkDate(new Date(System.currentTimeMillis()))
                .setHours("6")
                .setSolicitude(33034)
                .setObservations("lalala");
    }
}

