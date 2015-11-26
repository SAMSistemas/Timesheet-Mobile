package com.samsistemas.timesheet.network.request;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.samsistemas.timesheet.util.AuthUtil;

import java.util.Map;

/**
 * @author jonatan.salas
 */
public class NetworkRequest extends Request<NetworkResponse> {
    protected Response.Listener<NetworkResponse> mListener;
    protected String[] mCredentials;

    public NetworkRequest(int method, String url, Response.Listener<NetworkResponse> listener, Response.ErrorListener errorListener, String[] credentials) {
        super(method, url, errorListener);
        this.mListener = listener;
        this.mCredentials = credentials;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return (null != mCredentials)? AuthUtil.getAuthHeaders(mCredentials) : super.getHeaders();
    }

    @Override
    protected Response<NetworkResponse> parseNetworkResponse(NetworkResponse response) {
        return Response.success(response, HttpHeaderParser.parseCacheHeaders(response));
    }

    @Override
    protected void deliverResponse(NetworkResponse response) {
        if(null != mListener)
            mListener.onResponse(response);
    }
}
