package com.samsistemas.timesheet.viewmodel;

import android.support.annotation.NonNull;

import com.samsistemas.timesheet.model.Project;

/**
 * @author jonatan.salas
 */
public class ProjectViewModel {
    private long projectId;
    private String projectName;

    /**
     *
     * @param project
     */
    public ProjectViewModel(@NonNull Project project) {
        this.projectId = project.getProjectId();
        this.projectName = project.getName();
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
