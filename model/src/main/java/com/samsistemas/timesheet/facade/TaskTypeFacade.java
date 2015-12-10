package com.samsistemas.timesheet.facade;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.samsistemas.timesheet.controller.Controller;
import com.samsistemas.timesheet.entity.TaskTypeEntity;
import com.samsistemas.timesheet.facade.base.Facade;
import com.samsistemas.timesheet.factory.ControllerFactory;
import com.samsistemas.timesheet.helper.UriHelper;
import com.samsistemas.timesheet.model.TaskType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jonatan.salas
 */
public class TaskTypeFacade implements Facade<TaskType> {
    private static TaskTypeFacade instance = null;
    private final Controller<TaskTypeEntity> taskTypeController;

    private TaskTypeFacade() {
        this.taskTypeController = ControllerFactory.getTaskTypeController();
    }

    @Override
    public TaskType findById(@NonNull Context context, long id) {
        final Uri uri = UriHelper.buildTaskTypeUriWithId(context, id);
        final TaskTypeEntity entity = taskTypeController.get(context, uri);
        final TaskType taskType = new TaskType();

        taskType.setId(entity.getId())
                .setName(entity.getName())
                .setEnabled(entity.isEnabled());

        return taskType;
    }

    @Override
    public List<TaskType> findAll(@NonNull Context context) {
        final Uri uri = UriHelper.buildTaskTypeUri(context);
        final List<TaskTypeEntity> taskTypeEntities = taskTypeController.listAll(context, uri);
        List<TaskType> taskTypes = new ArrayList<>(taskTypeEntities.size());
        TaskTypeEntity entity;

        for (int i = 0; i < taskTypeEntities.size(); i++) {
            entity = taskTypeEntities.get(i);

            TaskType taskType = new TaskType();
            taskType.setId(entity.getId())
                    .setName(entity.getName())
                    .setEnabled(entity.isEnabled());

            taskTypes.add(taskType);
        }

        return taskTypes;
    }

    @Override
    public boolean insert(@NonNull Context context, TaskType taskType) {
        final Uri uri = UriHelper.buildTaskTypeUri(context);
        final TaskTypeEntity entity = new TaskTypeEntity();

        entity.setId(taskType.getId());
        entity.setName(taskType.getName())
              .setEnabled(taskType.isEnabled());

        return taskTypeController.insert(context, entity, uri);
    }

    @Override
    public boolean update(@NonNull Context context, TaskType taskType) {
        final Uri uri = UriHelper.buildTaskTypeUri(context);
        final TaskTypeEntity entity = new TaskTypeEntity();

        entity.setId(taskType.getId());
        entity.setName(taskType.getName())
              .setEnabled(taskType.isEnabled());

        return taskTypeController.update(context, entity, uri);
    }

    @Override
    public boolean deleteById(@NonNull Context context, long id) {
        final Uri uri = UriHelper.buildTaskTypeUri(context);
        return taskTypeController.delete(context, uri, id);
    }

    public static TaskTypeFacade newInstance() {
        if (null == instance) {
            instance = new TaskTypeFacade();
        }
        return instance;
    }
}
