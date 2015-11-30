package com.samsistemas.timesheet.facade;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.samsistemas.timesheet.controller.Controller;
import com.samsistemas.timesheet.entity.ProjectEntity;
import com.samsistemas.timesheet.facade.base.Facade;
import com.samsistemas.timesheet.factory.ControllerFactory;
import com.samsistemas.timesheet.helper.UriHelper;
import com.samsistemas.timesheet.model.Client;
import com.samsistemas.timesheet.model.Project;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jonatan.salas
 */
public class ProjectFacade implements Facade<Project> {
    private static ProjectFacade instance = null;
    private final Controller<ProjectEntity> projectController;
    private final Facade<Client> clientFacade;

    private ProjectFacade() {
        this.projectController = ControllerFactory.getProjectController();
        this.clientFacade = ClientFacade.newInstance();
    }

    @Override
    public Project findById(@NonNull Context context, long id) {
        final Uri uri = UriHelper.buildProjectUriWithId(context, id);
        final ProjectEntity entity = projectController.get(context, uri);
        final Client client = clientFacade.findById(context, entity.getClientId());
        final Project project = new Project();

        project.setId(entity.getId())
               .setClient(client)
               .setName(entity.getName())
               .setShortName(entity.getShortName())
               .setStartDate(entity.getStartDate())
               .setEnabled(entity.isEnabled());

        return project;
    }

    @Override
    public List<Project> findAll(@NonNull Context context) {
        final Uri uri = UriHelper.buildProjectUri(context);
        final List<ProjectEntity> projectEntities = projectController.listAll(context, uri);
        final List<Project> projects = new ArrayList<>(projectEntities.size());
        final Project project = new Project();
        ProjectEntity entity;
        Client client;

        for(int i = 0; i < projectEntities.size(); i++) {
            entity = projectEntities.get(i);
            client = clientFacade.findById(context, entity.getClientId());

            project.setId(entity.getId())
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
        final Uri uri = UriHelper.buildProjectUri(context);
        final ProjectEntity entity = new ProjectEntity();

        entity.setId(project.getId());
        entity.setClientId(project.getClient().getId())
              .setName(project.getName())
              .setShortName(project.getShortName())
              .setStartDate(project.getStartDate())
              .setEnabled(project.isEnabled());

        return projectController.insert(context, entity, uri);
    }

    @Override
    public boolean update(@NonNull Context context, Project project) {
        final Uri uri = UriHelper.buildProjectUri(context);
        final ProjectEntity entity = new ProjectEntity();

        entity.setId(project.getId());
        entity.setClientId(project.getClient().getId())
              .setName(project.getName())
              .setShortName(project.getShortName())
              .setStartDate(project.getStartDate())
              .setEnabled(project.isEnabled());

        return projectController.update(context, entity, uri);
    }

    @Override
    public boolean deleteById(@NonNull Context context, long id) {
        Uri uri = UriHelper.buildProjectUri(context);
        return projectController.delete(context, uri, id);
    }

    public static ProjectFacade newInstance() {
        if(null == instance)
            instance = new ProjectFacade();
        return instance;
    }
}
