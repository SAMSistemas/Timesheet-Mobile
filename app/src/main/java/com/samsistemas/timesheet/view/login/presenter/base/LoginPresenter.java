package com.samsistemas.timesheet.view.login.presenter.base;

import com.samsistemas.timesheet.view.login.listener.OnCreateSessionListener;
import com.samsistemas.timesheet.view.login.listener.OnRestoreSessionListener;
import com.samsistemas.timesheet.view.login.view.LoginView;

/**
 * @author jonatan.salas
 */
public interface LoginPresenter {

    void validateCredentials(final String username, final String password);

    void onDestroy();

    void restoreUserSession(OnRestoreSessionListener listener);

    void setOnCreateSessionListener(OnCreateSessionListener listener);

    void setLoginView(LoginView loginView);
}
