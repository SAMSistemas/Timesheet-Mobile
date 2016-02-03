package com.samsistemas.timesheet.Login.listener;

/**
 * @author jonatan.salas
 */
public interface OnLoginFinishedListener {
    void onUsernameError();

    void onPasswordError();

    void onSuccess();
}
