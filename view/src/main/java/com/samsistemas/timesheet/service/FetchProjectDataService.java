package com.samsistemas.timesheet.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import static com.samsistemas.timesheet.util.JSONObjectKeys.*;

import com.samsistemas.timesheet.controller.Controller;
import com.samsistemas.timesheet.entity.ClientEntity;
import com.samsistemas.timesheet.entity.ProjectEntity;
import com.samsistemas.timesheet.factory.ControllerFactory;
import com.samsistemas.timesheet.helper.UriHelper;
import com.samsistemas.timesheet.network.converter.ClientConverter;
import com.samsistemas.timesheet.network.converter.ProjectConverter;
import com.samsistemas.timesheet.util.AuthUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * @author jonatan.salas
 */
public class FetchProjectDataService extends IntentService {
    private static final String TAG = FetchProjectDataService.class.getSimpleName();
    private RequestQueue mRequestQueue;

    public FetchProjectDataService() {
        super(TAG);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.mRequestQueue = Volley.newRequestQueue(this);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        final String projectUrl = intent.getStringExtra(URL);
        final String username = intent.getStringExtra(USERNAME);
        final String password = intent.getStringExtra(PASSWORD);

        final JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET,
                projectUrl,
                new JSONObject(),
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            final Controller<ProjectEntity> projectController = ControllerFactory.getProjectController();
                            final Controller<ClientEntity> clientController = ControllerFactory.getClientController();

                            final ProjectConverter projectConverter = ProjectConverter.newInstance();
                            final List<ProjectEntity> projectEntities = projectConverter.asList(response);

                            projectController.bulkInsert(getApplicationContext(), projectEntities, UriHelper.buildProjectUri(getApplicationContext()));

                            final ClientConverter clientConverter = ClientConverter.newInstance();
                            final List<ClientEntity> clientEntities = clientConverter.asList(response);

                            clientController.bulkInsert(getApplicationContext(), clientEntities, UriHelper.buildClientUri(getApplicationContext()));

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
                return AuthUtil.getAuthHeaders(new String[] { username, password });
            }
        };

        mRequestQueue.add(request);
    }
}
