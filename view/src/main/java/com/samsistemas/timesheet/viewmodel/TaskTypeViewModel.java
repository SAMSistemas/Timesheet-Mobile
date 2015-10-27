package com.samsistemas.timesheet.viewmodel;

import android.support.annotation.NonNull;

import com.samsistemas.timesheet.model.TaskType;

/**
 * @author jonatan.salas
 */
public class TaskTypeViewModel {
    private long taskTypeId;
    private String taskTypeName;

    /**
     *
     * @param taskType
     */
    public TaskTypeViewModel(@NonNull TaskType taskType) {
        this.taskTypeId = taskType.getTaskTypeId();
        this.taskTypeName = taskType.getName();
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
