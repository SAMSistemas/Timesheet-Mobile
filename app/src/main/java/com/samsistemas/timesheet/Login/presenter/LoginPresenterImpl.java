package com.samsistemas.timesheet.Login.presenter;

import com.samsistemas.timesheet.Login.interactor.LoginInteractorImpl;
import com.samsistemas.timesheet.Login.interactor.base.LoginInteractor;
import com.samsistemas.timesheet.Login.listener.OnLoginFinishedListener;
import com.samsistemas.timesheet.Login.presenter.base.LoginPresenter;
import com.samsistemas.timesheet.Login.view.LoginView;

/**
 * @author jonatan.salas
 */
public class LoginPresenterImpl implements LoginPresenter, OnLoginFinishedListener {
    private LoginInteractor loginInteractor;
    private LoginView loginView;

    public LoginPresenterImpl(LoginView loginView) {
        this.loginInteractor = new LoginInteractorImpl();
        this.loginView = loginView;
    }

    @Override
    public void validateCredentials(String username, String password) {
        if (loginView != null) {
            loginView.showProgress();
        }

        loginInteractor.login(username, password, this);
    }

    @Override
    public void onDestroy() {
        loginView = null;
    }

    @Override
    public void onUsernameError() {
        if (loginView != null) {
            loginView.setUsernameError();
            loginView.hideProgress();
        }
    }

    @Override
    public void onPasswordError() {
        if (loginView != null) {
            loginView.setPasswordError();
            loginView.hideProgress();
        }
    }

    @Override
    public void onSuccess() {
        if (loginView != null) {
            loginView.navigateToHome();
        }
    }
}
