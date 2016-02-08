package com.samsistemas.timesheet.screen.login.presenter;

import com.samsistemas.timesheet.domain.Session;

import com.samsistemas.timesheet.screen.login.interactor.LoginInteractorImpl;
import com.samsistemas.timesheet.screen.login.interactor.base.LoginInteractor;
import com.samsistemas.timesheet.screen.login.listener.OnCreateSessionListener;
import com.samsistemas.timesheet.screen.login.listener.OnLoginFinishedListener;
import com.samsistemas.timesheet.screen.login.listener.OnRestoreSessionListener;
import com.samsistemas.timesheet.screen.login.presenter.base.LoginPresenter;
import com.samsistemas.timesheet.screen.login.view.LoginView;

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
        if (loginView != null) {
            loginView.showProgress();
        }

        loginInteractor.login(username, password, this, onCreateSessionListener);
    }

    @Override
    public void onDestroy() {
        loginView = null;
    }

    @Override
    public void restoreUserSession(final OnRestoreSessionListener listener) {
        if (null != listener) {
            final Long id = listener.onSessionRestore();
            final Session session = Session.findById(Session.class, id);

            if (session != null) {
                if (session.getActive() && loginView != null) {
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
