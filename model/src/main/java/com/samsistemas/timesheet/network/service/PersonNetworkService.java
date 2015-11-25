package com.samsistemas.timesheet.network.service;

import android.content.Context;
import android.support.annotation.NonNull;

import com.samsistemas.timesheet.constant.JSONConst;
import com.samsistemas.timesheet.controller.base.BaseController;
import com.samsistemas.timesheet.controller.base.BaseSessionController;
import com.samsistemas.timesheet.entity.PersonEntity;
import com.samsistemas.timesheet.entity.SessionEntity;
import com.samsistemas.timesheet.entity.TaskTypeEntity;
import com.samsistemas.timesheet.entity.WorkPositionEntity;
import com.samsistemas.timesheet.factory.ControllerFactory;
import com.samsistemas.timesheet.network.converter.PersonEntityParser;
import com.samsistemas.timesheet.network.converter.TaskTypeEntityListParser;
import com.samsistemas.timesheet.network.converter.WorkPositionEntityParser;
import com.samsistemas.timesheet.network.service.base.NetworkService;
import com.samsistemas.timesheet.util.AuthUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * @author jonatan.salas
 */
public class PersonNetworkService implements NetworkService<JSONObject, String[]>, JSONConst {

    @Override
    public String[] parseNetworkResponse(@NonNull Context context, JSONObject response, String[] credentials) throws JSONException {
        final String username = credentials[0];
        final String password = credentials[1];

        final BaseController<PersonEntity> personController = ControllerFactory.getPersonController();
        final BaseController<WorkPositionEntity> workPositionController = ControllerFactory.getWorkPositionController();
        final BaseController<TaskTypeEntity> taskTypeController = ControllerFactory.getTaskTypeController();

        final WorkPositionEntityParser workPositionParser = WorkPositionEntityParser.newInstance();
        final WorkPositionEntity workPositionEntity = workPositionParser.convert(response);

        final PersonEntityParser personEntityParser = PersonEntityParser.newInstance();
        final PersonEntity personEntity = personEntityParser.convert(response);

        personEntity.setUsername(username)
                    .setPassword(password);

        final JSONArray jsonTaskTypeArray = response.getJSONArray(TASK_TYPES);
        final TaskTypeEntityListParser taskTypeEntityListParser = TaskTypeEntityListParser.newInstance();
        List<TaskTypeEntity> taskTypeEntities = taskTypeEntityListParser.convert(jsonTaskTypeArray);

        taskTypeController.bulkInsert(context.getApplicationContext(), taskTypeEntities);
        workPositionController.insert(context.getApplicationContext(), workPositionEntity);
        personController.insert(context.getApplicationContext(), personEntity);

        initUserSession(context, credentials, personEntity.getPersonId());

        return null;
    }

    protected void initUserSession(@NonNull Context context, String[] credentials, long id) {
        final BaseSessionController<SessionEntity> sessionController = ControllerFactory.getSessionController();
        final String authCredential = AuthUtil.getAuthCredential(credentials[0], credentials[1]);
        final SessionEntity entity = new SessionEntity();

        entity.setSessionId(1)
              .setUserId(id)
              .setUsername(credentials[0])
              .setPassword(credentials[1])
              .setAuthCredential(authCredential)
              .setLogged(true);

        sessionController.createUserSession(context.getApplicationContext(), entity);
    }
}
