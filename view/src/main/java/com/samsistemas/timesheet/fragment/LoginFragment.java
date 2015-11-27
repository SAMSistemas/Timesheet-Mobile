package com.samsistemas.timesheet.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.IntentFilter;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import com.samsistemas.timesheet.R;
import com.samsistemas.timesheet.constant.JSONConst;
import com.samsistemas.timesheet.helper.URLHelper;
import com.samsistemas.timesheet.navigation.MenuNavigator;
import com.samsistemas.timesheet.network.request.NetworkRequest;
import com.samsistemas.timesheet.network.service.JobLogsNetworkService;
import com.samsistemas.timesheet.network.service.PersonNetworkService;
import com.samsistemas.timesheet.network.service.ProjectNetworkService;
import com.samsistemas.timesheet.service.NetworkStateService;
import com.samsistemas.timesheet.util.AuthUtil;
import com.samsistemas.timesheet.util.InputUtil;
import com.samsistemas.timesheet.util.TypefaceUtil;
import com.samsistemas.timesheet.validation.EmailValidator;
import com.samsistemas.timesheet.validation.PasswordValidator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Class that represents a Login UI.
 *
 * @author jonatan.salas
 */
public class LoginFragment extends Fragment implements View.OnClickListener, View.OnFocusChangeListener, NetworkStateService.OnNetworkStateReceived, JSONConst {
    private static final String TAG = LoginFragment.class.getSimpleName();
    private NetworkStateService mNetworkStateService;

    @Bind(R.id.username_layout)
    TextInputLayout mUsernameInput;

    @Bind(R.id.password_layout)
    TextInputLayout mPasswordInput;

    @Bind(R.id.username)
    EditText mUsernameEditText;

    @Bind(R.id.password)
    EditText mPasswordEditText;

    @Bind(R.id.login_button)
    Button mLoginButton;

    /**
     * Default LoginFragment constructor.
     */
    public LoginFragment() {
        setHasOptionsMenu(false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);

        final Typeface robotoRegular = TypefaceUtil.getCustomTypeface(
                getApplicationContext(),
                R.string.roboto_regular
        );

        mUsernameInput.requestFocus();
        mUsernameEditText.requestFocus();

        //Styling views..
        mUsernameInput.setTypeface(robotoRegular);
        mPasswordInput.setTypeface(robotoRegular);
        mUsernameEditText.setTypeface(robotoRegular);
        mPasswordEditText.setTypeface(robotoRegular);

        //Setting listeners..
        mUsernameEditText.setOnFocusChangeListener(this);
        mPasswordEditText.setOnFocusChangeListener(this);
        mLoginButton.setOnFocusChangeListener(this);
        mLoginButton.setOnClickListener(this);

        mNetworkStateService = new NetworkStateService(this);
        final IntentFilter filter = new IntentFilter(NetworkStateService.CONNECTIVITY_CHANGE_ACTION);
        getActivity().registerReceiver(mNetworkStateService, filter);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUsernameEditText.setOnFocusChangeListener(null);
        mPasswordEditText.setOnFocusChangeListener(null);
        mLoginButton.setOnFocusChangeListener(null);
        mLoginButton.setOnClickListener(null);

        getActivity().unregisterReceiver(mNetworkStateService);

        ButterKnife.unbind(this);
    }

    @Override
    public void onClick(@NonNull View view) {
        final int id = view.getId();

        switch (id) {
            case R.id.login_button:
                final String username = mUsernameEditText.getText().toString().trim();
                final String password = mPasswordEditText.getText().toString().trim();

                final String[] credentials = new String[] {
                        username,
                        password
                };

                doLogin(getActivity(), credentials, view);

                break;
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        final int id = v.getId();

        switch (id) {
            case R.id.username:
                if(hasFocus)
                    InputUtil.showKeyboard(getActivity());
                else
                    InputUtil.hideKeyboard(getActivity());

                break;
            case R.id.password:
                if(hasFocus)
                    InputUtil.showKeyboard(getActivity());
                else
                    InputUtil.hideKeyboard(getActivity());

                break;
            case R.id.login_button:
                if(hasFocus)
                    InputUtil.hideKeyboard(getActivity());
                break;
        }
    }

    /***
     * Method that checks email and password. After checking it performs login.
     *
     * @param context - the activity where this method is going to be used.
     * @param credentials - the email and password as a String array.
     * @param view - the view used for animations.
     */
    protected void doLogin(@NonNull Activity context,
                           @NonNull final String[] credentials,
                           @NonNull final View view) {

        final String email = credentials[0];
        final String password = credentials[1];

        final EmailValidator emailValidator = EmailValidator.newInstance();
        final PasswordValidator passwordValidator = PasswordValidator.newInstance();

        if(!emailValidator.validate(email) && !passwordValidator.validate(password)) {
            Snackbar.make(view, context.getString(R.string.email_password_error), Snackbar.LENGTH_LONG).show();
        } else if(!emailValidator.validate(email)) {
            Snackbar.make(view, context.getString(R.string.email_error), Snackbar.LENGTH_LONG).show();
        } else if(!passwordValidator.validate(password)) {
            Snackbar.make(view, context.getString(R.string.password_error), Snackbar.LENGTH_LONG).show();
        } else {
            final RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.AppTheme_Dialog);
            builder.setView(R.layout.authentication_dialog);
            builder.setCancelable(true);

            final AlertDialog alertDialog = builder.create();
            alertDialog.show();

            try {
                Thread.sleep(2000);
                NetworkRequest networkRequest = new NetworkRequest(
                        Request.Method.GET,
                        URLHelper.buildLoginUrl(getApplicationContext()),
                        new Response.Listener<NetworkResponse>() {
                            @Override
                            public void onResponse(NetworkResponse response) {
                                if(null != response) {
                                    if (response.statusCode == 200) {
                                        alertDialog.dismiss();
                                        fetchWorkingData(requestQueue, view, credentials);
                                        MenuNavigator.newInstance().navigateWithAnimation(getActivity(), view);
                                    }
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e(TAG, error.getMessage(), error.getCause());
                                if (null != error.networkResponse) {
                                    final int statusCode = error.networkResponse.statusCode;

                                    if (statusCode == 401) {
                                        alertDialog.dismiss();
                                        Snackbar.make(view, "Invalid credentials!!", Snackbar.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        },
                        credentials
                );

                requestQueue.add(networkRequest);
            } catch (InterruptedException ex) {
                Log.e(TAG, ex.getMessage(), ex.getCause());            }
        }
    }

    /**
     * Method that gets the applicationContext.
     *
     * @return a context object.
     */
    private Context getApplicationContext() {
        return getContext().getApplicationContext();
    }

    @Override
    public void checkNetworkState(Context context, Boolean isConnected) {
        if (!isConnected) {
            checkNullability(false);
        } else {
            checkNullability(true);
        }
    }

    protected void fetchWorkingData(RequestQueue requestQueue, final View view, final String[] credentials) {
        JsonObjectRequest personJsonRequest = new JsonObjectRequest(
                Request.Method.GET,
                URLHelper.buildShowPersonUrl(getApplicationContext(), credentials[0]),
                new JSONObject(),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            final PersonNetworkService personNetworkService = new PersonNetworkService();
                            personNetworkService.parseNetworkResponse(getApplicationContext(), response, credentials);
                        } catch (JSONException ex) {
                            Snackbar.make(view, "Error Parsing data!", Snackbar.LENGTH_SHORT).show();
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

        JsonArrayRequest projectRequest = new JsonArrayRequest(
                Request.Method.GET,
                URLHelper.buildAllProjectsByUsernameUrl(getApplicationContext(), credentials[0]),
                new JSONObject(),
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            final ProjectNetworkService projectNetworkService = new ProjectNetworkService();
                            projectNetworkService.parseNetworkResponse(getApplicationContext(), response, null);
                        } catch (JSONException ex) {
                            Snackbar.make(view, "Error Parsing data!", Snackbar.LENGTH_SHORT).show();
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

        JSONObject jobLog = null;
        Date date = new Date(System.currentTimeMillis());
        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        calendar.setTime(date);

        try {
            jobLog = new JSONObject();
            jobLog.put(USERNAME, credentials[0])
                  .put(MONTH, calendar.get(Calendar.MONTH))
                  .put(YEAR, calendar.get(Calendar.YEAR));

        } catch (JSONException ex) {
            Log.e(TAG, ex.getMessage(), ex.getCause());
        }

        JsonArrayRequest jobLogRequest = new JsonArrayRequest(
                Request.Method.POST,
                URLHelper.buildAllJobLogsUrl(getApplicationContext()),
                jobLog,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            final JobLogsNetworkService jobLogsNetworkService = new JobLogsNetworkService();
                            jobLogsNetworkService.parseNetworkResponse(getApplicationContext(), response, null);
                        } catch (JSONException ex) {
                            Snackbar.make(view, "Error Parsing data!", Snackbar.LENGTH_SHORT).show();
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

        requestQueue.add(personJsonRequest);
        requestQueue.add(projectRequest);
        requestQueue.add(jobLogRequest);
    }

    protected void checkNullability(@NonNull final Boolean enabled) {
        if(null != mUsernameInput) {
            mUsernameInput.setFocusable(enabled);
            mUsernameInput.setFocusableInTouchMode(enabled);
            mUsernameInput.setEnabled(enabled);
        }

        if(null != mPasswordInput)
            mPasswordInput.setEnabled(enabled);

        if(null != mUsernameEditText)
            mUsernameEditText.setEnabled(enabled);

        if(null != mPasswordEditText)
            mPasswordEditText.setEnabled(enabled);

        if(null != mLoginButton)
            mLoginButton.setEnabled(enabled);
    }
}
