package com.samsistemas.timesheet.service;

import android.app.IntentService;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import static com.samsistemas.timesheet.util.JSONObjectKeys.*;

import com.samsistemas.timesheet.controller.Controller;
import com.samsistemas.timesheet.entity.JobLogEntity;
import com.samsistemas.timesheet.factory.ControllerFactory;
import com.samsistemas.timesheet.helper.UriHelper;
import com.samsistemas.timesheet.network.converter.JobLogEntityConverter;
import com.samsistemas.timesheet.util.AuthUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * @author jonatan.salas
 */
public class FetchJobLogDataService extends IntentService {
    private static final String TAG = FetchJobLogDataService.class.getSimpleName();
    private RequestQueue mRequestQueue;

    public FetchJobLogDataService() {
        super(TAG);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.mRequestQueue = Volley.newRequestQueue(this);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        final String jobLogsUrl = intent.getStringExtra(URL);
        final String username = intent.getStringExtra(USERNAME);
        final String password = intent.getStringExtra(PASSWORD);
        final String month = intent.getStringExtra(MONTH);
        final String year = intent.getStringExtra(YEAR);

        JSONObject jobLog = null;

        try {
            jobLog = new JSONObject();
            jobLog.put(USERNAME, username)
                  .put(MONTH, month)
                  .put(YEAR, year);

        } catch (JSONException ex) {
            Log.e(TAG, ex.getMessage(), ex.getCause());
        }

        final JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.POST,
                jobLogsUrl,
                jobLog,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            final Controller<JobLogEntity> jobLogController = ControllerFactory.getJobLogController();

                            final JobLogEntityConverter jobLogConverter = JobLogEntityConverter.newInstance();
                            final List<JobLogEntity> jobLogEntities = jobLogConverter.asList(response);
                            final Uri uri = UriHelper.buildJobLogUri(getApplicationContext());

                            jobLogController.bulkInsert(getApplicationContext(), jobLogEntities, uri);

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
                return AuthUtil.getAuthHeaders(new String[]{ username, password });
            }
        };

        request.setShouldCache(true);
        request.setRetryPolicy(new DefaultRetryPolicy());
        mRequestQueue.add(request);
    }
}
