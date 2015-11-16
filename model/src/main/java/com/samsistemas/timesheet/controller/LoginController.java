package com.samsistemas.timesheet.controller;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.samsistemas.timesheet.controller.base.BaseController;
import com.samsistemas.timesheet.controller.base.BaseLoginController;
import com.samsistemas.timesheet.controller.base.BaseSessionController;
import com.samsistemas.timesheet.data.R;
import com.samsistemas.timesheet.entity.ClientEntity;
import com.samsistemas.timesheet.entity.JobLogEntity;
import com.samsistemas.timesheet.entity.PersonEntity;
import com.samsistemas.timesheet.entity.ProjectEntity;
import com.samsistemas.timesheet.entity.SessionEntity;
import com.samsistemas.timesheet.entity.TaskTypeEntity;
import com.samsistemas.timesheet.entity.WorkPositionEntity;
import com.samsistemas.timesheet.factory.ControllerFactory;
import com.samsistemas.timesheet.request.BasicAuthRequest;
import com.samsistemas.timesheet.util.AuthUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Login controller implementation.
 *
 * @author jonatan.salas
 */
public class LoginController implements BaseLoginController {
    protected static final String TAG = LoginController.class.getSimpleName();
    protected static final String BASE_URL = "http://10.0.0.100:8080";
    protected static final String PROJECT_URL = BASE_URL + "/project/allByUsername/";
    protected static final String JOB_LOG_URL = BASE_URL + "/jobLog/all/";
    protected static final String LOGIN_URL = BASE_URL + "/login";
    protected static final String PERSON_URL = BASE_URL + "/person/show/";

    private long userId;
    private boolean isLogged = false;

    protected BaseSessionController<SessionEntity> sessionController;
    protected BaseController<PersonEntity> personController;
    protected BaseController<WorkPositionEntity> workPositionController;
    protected BaseController<TaskTypeEntity> taskTypeController;
    protected BaseController<ClientEntity> clientController;
    protected BaseController<ProjectEntity> projectController;
    protected BaseController<JobLogEntity> jobLogController;

    public LoginController() {
        this.sessionController = ControllerFactory.getSessionController();
        this.personController = ControllerFactory.getPersonController();
        this.workPositionController = ControllerFactory.getWorkPositionController();
        this.taskTypeController = ControllerFactory.getTaskTypeController();
        this.clientController = ControllerFactory.getClientController();
        this.projectController = ControllerFactory.getProjectController();
        this.jobLogController = ControllerFactory.getJobLogController();
    }

    @Override
    public boolean performLogin(@NonNull final Context context, @NonNull final String[] credentials) {
        final RequestQueue requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        final String username = credentials[0];
        final String password = credentials[1];

        final BasicAuthRequest loginRequest = new BasicAuthRequest(
                Request.Method.POST,
                LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        isLogged = Boolean.parseBoolean(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        isLogged = false;
                        Log.e(TAG, error.getMessage(), error.getCause());
                    }
                },
                credentials
        );

        final String personUrl = PERSON_URL + credentials[0];

        final JsonObjectRequest personJsonRequest = new JsonObjectRequest(
                Request.Method.GET,
                personUrl,
                new JSONObject(),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            userId = parsePersonNetworkResponse(context, response, username, password);
                        } catch (JSONException ex) {
                            Log.e(TAG, ex.getMessage(), ex.getCause());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, error.getMessage(), error.getCause());
                    }
                })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return AuthUtil.getAuthHeaders(username, password);
            }
        };

        final JsonArrayRequest projectRequest = new JsonArrayRequest(
                Request.Method.GET,
                PROJECT_URL,
                new JSONObject(),
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            parseProjectNetWorkResponse(context, response);
                        } catch (JSONException ex) {
                            Log.e(TAG, ex.getMessage(), ex.getCause());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, error.getMessage(), error.getCause());
                    }
                }
        );

        JSONObject jobLog = null;

        try {
            jobLog = new JSONObject(getJson(username));
        } catch (JSONException ex) {
            Log.e(TAG, ex.getMessage(), ex.getCause());
        }

        final JsonArrayRequest jobLogRequest = new JsonArrayRequest(
                Request.Method.POST,
                JOB_LOG_URL,
                jobLog,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            parseJobLogNetworkResponse(context, response);
                        } catch (JSONException ex) {
                            Log.e(TAG, ex.getMessage(), ex.getCause());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, error.getMessage(), error.getCause());
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return AuthUtil.getAuthHeaders(username, password);
            }
        };

        requestQueue.add(loginRequest);
        requestQueue.add(personJsonRequest);
        requestQueue.add(projectRequest);
        requestQueue.add(jobLogRequest);

        if(isLogged) {
            final SessionEntity entity = new SessionEntity();
            final String authCredential = AuthUtil.getAuthCredential(username, password);

            entity.setSessionId(1)
                  .setUserId(userId)
                  .setUsername(username)
                  .setPassword(password)
                  .setAuthCredential(authCredential)
                  .setLogged(true);

            sessionController.createUserSession(context.getApplicationContext(), entity);
        }

        return isLogged;
    }

    /**
     *
     * @param context
     * @param person
     * @param username
     * @param password
     * @return
     * @throws JSONException
     */
    protected long parsePersonNetworkResponse(@NonNull Context context, JSONObject person, String username, String password) throws JSONException {
        final PersonEntity personEntity = new PersonEntity();
        final WorkPositionEntity workPositionEntity = new WorkPositionEntity();
        final TaskTypeEntity taskTypeEntity = new TaskTypeEntity();
        final List<TaskTypeEntity> taskTypeEntities = new ArrayList<>();

        JSONObject jsonWorkPosition = person.getJSONObject(context.getString(R.string.work_position));

        workPositionEntity.setWorkPositionId(jsonWorkPosition.getLong(context.getString(R.string.id)))
                          .setDescription(jsonWorkPosition.getString(context.getString(R.string.description)));

        personEntity.setPersonId(person.getLong(context.getString(R.string.id)))
                    .setName(person.getString(context.getString(R.string.name)))
                    .setLastName(person.getString(context.getString(R.string.last_name)))
                    .setUsername(username)
                    .setPassword(password)
                    .setWorkPositionId(workPositionEntity.getWorkPositionId())
                    .setPicture(null)
                    .setEnabled(true);

        JSONArray jsonTaskTypes = person.getJSONArray(context.getString(R.string.task_types));

        if(null != jsonTaskTypes) {
            for (int i = 0; i < jsonTaskTypes.length(); i++) {
                JSONObject jsonTaskType = jsonTaskTypes.getJSONObject(i);

                taskTypeEntity.setTaskTypeId(jsonTaskType.getLong(context.getString(R.string.id)))
                        .setName(jsonTaskType.getString(context.getString(R.string.name)))
                        .setEnabled(true);

                taskTypeEntities.add(i, taskTypeEntity);
            }

            boolean taskInserted = taskTypeController.bulkInsert(context, taskTypeEntities);
            Log.d(TAG, "Result of inserted TaskTypes list is: " + taskInserted);
        }

        boolean workPosInserted = workPositionController.insert(context, workPositionEntity);
        Log.d(TAG, "Result of inserted workPosition is: " + workPosInserted);
        boolean personInserted = personController.insert(context, personEntity);
        Log.d(TAG, "Result of inserted person is: " + personInserted);

        return personEntity.getPersonId();
    }

    protected void parseProjectNetWorkResponse(@NonNull Context context, JSONArray projectResponse) throws JSONException {
        List<ClientEntity> clientEntities = new ArrayList<>();
        List<ProjectEntity> projectEntities = new ArrayList<>();
        ClientEntity clientEntity = new ClientEntity();
        ProjectEntity projectEntity = new ProjectEntity();

        for(int i = 0; i < projectResponse.length(); i++) {

        }
    }

    protected void parseJobLogNetworkResponse(@NonNull Context context, JSONArray jobLogResponse) throws JSONException {
        final List<JobLogEntity> jobLogEntities = new ArrayList<>();
        final JobLogEntity jobLogEntity = new JobLogEntity();

        for(int i = 0; i < jobLogResponse.length(); i++) {

        }
    }

    protected String getJson(@NonNull String username) {
        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        calendar.setTime(new Date(System.currentTimeMillis()));

        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        return " { username: " + username + ", month: " + String.valueOf(month) + ", year: " + String.valueOf(year) + " }";
    }
}
