package com.samsistemas.timesheet.util;

import com.samsistemas.timesheet.entity.PersonEntity;
import com.samsistemas.timesheet.entity.TaskTypeEntity;
import com.samsistemas.timesheet.entity.WorkPositionEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jonatan.salas
 */
public class TestUtilities {

    public static PersonEntity getPerson() {
        return new PersonEntity()
                .setPersonId(0)
                .setName("Jonatan")
                .setLastName("Salas")
                .setUsername("jonisaa")
                .setPassword("jonisaa")
                .setPicture(null)
                .setEnabled(true)
                .setWorkPositionId(1);
    }

    public static WorkPositionEntity getWorkPosition() {
        return new WorkPositionEntity()
                .setWorkPositionId(1)
                .setDescription("Developer");
    }

    public static List<TaskTypeEntity> getTaskTypeList() {
        List<TaskTypeEntity> taskTypeEntityList = new ArrayList<>();
        taskTypeEntityList.add(
                new TaskTypeEntity()
                .setTaskTypeId(1)
                .setName("Programar")
                .setEnabled(true)
        );
        taskTypeEntityList.add(
                new TaskTypeEntity()
                .setTaskTypeId(2)
                .setName("Testear")
                .setEnabled(true)
        );
        taskTypeEntityList.add(
                new TaskTypeEntity()
                .setTaskTypeId(3)
                .setName("Diseñar")
                .setEnabled(true)
        );
        taskTypeEntityList.add(
                new TaskTypeEntity()
                .setTaskTypeId(4)
                .setName("Debuggear")
                .setEnabled(true)
        );

        return taskTypeEntityList;
    }
}
