package com.samsistemas.timesheet.util;

import com.samsistemas.timesheet.entity.ClientEntity;
import com.samsistemas.timesheet.entity.JobLogEntity;
import com.samsistemas.timesheet.entity.PersonEntity;
import com.samsistemas.timesheet.entity.ProjectEntity;
import com.samsistemas.timesheet.entity.TaskTypeEntity;
import com.samsistemas.timesheet.entity.WorkPositionEntity;

import junit.framework.Assert;

/**
 * @author jonatan.salas
 */
public class AssertUtilities {

    public static void compareClient(ClientEntity expected, ClientEntity clientEntity) {
        Assert.assertEquals(expected.getClientId(), clientEntity.getClientId());
        Assert.assertEquals(expected.getName(), clientEntity.getName());
        Assert.assertEquals(expected.getShortName(), clientEntity.getShortName());
        Assert.assertEquals(expected.isEnabled(), clientEntity.isEnabled());
    }

    public static void compareWorkPosition(WorkPositionEntity expected, WorkPositionEntity workPositionEntity) {
        Assert.assertEquals(expected.getWorkPositionId(), workPositionEntity.getWorkPositionId());
        Assert.assertEquals(expected.getDescription(), workPositionEntity.getDescription());
    }

    public static void comparePerson(PersonEntity expected, PersonEntity personEntity) {
        Assert.assertEquals(expected.getPersonId(), personEntity.getPersonId());
        Assert.assertEquals(expected.getName(), personEntity.getName());
        Assert.assertEquals(expected.getLastName(), personEntity.getLastName());
        Assert.assertEquals(expected.getUsername(), personEntity.getUsername());
        Assert.assertEquals(expected.getPassword(), personEntity.getPassword());
        Assert.assertEquals(expected.getWorkPositionId(), personEntity.getWorkPositionId());
        Assert.assertEquals(expected.getPicture(), personEntity.getPicture());
    }

    public static void compareTaskType(TaskTypeEntity expected, TaskTypeEntity taskTypeEntity) {
        Assert.assertEquals(expected.getTaskTypeId(), taskTypeEntity.getTaskTypeId());
        Assert.assertEquals(expected.getName(), taskTypeEntity.getName());
    }

    public static void compareProject(ProjectEntity expected, ProjectEntity projectEntity) {
        Assert.assertEquals(expected.getProjectId(), projectEntity.getProjectId());
        Assert.assertEquals(expected.getClientId(), projectEntity.getClientId());
        Assert.assertEquals(expected.getName(), projectEntity.getName());
        Assert.assertEquals(expected.getShortName(), projectEntity.getShortName());
        Assert.assertEquals(expected.getStartDate(), projectEntity.getStartDate());
    }

    public static void compareJobLog(JobLogEntity expected, JobLogEntity jobLogEntity) {
        Assert.assertEquals(expected.getJobLogId(), jobLogEntity.getJobLogId());
        Assert.assertEquals(expected.getProjectId(), jobLogEntity.getProjectId());
        Assert.assertEquals(expected.getPersonId(), jobLogEntity.getPersonId());
        Assert.assertEquals(expected.getTaskTypeId(), jobLogEntity.getTaskTypeId());
        Assert.assertEquals(expected.getHours(), jobLogEntity.getHours());
        Assert.assertEquals(expected.getObservations(), jobLogEntity.getObservations());
        Assert.assertEquals(expected.getSolicitude(), jobLogEntity.getSolicitude());
        Assert.assertEquals(expected.getWorkDate(), jobLogEntity.getWorkDate());
    }
}
