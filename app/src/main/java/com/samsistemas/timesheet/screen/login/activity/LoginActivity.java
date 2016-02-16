package com.samsistemas.timesheet.screen.login.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.IntentCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.Theme;

import com.samsistemas.timesheet.screen.login.fragment.VerifyConnectionFragment;
import com.samsistemas.timesheet.utility.DeveloperUtility;
import com.samsistemas.timesheet.utility.NetworkUtility;
import com.samsistemas.timesheet.utility.PreferenceUtility;
import com.samsistemas.timesheet.utility.ThreadUtility;
import com.samsistemas.timesheet.screen.login.listener.OnCreateSessionListener;
import com.samsistemas.timesheet.screen.login.listener.OnRestoreSessionListener;
import com.samsistemas.timesheet.screen.login.presenter.LoginPresenterImpl;
import com.samsistemas.timesheet.screen.login.presenter.base.LoginPresenter;
import com.samsistemas.timesheet.screen.login.view.LoginView;
import com.samsistemas.timesheet.R;
import com.samsistemas.timesheet.screen.menu.activity.MenuActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author jonatan.salas
 */
public class LoginActivity extends AppCompatActivity implements LoginView, OnCreateSessionListener,
        OnRestoreSessionListener, View.OnClickListener {
    private LoginPresenter loginPresenter;
    private MaterialDialog dialog;

    @Bind(R.id.username) EditText username;

    @Bind(R.id.password) EditText password;

    @Bind(R.id.login) Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_Dark_NoActionBar);
        ThreadUtility.sleep(1000);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        DeveloperUtility.enableStrictModeApi(true);

        login.setOnClickListener(this);

        loginPresenter = new LoginPresenterImpl();
        loginPresenter.setLoginView(this);
        loginPresenter.setOnCreateSessionListener(this);
        loginPresenter.restoreUserSession(this);
    }

    @Override
    protected void onDestroy() {
        if (dialog != null) {
            dialog.dismiss();
        }

        loginPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void showProgress() {
        dialog = new MaterialDialog.Builder(this)
                .content(R.string.login_dialog_message)
                .progress(true, 0)
                .theme(Theme.LIGHT)
                .build();

        dialog.show();
    }

    @Override
    public void hideProgress() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    @Override
    public void setUsernameError() {
        Snackbar.make(username, getString(R.string.email_error), Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void setPasswordError() {
        Snackbar.make(password, getString(R.string.password_error), Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void navigateToHome() {
        final Intent intent = new Intent(getApplicationContext(), MenuActivity.class);

        intent.setFlags(
                Intent.FLAG_ACTIVITY_NEW_TASK |
                IntentCompat.FLAG_ACTIVITY_CLEAR_TASK |
                Intent.FLAG_ACTIVITY_CLEAR_TOP
        );

        startActivity(intent);
        finish();
    }

    @Override
    public boolean checkConnectivity() {
        return NetworkUtility.isConnected(getApplicationContext());
    }

    @Override
    public void showInternetSettings() {
        final VerifyConnectionFragment fragment = new VerifyConnectionFragment();
        fragment.setContext(this);
        fragment.show(getSupportFragmentManager(), VerifyConnectionFragment.TAG);
    }

    @Override
    public void onSessionCreate(Long id) {
        PreferenceUtility.setSessionId(getApplicationContext(), id);
    }

    @Override
    public Long onSessionRestore() {
        return PreferenceUtility.getSessionId(getApplicationContext());
    }

    @Override
    public void onClick(View v) {
        final String user = username.getText().toString().trim();
        final String pass = password.getText().toString().trim();

        loginPresenter.validateCredentials(user, pass);
    }
}
