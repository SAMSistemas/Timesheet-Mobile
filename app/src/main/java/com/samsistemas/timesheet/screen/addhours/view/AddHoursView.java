package com.samsistemas.timesheet.screen.addhours.view;

import android.support.annotation.ArrayRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.samsistemas.timesheet.domain.Client;
import com.samsistemas.timesheet.domain.Project;
import com.samsistemas.timesheet.domain.TaskType;

import java.util.List;

/**
 * @author jonatan.salas
 */
public interface AddHoursView {

    void styleBar();

    void loadHours(@ArrayRes int hoursArrayId, @LayoutRes int layoutItemId);

    void loadTaskTypes(@Nullable List<TaskType> taskTypeList);

    void loadClients(@Nullable List<Client> clientList);

    void loadProjects(@Nullable List<Project> projectList);
}
