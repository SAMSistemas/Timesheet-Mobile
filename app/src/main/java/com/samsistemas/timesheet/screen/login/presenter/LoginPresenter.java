package com.samsistemas.timesheet.screen.login.presenter;

import android.support.annotation.NonNull;

import com.jonisaa.commons.presenter.BasePresenter;
import com.samsistemas.timesheet.utility.ThreadUtility;
import com.samsistemas.timesheet.domain.Session;

import com.samsistemas.timesheet.screen.login.interactor.LoginInteractorImpl;
import com.samsistemas.timesheet.screen.login.interactor.base.LoginInteractor;
import com.samsistemas.timesheet.screen.login.listener.OnCreateSessionListener;
import com.samsistemas.timesheet.screen.login.listener.OnLoginFinishedListener;
import com.samsistemas.timesheet.screen.login.listener.OnRestoreSessionListener;
import com.samsistemas.timesheet.screen.login.view.LoginView;

/**
 * @author jonatan.salas
 */
public class LoginPresenter extends BasePresenter<LoginView> implements OnLoginFinishedListener {
    private OnCreateSessionListener onCreateSessionListener;
    private LoginInteractor loginInteractor;

    private LoginPresenter(@NonNull LoginView view) {
        super(view);
        this.loginInteractor = new LoginInteractorImpl();
    }

    public void validateCredentials(String username, String password) {
        if (getView() != null && getView().checkConnectivity()) {
            getView().showProgress();
        }

        if (getView() != null && getView().checkConnectivity()) {
            loginInteractor.login(username, password, this, onCreateSessionListener);
        } else if (getView() != null && !getView().checkConnectivity()) {
            getView().showInternetSettings();
        }
    }

    public void restoreUserSession(final OnRestoreSessionListener listener) {
        if (null != listener) {
            final Session session = ThreadUtility.runInBackground(new ThreadUtility.CallBack<Session>() {
                @Override
                public Session execute() {
                    return Session.findById(Session.class, listener.onSessionRestore());
                }
            });

            if (null != session && session.getActive()) {
                if (getView() != null) {
                    getView().navigateToHome();
                }
            }
        }

    }

    public void setOnCreateSessionListener(OnCreateSessionListener listener) {
        this.onCreateSessionListener = listener;
    }

    @Override
    public void onUsernameError() {
        if (getView() != null) {
            getView().setUsernameError();
            getView().hideProgress();
        }
    }

    @Override
    public void onPasswordError() {
        if (getView() != null) {
            getView().setPasswordError();
            getView().hideProgress();
        }
    }

    @Override
    public void onLoginSuccess() {
        if (getView() != null) {
            getView().navigateToHome();
        }
    }

    @Override
    public void onLoginFailure() {

    }

    public static LoginPresenter getInstance(@NonNull LoginView view) {
        return new LoginPresenter(view);
    }
}
