package com.samsistemas.timesheet.screen.login.listener;

/**
 * @author jonatan.salas
 */
public interface OnLoginFinishedListener {

    void onUsernameError();

    void onPasswordError();

    void onLoginSuccess();

    void onLoginFailure();
}
