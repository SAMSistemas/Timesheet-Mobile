package com.samsistemas.timesheet.view.login.presenter;

import com.samsistemas.timesheet.common.utility.ThreadUtility;
import com.samsistemas.timesheet.domain.Session;

import com.samsistemas.timesheet.view.login.interactor.LoginInteractorImpl;
import com.samsistemas.timesheet.view.login.interactor.base.LoginInteractor;
import com.samsistemas.timesheet.view.login.listener.OnCreateSessionListener;
import com.samsistemas.timesheet.view.login.listener.OnLoginFinishedListener;
import com.samsistemas.timesheet.view.login.listener.OnRestoreSessionListener;
import com.samsistemas.timesheet.view.login.presenter.base.LoginPresenter;
import com.samsistemas.timesheet.view.login.view.LoginView;

/**
 * @author jonatan.salas
 */
public class LoginPresenterImpl implements LoginPresenter, OnLoginFinishedListener {
    private OnCreateSessionListener onCreateSessionListener;
    private LoginInteractor loginInteractor;
    private LoginView loginView;

    public LoginPresenterImpl() {
        this.loginInteractor = new LoginInteractorImpl();
    }

    @Override
    public void validateCredentials(String username, String password) {
        if (loginView != null && loginView.checkConnectivity()) {
            loginView.showProgress();
        }

        if (loginView != null && loginView.checkConnectivity()) {
            loginInteractor.login(username, password, this, onCreateSessionListener);
        } else if (loginView != null && !loginView.checkConnectivity()) {
            loginView.showInternetSettings();
        }
    }

    @Override
    public void onDestroy() {
        loginView = null;
    }

    @Override
    public void restoreUserSession(final OnRestoreSessionListener listener) {
        if (null != listener) {
            Session session = ThreadUtility.runInBackGround(new ThreadUtility.CallBack<Session>() {
                @Override
                public Session execute() {
                    return Session.findById(Session.class, listener.onSessionRestore());
                }
            });

            if (null != session && session.getActive()) {
                if (loginView != null) {
                    loginView.navigateToHome();
                }
            }
        }

    }

    @Override
    public void setLoginView(LoginView loginView) {
        this.loginView = loginView;
    }

    @Override
    public void setOnCreateSessionListener(OnCreateSessionListener listener) {
        this.onCreateSessionListener = listener;
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
    public void onLoginSuccess() {
        if (loginView != null) {
            loginView.navigateToHome();
        }
    }
}
