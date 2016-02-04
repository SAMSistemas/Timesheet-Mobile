package com.samsistemas.timesheet.login.interactor.base;

import com.samsistemas.timesheet.login.listener.OnLoginFinishedListener;

/**
 * @author jonatan.salas
 */
public interface LoginInteractor {

    void login(String username, String password, OnLoginFinishedListener listener);
}
