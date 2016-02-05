package com.samsistemas.timesheet.login.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.IntentCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.Theme;

import com.samsistemas.timesheet.login.listener.OnCreateSessionListener;
import com.samsistemas.timesheet.login.listener.OnRestoreSessionListener;
import com.samsistemas.timesheet.login.presenter.LoginPresenterImpl;
import com.samsistemas.timesheet.login.presenter.base.LoginPresenter;
import com.samsistemas.timesheet.login.view.LoginView;
import com.samsistemas.timesheet.R;
import com.samsistemas.timesheet.common.activity.MenuActivity;
import com.samsistemas.timesheet.common.animation.ScaleUpAnimator;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author jonatan.salas
 */
public class LoginActivity extends AppCompatActivity implements LoginView, OnCreateSessionListener,
        OnRestoreSessionListener, View.OnClickListener {

    private static final String PREFERENCE_FILENAME="timesheet_prefs";
    private static final String SESSION_KEY = "session_id";

    private LoginPresenter loginPresenter;
    private MaterialDialog dialog;

    @Bind(R.id.username)
    EditText username;

    @Bind(R.id.password)
    EditText password;

    @Bind(R.id.login)
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_Dark_NoActionBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

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
        final Bundle options = ScaleUpAnimator.newInstance().saveAnimation(login);
        final Intent intent = new Intent(getApplicationContext(), MenuActivity.class);

        intent.setFlags(
                Intent.FLAG_ACTIVITY_NEW_TASK |
                IntentCompat.FLAG_ACTIVITY_CLEAR_TASK |
                Intent.FLAG_ACTIVITY_CLEAR_TOP
        );

        ActivityCompat.startActivity(this, intent, options);
        finish();
    }

    @Override
    public void onSessionCreate(Long id) {
        final SharedPreferences preferences = getSharedPreferences(
                PREFERENCE_FILENAME,
                Context.MODE_PRIVATE
        );

        final SharedPreferences.Editor editor = preferences.edit();

        editor.putLong(SESSION_KEY, id);
        editor.apply();
    }

    @Override
    public Long onSessionRestore() {
        final SharedPreferences preferences = getSharedPreferences(
                PREFERENCE_FILENAME,
                Context.MODE_PRIVATE
        );

        return preferences.getLong(SESSION_KEY, 0L);
    }

    @Override
    public void onClick(View v) {
        final String user = username.getText().toString().trim();
        final String pass = password.getText().toString().trim();

        loginPresenter.validateCredentials(user, pass);
    }
}
