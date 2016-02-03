package com.samsistemas.timesheet.Login.presenter.base;

/**
 * @author jonatan.salas
 */
public interface LoginPresenter {
    void validateCredentials(String username, String password);

    void onDestroy();
}
