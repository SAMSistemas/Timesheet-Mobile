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

import com.samsistemas.timesheet.constant.JSONConst;
import com.samsistemas.timesheet.network.service.ProjectNetworkService;
import com.samsistemas.timesheet.util.AuthUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

/**
 * @author jonatan.salas
 */
public class FetchProjectDataService extends IntentService implements JSONConst {
    protected static final String TAG = FetchProjectDataService.class.getSimpleName();
//    public static final int STATUS_RUNNING = 0;
//    public static final int STATUS_FINISHED = 1;
//    public static final int STATUS_ERROR = 2;
    private ProjectNetworkService mService;
    private RequestQueue mRequestQueue;


    public FetchProjectDataService() {
        super(TAG);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.mService = new ProjectNetworkService();
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
                            mService.parseNetworkResponse(getApplicationContext(), response, null);
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
