package com.samsistemas.timesheet.util;

import android.content.ContentValues;
import android.database.Cursor;

import com.samsistemas.timesheet.entity.ClientEntity;
import com.samsistemas.timesheet.entity.JobLogEntity;
import com.samsistemas.timesheet.entity.PersonEntity;
import com.samsistemas.timesheet.entity.ProjectEntity;
import com.samsistemas.timesheet.entity.TaskTypeEntity;
import com.samsistemas.timesheet.entity.WorkPositionEntity;
import com.samsistemas.timesheet.factory.MapperFactory;

import junit.framework.Assert;

import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 * @author jonatan.salas
 */
public class TestUtilities {

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

    public static ContentValues getClientValues() {
        return MapperFactory.getClientMapper().asContentValues(getClient());
    }

    public static ContentValues getWorkPositionValues() {
        return MapperFactory.getWorkPositionMapper().asContentValues(getWorkPosition());
    }

    public static ContentValues getPersonValues(long id) {
        return MapperFactory.getPersonMapper().asContentValues(getPerson(id));
    }

    public static ContentValues getTaskTypeValues() {
        return MapperFactory.getTaskTypeMapper().asContentValues(getTaskType());
    }

    public static ContentValues getProjectValues(long clientId) {
        return MapperFactory.getProjectMapper().asContentValues(getProject(clientId));
    }

    public static ContentValues getJobLogValues(long projectId, long personId, long taskTypeId) {
        return MapperFactory.getJoblogMapper().asContentValues(getJobLog(projectId, personId, taskTypeId));
    }

    public static ClientEntity getClient() {
        ClientEntity entity = new ClientEntity();
        entity.setId(1);
        entity.setName("fulanito")
              .setShortName("fn")
              .setEnabled(true);

        return entity;
    }

    public static WorkPositionEntity getWorkPosition() {
        WorkPositionEntity entity = new WorkPositionEntity();

        entity.setId(0);
        entity.setDescription("Developer");

        return entity;
    }

    public static PersonEntity getPerson(long workPositionId) {
        PersonEntity entity = new PersonEntity();

        entity.setId(1);
        entity.setName("Jonatan")
                .setLastName("Salas")
                .setUsername("JONATANS")
                .setPassword("JONATANS")
                .setWorkPositionId(workPositionId)
                .setWorkHours(6)
                .setPicture(null)
                .setEnabled(true);

        return entity;
    }

    public static TaskTypeEntity getTaskType() {
        TaskTypeEntity entity = new TaskTypeEntity();

        entity.setId(1);
        entity.setName("Programación")
              .setEnabled(true);

        return entity;
    }

    public static ProjectEntity getProject(long clientId) {
        ProjectEntity entity = new ProjectEntity();

        entity.setId(1);
        entity.setClientId(clientId)
              .setName("Timesheet - carga de horas")
              .setShortName("cdh")
              .setStartDate(new Date(System.currentTimeMillis()))
              .setEnabled(true);

        return entity;
    }

    public static JobLogEntity getJobLog(long projectId, long personId, long taskTypeId) {
        JobLogEntity entity = new JobLogEntity();

        entity.setId(1);
        entity.setProjectId(projectId)
              .setPersonId(personId)
              .setTaskTypeId(taskTypeId)
              .setWorkDate(new Date(System.currentTimeMillis()))
              .setHours("6")
              .setSolicitude(33034)
              .setObservations("lalala");

        return entity;
    }
}

