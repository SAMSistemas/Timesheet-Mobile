package com.samsistemas.timesheet.util;

import com.samsistemas.timesheet.model.Client;
import com.samsistemas.timesheet.model.JobLog;
import com.samsistemas.timesheet.model.Person;
import com.samsistemas.timesheet.model.Project;
import com.samsistemas.timesheet.model.TaskForPosition;
import com.samsistemas.timesheet.model.TaskType;
import com.samsistemas.timesheet.model.WorkPosition;

import junit.framework.Assert;

/**
 * @author jonatan.salas
 */
public class AssertUtilities {

    public static void compareClient(Client expected, Client client) {
        Assert.assertEquals(expected.getClientId(), client.getClientId());
        Assert.assertEquals(expected.getName(), client.getName());
        Assert.assertEquals(expected.getShortName(), client.getShortName());
        Assert.assertEquals(expected.isEnabled(), client.isEnabled());
    }

    public static void compareWorkPosition(WorkPosition expected, WorkPosition workPosition) {
        Assert.assertEquals(expected.getWorkPositionId(), workPosition.getWorkPositionId());
        Assert.assertEquals(expected.getDescription(), workPosition.getDescription());
    }

    public static void comparePerson(Person expected, Person person) {
        Assert.assertEquals(expected.getPersonId(), person.getPersonId());
        Assert.assertEquals(expected.getName(), person.getName());
        Assert.assertEquals(expected.getLastName(), person.getLastName());
        Assert.assertEquals(expected.getUsername(), person.getUsername());
        Assert.assertEquals(expected.getPassword(), person.getPassword());
        Assert.assertEquals(expected.getWorkPositionId(), person.getWorkPositionId());
        Assert.assertEquals(expected.getPicture(), person.getPicture());
    }

    public static void compareTaskType(TaskType expected, TaskType taskType) {
        Assert.assertEquals(expected.getTaskTypeId(), taskType.getTaskTypeId());
        Assert.assertEquals(expected.getName(), taskType.getName());
    }

    public static void compareTaskForPosition(TaskForPosition expected, TaskForPosition taskForPosition) {
        Assert.assertEquals(expected.getTaskTypeId(), taskForPosition.getTaskTypeId());
        Assert.assertEquals(expected.getWorkPositionId(), taskForPosition.getWorkPositionId());
    }

    public static void compareProject(Project expected, Project project) {
        Assert.assertEquals(expected.getProjectId(), project.getProjectId());
        Assert.assertEquals(expected.getClientId(), project.getClientId());
        Assert.assertEquals(expected.getName(), project.getName());
        Assert.assertEquals(expected.getShortName(), project.getShortName());
        Assert.assertEquals(expected.getStartDate(), project.getStartDate());
    }

    public static void compareJobLog(JobLog expected, JobLog jobLog) {
        Assert.assertEquals(expected.getJobLogId(), jobLog.getJobLogId());
        Assert.assertEquals(expected.getProjectId(), jobLog.getProjectId());
        Assert.assertEquals(expected.getPersonId(), jobLog.getPersonId());
        Assert.assertEquals(expected.getTaskTypeId(), jobLog.getTaskTypeId());
        Assert.assertEquals(expected.getHours(), jobLog.getHours());
        Assert.assertEquals(expected.getObservations(), jobLog.getObservations());
        Assert.assertEquals(expected.getSolicitude(), jobLog.getSolicitude());
        Assert.assertEquals(expected.getWorkDate(), jobLog.getWorkDate());
    }
}
