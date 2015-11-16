package com.samsistemas.timesheet.controller;

import android.content.Context;
import android.support.annotation.NonNull;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.samsistemas.timesheet.controller.base.BaseLoginController;
import com.samsistemas.timesheet.request.BasicAuthRequest;
import com.samsistemas.timesheet.util.AuthUtil;

import java.util.Map;

/**
 * Login controller implementation.
 *
 * @author jonatan.salas
 */
public class LoginController implements BaseLoginController {
    protected static final String LOGIN_URL = "http://10.0.0.67:8080/login";
    private Boolean isLogged = false;

    @Override
    public boolean performLogin(@NonNull final Context context, @NonNull final String[] credentials) {
        final RequestQueue requestQueue = Volley.newRequestQueue(context.getApplicationContext());

        BasicAuthRequest stringRequest = new BasicAuthRequest(
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
                        //VolleyLog.e(error.getCause(), error.getMessage());
                    }
                },
                credentials
        );

        requestQueue.add(stringRequest);

        return isLogged;
    }
}
