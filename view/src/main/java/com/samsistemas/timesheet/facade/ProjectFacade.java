package com.samsistemas.timesheet.facade;

import android.content.Context;
import android.support.annotation.NonNull;

import com.samsistemas.timesheet.controller.base.BaseController;
import com.samsistemas.timesheet.entity.ProjectEntity;
import com.samsistemas.timesheet.facade.base.Facade;
import com.samsistemas.timesheet.factory.ControllerFactory;
import com.samsistemas.timesheet.model.Client;
import com.samsistemas.timesheet.model.Project;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jonatan.salas
 */
public class ProjectFacade implements Facade<Project> {
    private static ProjectFacade instance = null;
    protected BaseController<ProjectEntity> projectController;
    protected Facade<Client> clientFacade;

    protected ProjectFacade() {
        this.projectController = ControllerFactory.getProjectController();
        this.clientFacade = ClientFacade.newInstance();
    }

    @Override
    public Project findById(@NonNull Context context, long id) {
        final ProjectEntity entity = projectController.get(context, id);
        final Client client = clientFacade.findById(context, entity.getClientId());
        final Project project = new Project();

        project.setId(entity.getProjectId())
               .setClient(client)
               .setName(entity.getName())
               .setShortName(entity.getShortName())
               .setStartDate(entity.getStartDate())
               .setEnabled(entity.isEnabled());

        return project;
    }

    @Override
    public List<Project> findAll(@NonNull Context context) {
        final List<ProjectEntity> projectEntities = projectController.listAll(context);
        final List<Project> projects = new ArrayList<>(projectEntities.size());
        final Project project = new Project();
        ProjectEntity entity;
        Client client;

        for(int i = 0; i < projectEntities.size(); i++) {
            entity = projectEntities.get(i);
            client = clientFacade.findById(context, entity.getClientId());

            project.setId(entity.getProjectId())
                   .setClient(client)
                   .setName(entity.getName())
                   .setShortName(entity.getShortName())
                   .setStartDate(entity.getStartDate())
                   .setEnabled(entity.isEnabled());

            projects.add(project);
        }

        return projects;
    }

    @Override
    public boolean insert(@NonNull Context context, Project project) {
        final ProjectEntity entity = new ProjectEntity();

        entity.setProjectId(project.getId())
              .setClientId(project.getClient().getId())
              .setName(project.getName())
              .setShortName(project.getShortName())
              .setStartDate(project.getStartDate())
              .setEnabled(project.isEnabled());

        return projectController.insert(context, entity);
    }

    @Override
    public boolean update(@NonNull Context context, Project project) {
        final ProjectEntity entity = new ProjectEntity();

        entity.setProjectId(project.getId())
                .setClientId(project.getClient().getId())
                .setName(project.getName())
                .setShortName(project.getShortName())
                .setStartDate(project.getStartDate())
                .setEnabled(project.isEnabled());

        return projectController.update(context, entity);
    }

    @Override
    public boolean deleteById(@NonNull Context context, long id) {
        return projectController.delete(context, id);
    }

    public static ProjectFacade newInstance() {
        if(null == instance)
            instance = new ProjectFacade();
        return instance;
    }
}
