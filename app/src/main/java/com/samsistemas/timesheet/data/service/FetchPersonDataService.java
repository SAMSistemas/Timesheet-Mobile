package com.samsistemas.timesheet.data.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

/**
 * @author jonatan.salas
 */
public class FetchPersonDataService extends IntentService {
    private static final String TAG = FetchPersonDataService.class.getSimpleName();

    public FetchPersonDataService() {
        super(TAG);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
//        final String personUrl = intent.getStringExtra(URL);
//        final String username = intent.getStringExtra(USERNAME);
//        final String password = intent.getStringExtra(PASSWORD);
//
//        final JsonObjectRequest request = new JsonObjectRequest(
//                Request.Method.GET,
//                personUrl,
//                new JSONObject(),
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        try {
////                            final Controller<PersonEntity> personController = ControllerFactory.getPersonController();
////                            final Controller<WorkPositionEntity> workPositionController = ControllerFactory.getWorkPositionController();
////                            final Controller<TaskTypeEntity> taskTypeController = ControllerFactory.getTaskTypeController();
////
////                            final WorkPositionEntityConverter workPositionConverter = WorkPositionEntityConverter.newInstance();
////                            final WorkPositionEntity workPositionEntity = workPositionConverter.asObject(response);
////
////                            final PersonEntityConverter personConverter = PersonEntityConverter.newInstance();
////                            final PersonEntity personEntity = personConverter.asObject(response);
////
////                            personEntity.setUsername(username)
////                                        .setPassword(password);
////
////                            final JSONArray jsonTaskTypeArray = response.getJSONArray(TASK_TYPES);
////                            final TaskTypeEntityConverter taskTypeConverter = TaskTypeEntityConverter.newInstance();
////                            List<TaskTypeEntity> taskTypeEntities = taskTypeConverter.asList(jsonTaskTypeArray);
////
////                            taskTypeController.bulkInsert(getApplicationContext(), taskTypeEntities, UriHelper.buildTaskTypeUri(getApplicationContext()));
////                            workPositionController.insert(getApplicationContext(), workPositionEntity, UriHelper.buildWorkPositionUri(getApplicationContext()));
////                            personController.insert(getApplicationContext(), personEntity, UriHelper.buildPersonUri(getApplicationContext()));
//
//                            initUserSession(getApplicationContext(), new String[] { username, password } , personEntity.getId());
//
//                        } catch (JSONException ex) {
//                            Log.e(TAG, ex.getMessage(), ex.getCause());
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Log.e(TAG, error.getMessage(), error.getCause());
//                    }
//                }) {
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                return AuthUtil.getAuthHeaders(new String[]{ username, password });
//            }
//        };
//
//        request.setShouldCache(true);
//        request.setRetryPolicy(new DefaultRetryPolicy());
//        mRequestQueue.add(request);
    }

    private void initUserSession(@NonNull Context context, String[] credentials, long id) {
////        final BaseSessionController<SessionEntity> sessionController = ControllerFactory.getSessionController();
//        final String authCredential = AuthUtil.getAuthCredential(credentials[0], credentials[1]);
//        final SessionEntity entity = new SessionEntity();
//
//        entity.setSessionId(1)
//              .setUserId(id)
//              .setUsername(credentials[0])
//              .setPassword(credentials[1])
//              .setAuthCredential(authCredential)
//              .setLogged(true);
//
//        sessionController.createUserSession(context.getApplicationContext(), entity);
    }
}
