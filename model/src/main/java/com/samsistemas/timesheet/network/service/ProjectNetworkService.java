package com.samsistemas.timesheet.network.service;

import android.content.Context;
import android.support.annotation.NonNull;

import com.samsistemas.timesheet.controller.Controller;
import com.samsistemas.timesheet.entity.ClientEntity;
import com.samsistemas.timesheet.entity.ProjectEntity;
import com.samsistemas.timesheet.factory.ControllerFactory;
import com.samsistemas.timesheet.helper.UriHelper;
import com.samsistemas.timesheet.network.converter.ClientEntityListParser;
import com.samsistemas.timesheet.network.converter.ProjectEntityListParser;
import com.samsistemas.timesheet.network.service.base.NetworkService;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;

/**
 * @author jonatan.salas
 */
public class ProjectNetworkService implements NetworkService<JSONArray, Object> {

    @Override
    public Object parseNetworkResponse(@NonNull Context context, JSONArray response, Object customArgs) throws JSONException {
        final Controller<ProjectEntity> projectController = ControllerFactory.getProjectController();
        final Controller<ClientEntity> clientController = ControllerFactory.getClientController();

        final ProjectEntityListParser projectEntityListParser = ProjectEntityListParser.newInstance();
        final List<ProjectEntity> projectEntities = projectEntityListParser.convert(response);

        projectController.bulkInsert(context.getApplicationContext(), projectEntities, UriHelper.buildProjectUri(context));

        final ClientEntityListParser clientEntityListParser = ClientEntityListParser.newInstance();
        final List<ClientEntity> clientEntities = clientEntityListParser.convert(response);

        return clientController.bulkInsert(context.getApplicationContext(), clientEntities, UriHelper.buildClientUri(context));
    }
}
