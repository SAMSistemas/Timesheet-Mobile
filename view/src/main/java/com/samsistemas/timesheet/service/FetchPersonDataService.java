package com.samsistemas.timesheet.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.samsistemas.timesheet.network.service.PersonNetworkService;
import com.samsistemas.timesheet.util.AuthUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

/**
 * @author jonatan.salas
 */
public class FetchPersonDataService extends IntentService {
    protected static final String TAG = FetchPersonDataService.class.getSimpleName();
//    public static final int STATUS_RUNNING = 0;
//    public static final int STATUS_FINISHED = 1;
//    public static final int STATUS_ERROR = 2;
    public static final String URL = "url";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";

    private PersonNetworkService mService;
    private RequestQueue mRequestQueue;

    public FetchPersonDataService() {
        super(TAG);
        this.mService = new PersonNetworkService();
        this.mRequestQueue = Volley.newRequestQueue(getApplicationContext());
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        final String personUrl = intent.getStringExtra(URL);
        final String username = intent.getStringExtra(USERNAME);
        final String password = intent.getStringExtra(PASSWORD);

        final JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                personUrl,
                new JSONObject(),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            mService.parseNetworkResponse(getApplicationContext(), response, new String[] { username, password });
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
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return AuthUtil.getAuthHeaders(new String[]{ username, password });
            }
        };

        mRequestQueue.add(request);
    }
}
