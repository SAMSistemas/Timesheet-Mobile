package com.samsistemas.timesheet.activity;

import android.app.Activity;
import android.content.Context;
import android.content.IntentFilter;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.samsistemas.timesheet.R;
import com.samsistemas.timesheet.fragment.VerifyConnectionFragment;
import com.samsistemas.timesheet.navigation.MenuNavigator;
import com.samsistemas.timesheet.receiver.NetworkStateReceiver;
import com.samsistemas.timesheet.util.InputUtil;
import com.samsistemas.timesheet.util.TypefaceUtil;
import com.samsistemas.timesheet.validation.EmailValidator;
import com.samsistemas.timesheet.validation.PasswordValidator;
import com.samsistemas.timesheet.validation.base.Validator;

/**
 * @author jonatan.salas
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener, View.OnFocusChangeListener, NetworkStateReceiver.OnNetworkStateReceived { // BaseSessionController.OnSessionRestored {

    private NetworkStateReceiver mNetworkStateReceiver;
    private EditText mUsernameEditText;
    private EditText mPasswordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_Dark_NoActionBar);
        super.onCreate(savedInstanceState);
//        restore();
        setContentView(R.layout.activity_login);

        TextInputLayout usernameInput = (TextInputLayout) findViewById(R.id.username_layout);
        TextInputLayout passwordInput = (TextInputLayout) findViewById(R.id.password_layout);
        mUsernameEditText = (EditText) findViewById(R.id.username);
        mPasswordEditText = (EditText) findViewById(R.id.password);
        Button loginButton = (Button) findViewById(R.id.login_button);

        final Typeface robotoRegular = TypefaceUtil.getCustomTypeface(
                getApplicationContext(),
                R.string.roboto_regular
        );

        usernameInput.requestFocus();
        mUsernameEditText.requestFocus();

        //Styling views..
        usernameInput.setTypeface(robotoRegular);
        passwordInput.setTypeface(robotoRegular);
        mUsernameEditText.setTypeface(robotoRegular);
        mPasswordEditText.setTypeface(robotoRegular);

        //Setting listeners..
        mUsernameEditText.setOnFocusChangeListener(this);
        mPasswordEditText.setOnFocusChangeListener(this);
        usernameInput.setOnFocusChangeListener(this);
        loginButton.setOnClickListener(this);

        mNetworkStateReceiver = new NetworkStateReceiver(this);
        final IntentFilter filter = new IntentFilter(NetworkStateReceiver.CONNECTIVITY_CHANGE_ACTION);
        registerReceiver(mNetworkStateReceiver, filter);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        restore();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != mNetworkStateReceiver) {
            unregisterReceiver(mNetworkStateReceiver);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_button:
                final String username = mUsernameEditText.getText().toString().trim();
                final String password = mPasswordEditText.getText().toString().trim();

                doLogin(this, new String[] { username, password }, v);

                break;
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        final int id = v.getId();

        switch (id) {
            case R.id.username:
                if(hasFocus)
                    InputUtil.showKeyboard(this);
                else
                    InputUtil.hideKeyboard(this);
                break;
            case R.id.password:
                if(hasFocus)
                    InputUtil.showKeyboard(this);
                else
                    InputUtil.hideKeyboard(this);
                break;
            case R.id.login_button:
                if(hasFocus)
                    InputUtil.hideKeyboard(this);
                break;
        }
    }

    @Override
    public void checkNetworkState(Context context, Boolean isConnected) {
        if (!isConnected) {
            VerifyConnectionFragment verifyConnection = new VerifyConnectionFragment();
            verifyConnection.setContext(this);
            verifyConnection.show(getSupportFragmentManager(), VerifyConnectionFragment.TAG);
        }
    }

//    @Override
//    public void restore() {
//        BaseSessionController<SessionEntity> sessionController = ControllerFactory.getSessionController();
//        if (sessionController.isLoggedIn(getApplicationContext())) {
//            MenuNavigator.newInstance().navigate(this);
//        }
//    }

    /***
     * Method that checks email and password. After checking it performs login.
     *
     * @param context - the activity where this method is going to be used.
     * @param credentials - the email and password as a String array.
     * @param view - the view used for animations.
     */
    private void doLogin(@NonNull Activity context,
                           @NonNull final String[] credentials,
                           @NonNull final View view) {

        final String email = credentials[0];
        final String password = credentials[1];

        final Validator emailValidator = EmailValidator.newInstance();
        final Validator passwordValidator = PasswordValidator.newInstance();

        if (!emailValidator.validate(email) && !passwordValidator.validate(password)) {
            Snackbar.make(view, context.getString(R.string.email_password_error), Snackbar.LENGTH_LONG).show();
        } else if (!emailValidator.validate(email)) {
            Snackbar.make(view, context.getString(R.string.email_error), Snackbar.LENGTH_LONG).show();
        } else if (!passwordValidator.validate(password)) {
            Snackbar.make(view, context.getString(R.string.password_error), Snackbar.LENGTH_LONG).show();
        } else {
            final AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AppTheme_Dialog);
            builder.setView(R.layout.authentication_dialog);
            builder.setCancelable(true);

            final AlertDialog alertDialog = builder.create();
            alertDialog.show();

            MenuNavigator.newInstance().navigateWithAnimation(LoginActivity.this, view);

//            try {
//                Thread.sleep(2000);
//                NetworkRequest request = new NetworkRequest(
//                        URLHelper.buildLoginUrl(getApplicationContext()),
//                        new Response.Listener<NetworkResponse>() {
//                            @Override
//                            public void onResponse(NetworkResponse response) {
//                                if(null != response) {
//                                    if (response.statusCode == 200) {
//                                        alertDialog.dismiss();
//
//                                        final Calendar calendar = Calendar.getInstance(Locale.getDefault());
//                                        final String month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
//                                        final String year = String.valueOf(calendar.get(Calendar.YEAR));
//
//                                        startFetchPersonService(credentials[0], credentials[1]);
////                                        startFetchProjectService(credentials[0], credentials[1]);
////                                        startFetchJobLogService(credentials[0], credentials[1], month, year);
//
//                                        //fetchWorkingData(requestQueue, view, credentials);
//                                        MenuNavigator.newInstance().navigateWithAnimation(LoginActivity.this, view);
//                                    }
//                                }
//                            }
//                        },
//                        new Response.ErrorListener() {
//                            @Override
//                            public void onErrorResponse(VolleyError error) {
//                                alertDialog.dismiss();
//                                Log.e(LOG_TAG, error.getMessage(), error.getCause());
//                                if (null != error.networkResponse) {
//                                    final int statusCode = error.networkResponse.statusCode;
//
//                                    if (statusCode == 401) {
//                                        alertDialog.dismiss();
//                                        Snackbar.make(view, "Invalid credentials!!", Snackbar.LENGTH_SHORT).show();
//                                    }
//                                }
//                            }
//                        },
//                        credentials
//                );
//
//                request.setShouldCache(true);
//                request.setRetryPolicy(new DefaultRetryPolicy());
//                requestQueue.add(request);
//            } catch (InterruptedException ex) {
//                Log.e(LOG_TAG, ex.getMessage(), ex.getCause());
//            }
        }
    }

//    private void startFetchPersonService(String username, String password) {
//        Intent intentService = new Intent(Intent.ACTION_SYNC, null, this, FetchPersonDataService.class);
//
//        intentService.putExtra(URL, URLHelper.buildShowPersonUrl(getApplicationContext(), username))
//                     .putExtra(USERNAME, username)
//                     .putExtra(PASSWORD, password);
//
//        startService(intentService);
//    }

//    private void startFetchProjectService(String username, String password) {
//        Intent intentService = new Intent(Intent.ACTION_SYNC, null, this, FetchProjectDataService.class);
//
//        intentService.putExtra(URL, URLHelper.buildAllProjectsByUsernameUrl(getApplicationContext(), username))
//                     .putExtra(USERNAME, username)
//                     .putExtra(PASSWORD, password);
//
//        startService(intentService);
//    }

//    private void startFetchJobLogService(String username, String password, String month, String year) {
//        Intent intent = new Intent(Intent.ACTION_SYNC, null, this, FetchJobLogDataService.class);
//
//        intent.putExtra(URL, URLHelper.buildAllJobLogsUrl(getApplicationContext()))
//              .putExtra(USERNAME, username)
//              .putExtra(PASSWORD, password)
//              .putExtra(MONTH, month)
//              .putExtra(YEAR, year);
//
//        startService(intent);
//    }
}
