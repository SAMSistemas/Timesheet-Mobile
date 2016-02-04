package com.samsistemas.timesheet.login.presenter.base;

/**
 * @author jonatan.salas
 */
public interface LoginPresenter {

    void validateCredentials(final String username, final String password);

    void onDestroy();
}
