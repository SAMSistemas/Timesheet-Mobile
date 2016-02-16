package com.samsistemas.timesheet.view.login.listener;

/**
 * @author jonatan.salas
 */
public interface OnLoginFinishedListener {

    void onUsernameError();

    void onPasswordError();

    void onLoginSuccess();
}
