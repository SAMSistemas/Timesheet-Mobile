package com.samsistemas.timesheet.adapter;

import android.content.Context;
import android.support.annotation.NonNull;

import com.samsistemas.timesheet.controller.base.BaseController;
import com.samsistemas.timesheet.factory.ControllerFactory;
import com.samsistemas.timesheet.model.Client;
import com.samsistemas.timesheet.model.JobLog;
import com.samsistemas.timesheet.model.Person;
import com.samsistemas.timesheet.model.Project;
import com.samsistemas.timesheet.model.TaskForPosition;
import com.samsistemas.timesheet.model.TaskType;
import com.samsistemas.timesheet.model.WorkPosition;
import com.samsistemas.timesheet.viewmodel.ClientViewModel;
import com.samsistemas.timesheet.viewmodel.ProjectViewModel;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jonatan.salas
 */
public class ModelAdapter {
    private static BaseController<Client> clientBaseController = ControllerFactory.getClientController();
    private static BaseController<WorkPosition> workPositionBaseController = ControllerFactory.getWorkPositionController();
    private static BaseController<Person> personBaseController = ControllerFactory.getPersonController();
    private static BaseController<TaskType> taskTypeBaseController = ControllerFactory.getTaskTypeController();
    private static BaseController<TaskForPosition> taskForPositionBaseController = ControllerFactory.getTaskForPositionController();
    private static BaseController<Project> projectBaseController = ControllerFactory.getProjectController();
    private static BaseController<JobLog> jobLogBaseController = ControllerFactory.getJobLogController();

    private WeakReference<Context> mContextReference;

    public ModelAdapter(@NonNull WeakReference<Context> contextReference) {
        this.mContextReference = contextReference;
    }

    /**
     *
     * @return
     */
    public List<ClientViewModel> getClients() {
        List<Client> clientList = clientBaseController.listAll(mContextReference.get());
        List<ClientViewModel> clientViewModels = new ArrayList<>(clientList.size());

        for(int i = 0; i < clientList.size(); i++) {
            clientViewModels.add(new ClientViewModel(clientList.get(i)));
        }

        return clientViewModels;
    }

    /**
     *
     * @return
     */
    public List<ProjectViewModel> getProjects() {
        List<Project> projectList = projectBaseController.listAll(mContextReference.get());
        List<ProjectViewModel> projectViewModels = new ArrayList<>(projectList.size());

        for(int i = 0; i < projectList.size(); i++) {
            projectViewModels.add(new ProjectViewModel(projectList.get(i)));
        }

        return projectViewModels;
    }
}
