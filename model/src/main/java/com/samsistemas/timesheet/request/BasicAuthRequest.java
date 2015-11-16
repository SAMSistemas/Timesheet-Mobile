package com.samsistemas.timesheet.request;

import android.support.annotation.NonNull;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.samsistemas.timesheet.util.AuthUtil;

import java.util.Map;

/**
 * @author jonatan.salas
 */
public class BasicAuthRequest extends StringRequest {
    @NonNull
    protected String username;

    @NonNull
    protected String password;

    /**
     *
     * @param method
     * @param url
     * @param listener
     * @param errorListener
     * @param credentials
     */
    public BasicAuthRequest(int method, String url, Response.Listener<String> listener,
                             Response.ErrorListener errorListener, @NonNull String[] credentials) {
        super(method, url, listener, errorListener);
        this.username = credentials[0];
        this.password = credentials[1];
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return AuthUtil.getAuthHeaders(username, password);
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        boolean isLogged = (response.statusCode == 200);
        return Response.success(Boolean.toString(isLogged), HttpHeaderParser.parseCacheHeaders(response));
    }

    @Override
    protected void deliverResponse(String response) {
        super.deliverResponse(response);
    }
}
