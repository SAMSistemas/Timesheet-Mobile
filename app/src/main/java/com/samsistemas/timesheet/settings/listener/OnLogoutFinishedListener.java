package com.samsistemas.timesheet.settings.listener;

/**
 * @author jonatan.salas
 */
public interface OnLogoutFinishedListener {

    void onLogoutError();

    void onLogoutSuccess();
}
