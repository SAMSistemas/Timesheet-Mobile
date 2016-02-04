package com.samsistemas.timesheet.login.listener;

/**
 * @author jonatan.salas
 */
public interface OnLoginFinishedListener {

    void onUsernameError();

    void onPasswordError();

    void onSuccess();
}
