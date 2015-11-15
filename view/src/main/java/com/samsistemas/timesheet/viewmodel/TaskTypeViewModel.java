package com.samsistemas.timesheet.viewmodel;

import android.support.annotation.NonNull;

import com.samsistemas.timesheet.entity.TaskTypeEntity;

/**
 * @author jonatan.salas
 */
public class TaskTypeViewModel {
    private long taskTypeId;
    private String taskTypeName;

    /**
     *
     * @param taskTypeEntity
     */
    public TaskTypeViewModel(@NonNull TaskTypeEntity taskTypeEntity) {
        this.taskTypeId = taskTypeEntity.getTaskTypeId();
        this.taskTypeName = taskTypeEntity.getName();
    }

    public TaskTypeViewModel setTaskTypeId(long taskTypeId) {
        this.taskTypeId = taskTypeId;
        return this;
    }

    public TaskTypeViewModel setTaskTypeName(String taskTypeName) {
        this.taskTypeName = taskTypeName;
        return this;
    }

    public long getTaskTypeId() {
        return taskTypeId;
    }

    public String getTaskTypeName() {
        return taskTypeName;
    }
}
