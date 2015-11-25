package com.samsistemas.timesheet.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;

import com.samsistemas.timesheet.entity.ClientEntity;
import com.samsistemas.timesheet.entity.JobLogEntity;
import com.samsistemas.timesheet.entity.PersonEntity;
import com.samsistemas.timesheet.entity.ProjectEntity;
import com.samsistemas.timesheet.entity.TaskTypeEntity;
import com.samsistemas.timesheet.entity.WorkPositionEntity;
import com.samsistemas.timesheet.mapper.ClientEntityMapper;
import com.samsistemas.timesheet.mapper.JobLogEntityMapper;
import com.samsistemas.timesheet.mapper.PersonEntityMapper;
import com.samsistemas.timesheet.mapper.ProjectEntityMapper;
import com.samsistemas.timesheet.mapper.TaskTypeEntityMapper;
import com.samsistemas.timesheet.mapper.WorkPositionEntityMapper;
import com.samsistemas.timesheet.mapper.base.EntityMapper;

import junit.framework.Assert;

import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 * @author jonatan.salas
 */
public class TestUtilities {

    /**
     *
     * @param expectedValues
     * @param valueCursor
     */
    public static void validateCursor(ContentValues expectedValues, Cursor valueCursor) {
        Set<Map.Entry<String, Object>> valueSet = expectedValues.valueSet();

        for(Map.Entry<String, Object> entry: valueSet) {

            String columnName = entry.getKey();
            int idx = valueCursor.getColumnIndex(columnName);

            Assert.assertFalse(-1 == idx);
            Object value = entry.getValue();

            //Use this check to avoid null pointer troubleshooting when trying to get
            //the picture from Person Table that could be null.
            if(null != value) {
                String expectedValue = entry.getValue().toString();
                Assert.assertEquals(expectedValue, valueCursor.getString(idx));
            } else {
                byte[] expected = (byte[]) entry.getValue();
                Assert.assertEquals(expected, valueCursor.getBlob(idx));
            }
        }
    }

    /**
     * @param context
     * @return
     */
    public static ContentValues getClient(@NonNull Context context) {
        final EntityMapper<ClientEntity, Cursor> mapper = new ClientEntityMapper();
        return mapper.asContentValues(
                new ClientEntity()
                .setClientId(1)
                .setName("fulanito")
                .setShortName("fn")
                .setEnabled(true)
        );
    }

    /**
     * @param context
     * @return
     */
    public static ContentValues getWorkPosition(@NonNull Context context) {
        final EntityMapper<WorkPositionEntity, Cursor> mapper = new WorkPositionEntityMapper();
        return mapper.asContentValues(
                new WorkPositionEntity()
                .setWorkPositionId(1)
                .setDescription("Developer")
        );
    }

    /**
     * @param context
     * @return
     */
    public static ContentValues getPerson(@NonNull Context context, long id) {
        final EntityMapper<PersonEntity, Cursor> mapper = new PersonEntityMapper();
        return mapper.asContentValues(
                new PersonEntity()
                .setPersonId(1)
                .setName("Jonatan")
                .setLastName("Salas")
                .setUsername("JONATANS")
                .setPassword("JONATANS")
                .setWorkPositionId(id)
                .setWorkHours(6)
                .setPicture(null)
                .setEnabled(true)
        );
    }

    /**
     * @param context
     * @return
     */
    public static ContentValues getTaskType(@NonNull Context context) {
        final EntityMapper<TaskTypeEntity, Cursor> mapper = new TaskTypeEntityMapper();
        return mapper.asContentValues(
                new TaskTypeEntity()
                .setTaskTypeId(1)
                .setName("Programación")
                .setEnabled(true)
        );
    }

    /**
     * @param context
     * @param clientId
     * @return
     */
    public static ContentValues getProject(@NonNull Context context, long clientId) {
        final EntityMapper<ProjectEntity, Cursor> mapper = new ProjectEntityMapper();
        return  mapper.asContentValues(
                new ProjectEntity()
                .setProjectId(1)
                .setClientId(clientId)
                .setName("Timesheet - carga de horas")
                .setShortName("cdh")
                .setStartDate(new Date(System.currentTimeMillis()))
                .setEnabled(true)
        );
    }

    /**
     * @param context
     * @param projectId
     * @param personId
     * @param taskTypeId
     * @return
     */
    public static ContentValues getJobLog(@NonNull Context context, long projectId, long personId, long taskTypeId) {
        final EntityMapper<JobLogEntity, Cursor> mapper = new JobLogEntityMapper();
        return  mapper.asContentValues(
                new JobLogEntity()
                .setJobLogId(1)
                .setProjectId(projectId)
                .setPersonId(personId)
                .setTaskTypeId(taskTypeId)
                .setWorkDate(new Date(System.currentTimeMillis()))
                .setHours("6")
                .setSolicitude(33034)
                .setObservations("lalala")
        );
    }

    /**
     * @return
     */
    public static ClientEntity getClient() {
        return new ClientEntity()
                .setClientId(1)
                .setName("fulanito")
                .setShortName("fn")
                .setEnabled(true);
    }

    /**
     *
     * @return
     */
    public static WorkPositionEntity getWorkPosition() {
        return new WorkPositionEntity()
                .setWorkPositionId(0)
                .setDescription("Developer");
    }

    /**
     *
     * @return
     */
    public static PersonEntity getPerson(long workPositionId) {
        return new PersonEntity()
                .setPersonId(1)
                .setName("Jonatan")
                .setLastName("Salas")
                .setUsername("JONATANS")
                .setPassword("JONATANS")
                .setWorkPositionId(workPositionId)
                .setWorkHours(6)
                .setPicture(null)
                .setEnabled(true);
    }

    /**
     *
     * @return
     */
    public static TaskTypeEntity getTaskType() {
        return new TaskTypeEntity()
                .setTaskTypeId(1)
                .setName("Programación")
                .setEnabled(true);
    }

    /**
     *
     * @param clientId
     * @return
     */
    public static ProjectEntity getProject(long clientId) {
        return new ProjectEntity()
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
    public static JobLogEntity getJobLog(long projectId, long personId, long taskTypeId) {
        return new JobLogEntity()
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

