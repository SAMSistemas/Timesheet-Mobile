package com.samsistemas.timesheet.service;

import android.app.IntentService;
import android.content.Intent;

/**
 * @author jonatan.salas
 */
public class FetchProjectDataService extends IntentService {
    private static final String LOG_TAG = FetchProjectDataService.class.getSimpleName();

    public FetchProjectDataService() {
        super(LOG_TAG);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
//        final String projectUrl = intent.getStringExtra(URL);
//        final String username = intent.getStringExtra(USERNAME);
//        final String password = intent.getStringExtra(PASSWORD);
//
//        final JsonArrayRequest request = new JsonArrayRequest(
//                Request.Method.GET,
//                projectUrl,
//                new JSONObject(),
//                new Response.Listener<JSONArray>() {
//                    @Override
//                    public void onResponse(JSONArray response) {
//                        try {
//                            final Controller<ProjectEntity> projectController = ControllerFactory.getProjectController();
//                            final Controller<ClientEntity> clientController = ControllerFactory.getClientController();
//
//                            final ProjectEntityConverter projectConverter = ProjectEntityConverter.newInstance();
//                            final List<ProjectEntity> projectEntities = projectConverter.asList(response);
//
//                            projectController.bulkInsert(getApplicationContext(), projectEntities, UriHelper.buildProjectUri(getApplicationContext()));
//
//                            final ClientEntityConverter clientConverter = ClientEntityConverter.newInstance();
//                            final List<ClientEntity> clientEntities = clientConverter.asList(response);
//
//                            clientController.bulkInsert(getApplicationContext(), clientEntities, UriHelper.buildClientUri(getApplicationContext()));
//
//                        } catch (JSONException ex) {
//                            Log.e(LOG_TAG, ex.getMessage(), ex.getCause());
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Log.e(LOG_TAG, error.getMessage(), error.getCause());
//                    }
//                }
//        ) {
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                return AuthUtil.getAuthHeaders(new String[] { username, password });
//            }
//        };
//
//        request.setShouldCache(true);
//        request.setRetryPolicy(new DefaultRetryPolicy());
//        mRequestQueue.add(request);
    }
}
