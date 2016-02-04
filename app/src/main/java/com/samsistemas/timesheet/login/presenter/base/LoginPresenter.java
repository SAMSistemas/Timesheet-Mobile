package com.samsistemas.timesheet.login.presenter.base;

import com.samsistemas.timesheet.login.view.LoginView;

/**
 * @author jonatan.salas
 */
public interface LoginPresenter {

    void validateCredentials(final String username, final String password);

    void onDestroy();

    void setLoginView(LoginView loginView);
}
