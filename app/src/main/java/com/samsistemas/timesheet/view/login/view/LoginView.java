package com.samsistemas.timesheet.view.login.view;

/**
 * @author jonatan.salas
 */
public interface LoginView {

    void showProgress();

    void hideProgress();

    void setUsernameError();

    void setPasswordError();

    void navigateToHome();

    boolean checkConnectivity();

    void showInternetSettings();
}
