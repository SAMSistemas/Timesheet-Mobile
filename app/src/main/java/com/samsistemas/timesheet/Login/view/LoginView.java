package com.samsistemas.timesheet.Login.view;

/**
 * @author jonatan.salas
 */
public interface LoginView {
    void showProgress();

    void hideProgress();

    void setUsernameError();

    void setPasswordError();

    void navigateToHome();
}
