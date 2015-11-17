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

import com.samsistemas.timesheet.data.R;
import com.samsistemas.timesheet.controller.base.BaseLoginController;
import com.samsistemas.timesheet.network.request.BasicAuthRequest;
import com.samsistemas.timesheet.network.service.JobLogNetworkService;
import com.samsistemas.timesheet.network.service.PersonNetworkService;
import com.samsistemas.timesheet.network.service.ProjectNetworkService;
import com.samsistemas.timesheet.util.AuthUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

/**
 * Login controller implementation.
 *
 * @author jonatan.salas
 */
public class LoginController implements BaseLoginController {
    protected static final String TAG = LoginController.class.getSimpleName();
    private boolean isLogged = false;

    @Override
    public boolean performLogin(@NonNull final Context context, @NonNull final String[] credentials) {
        final RequestQueue requestQueue = Volley.newRequestQueue(context.getApplicationContext());

        final BasicAuthRequest loginRequest = new BasicAuthRequest(
                Request.Method.POST,
                getLoginUrl(context),
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

        final JsonObjectRequest personJsonRequest = new JsonObjectRequest(
                Request.Method.GET,
                getPersonUrl(context) + credentials[0],
                new JSONObject(),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            final PersonNetworkService personNetworkService = new PersonNetworkService();
                            personNetworkService.parseNetworkResponse(context, response, credentials);
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
                return AuthUtil.getAuthHeaders(credentials);
            }
        };

        final JsonArrayRequest projectRequest = new JsonArrayRequest(
                Request.Method.GET,
                getProjectUrl(context),
                new JSONObject(),
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            final ProjectNetworkService projectNetworkService = new ProjectNetworkService();
                            projectNetworkService.parseNetworkResponse(context, response, null);
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
                return AuthUtil.getAuthHeaders(credentials);
            }
        };

        JSONObject jobLog = new JSONObject();

        try {
            jobLog = new JSONObject(getJson(credentials[0]));
        } catch (JSONException ex) {
            Log.e(TAG, ex.getMessage(), ex.getCause());
        }

        final JsonArrayRequest jobLogRequest = new JsonArrayRequest(
                Request.Method.POST,
                getJobLogUrl(context),
                jobLog,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            final JobLogNetworkService jobLogNetworkService = new JobLogNetworkService();
                            jobLogNetworkService.parseNetworkResponse(context, response, null);
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
                return AuthUtil.getAuthHeaders(credentials);
            }
        };

        requestQueue.add(loginRequest);
        requestQueue.add(personJsonRequest);
        requestQueue.add(projectRequest);
        requestQueue.add(jobLogRequest);

        return isLogged;
    }

    protected String getJson(@NonNull String username) {
        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        calendar.setTime(new Date(System.currentTimeMillis()));

        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        return "{ " +
               "    username: " + username + ", " +
               "    month: " + String.valueOf(month) + ", " +
               "    year: " + String.valueOf(year) +
               " }";
    }

    protected String getBaseUrl(@NonNull Context context) {
        return context.getString(R.string.base_url);
    }

    protected String getLoginUrl(@NonNull Context context) {
        return getBaseUrl(context) + context.getString(R.string.login_url);
    }

    protected String getPersonUrl(@NonNull Context context) {
        return getBaseUrl(context) + context.getString(R.string.person_url);
    }

    protected String getProjectUrl(@NonNull Context context) {
        return getBaseUrl(context) + context.getString(R.string.project_url);
    }

    protected String getJobLogUrl(@NonNull Context context) {
        return getBaseUrl(context) + context.getString(R.string.job_log_url);
    }
}
