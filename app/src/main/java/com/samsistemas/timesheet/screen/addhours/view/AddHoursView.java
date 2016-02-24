package com.samsistemas.timesheet.screen.addhours.view;

import android.support.annotation.Nullable;

import com.samsistemas.timesheet.domain.Client;
import com.samsistemas.timesheet.domain.Project;
import com.samsistemas.timesheet.domain.TaskType;

import java.util.List;

/**
 * @author jonatan.salas
 */
public interface AddHoursView {

    void loadTaskTypeData(@Nullable List<TaskType> taskTypeList);

    void loadClientsData(@Nullable List<Client> clientList);

    void loadProjectsData(@Nullable List<Project> projectList);
}
