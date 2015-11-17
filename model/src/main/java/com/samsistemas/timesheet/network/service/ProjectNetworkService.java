package com.samsistemas.timesheet.network.service;

import android.content.Context;
import android.support.annotation.NonNull;

import com.samsistemas.timesheet.controller.base.BaseController;
import com.samsistemas.timesheet.entity.ClientEntity;
import com.samsistemas.timesheet.entity.ProjectEntity;
import com.samsistemas.timesheet.factory.ControllerFactory;
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
    public void parseNetworkResponse(@NonNull Context context, JSONArray response, Object customArgs) throws JSONException {
        final BaseController<ProjectEntity> projectController = ControllerFactory.getProjectController();
        final BaseController<ClientEntity> clientController = ControllerFactory.getClientController();

        final ProjectEntityListParser projectEntityListParser = ProjectEntityListParser.newInstance();
        final List<ProjectEntity> projectEntities = projectEntityListParser.convert(context.getApplicationContext(), response);

        projectController.bulkInsert(context.getApplicationContext(), projectEntities);

        final ClientEntityListParser clientEntityListParser = ClientEntityListParser.newInstance();
        final List<ClientEntity> clientEntities = clientEntityListParser.convert(context.getApplicationContext(), response);

        clientController.bulkInsert(context.getApplicationContext(), clientEntities);
    }
}
