package com.samsistemas.timesheet.Login.activity;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.Theme;

import com.samsistemas.timesheet.Login.presenter.LoginPresenterImpl;
import com.samsistemas.timesheet.Login.presenter.base.LoginPresenter;
import com.samsistemas.timesheet.Login.view.LoginView;
import com.samsistemas.timesheet.R;
import com.samsistemas.timesheet.navigation.MenuNavigator;

import butterknife.Bind;

/**
 * @author jonatan.salas
 */
public class LoginActivity extends AppCompatActivity implements LoginView, View.OnClickListener {

    @Bind(R.id.username)
    EditText username;

    @Bind(R.id.password)
    EditText password;

    private MaterialDialog progressDialog;
    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_Dark_NoActionBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final Button login = (Button) findViewById(R.id.login);
        login.setOnClickListener(this);

        loginPresenter = new LoginPresenterImpl(this);
    }

    @Override
    protected void onDestroy() {
        loginPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void showProgress() {
        progressDialog = new MaterialDialog.Builder(this)
                .title("Iniciando")
                .content(R.string.login_dialog_message)
                .progress(true, 0)
                .theme(Theme.LIGHT)
                .build();

        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
        progressDialog = null;
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
        MenuNavigator.newInstance().navigate(this);
    }

    @Override
    public void onClick(View v) {
        final String user = username.getText().toString().trim();
        final String pass = password.getText().toString().trim();

        loginPresenter.validateCredentials(user, pass);
    }
}
