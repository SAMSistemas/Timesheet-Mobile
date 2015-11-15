package com.samsistemas.timesheet.viewmodel;

import android.support.annotation.NonNull;

import com.samsistemas.timesheet.entity.ProjectEntity;

/**
 * @author jonatan.salas
 */
public class ProjectViewModel {
    private long projectId;
    private String projectName;

    /**
     *
     * @param projectEntity
     */
    public ProjectViewModel(@NonNull ProjectEntity projectEntity) {
        this.projectId = projectEntity.getProjectId();
        this.projectName = projectEntity.getName();
    }

    public ProjectViewModel setProjectId(long projectId) {
        this.projectId = projectId;
        return this;
    }

    public ProjectViewModel setProjectName(String projectName) {
        this.projectName = projectName;
        return this;
    }

    public long getProjectId() {
        return projectId;
    }

    public String getProjectName() {
        return projectName;
    }
}
