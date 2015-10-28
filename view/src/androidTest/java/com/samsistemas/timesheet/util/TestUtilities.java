package com.samsistemas.timesheet.util;

import com.samsistemas.timesheet.model.Person;
import com.samsistemas.timesheet.model.TaskForPosition;
import com.samsistemas.timesheet.model.TaskType;
import com.samsistemas.timesheet.model.WorkPosition;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jonatan.salas
 */
public class TestUtilities {

    public static Person getPerson() {
        return new Person()
                .setPersonId(0)
                .setName("Jonatan")
                .setLastName("Salas")
                .setUsername("jonisaa")
                .setPassword("jonisaa")
                .setPicture(null)
                .setEnabled(true)
                .setWorkPositionId(1);
    }

    public static WorkPosition getWorkPosition() {
        return new WorkPosition()
                .setWorkPositionId(1)
                .setDescription("Developer");
    }

    public static List<TaskType> getTaskTypeList() {
        List<TaskType> taskTypeList = new ArrayList<>();
        taskTypeList.add(
                new TaskType()
                .setTaskTypeId(1)
                .setName("Programar")
                .setEnabled(true)
        );
        taskTypeList.add(
                new TaskType()
                .setTaskTypeId(2)
                .setName("Testear")
                .setEnabled(true)
        );
        taskTypeList.add(
                new TaskType()
                .setTaskTypeId(3)
                .setName("Diseñar")
                .setEnabled(true)
        );
        taskTypeList.add(
                new TaskType()
                .setTaskTypeId(4)
                .setName("Debuggear")
                .setEnabled(true)
        );

        return taskTypeList;
    }

    public static List<TaskForPosition> getTaskForPositions() {
        List<TaskForPosition> taskForPositionList = new ArrayList<>();
        taskForPositionList.add(
                new TaskForPosition()
                .setWorkPositionId(getWorkPosition().getWorkPositionId())
                .setTaskTypeId(1)
        );
        taskForPositionList.add(
                new TaskForPosition()
                .setWorkPositionId(getWorkPosition().getWorkPositionId())
                .setTaskTypeId(2)
        );
        taskForPositionList.add(
                new TaskForPosition()
                .setWorkPositionId(getWorkPosition().getWorkPositionId())
                .setTaskTypeId(3)
        );
        taskForPositionList.add(
                new TaskForPosition()
                .setWorkPositionId(getWorkPosition().getWorkPositionId())
                .setTaskTypeId(4)
        );

        return taskForPositionList;
    }
}
