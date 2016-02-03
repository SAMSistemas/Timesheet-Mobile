package com.samsistemas.timesheet.Login.interactor.base;

import com.samsistemas.timesheet.Login.listener.OnLoginFinishedListener;

/**
 * @author jonatan.salas
 */
public interface LoginInteractor {

    void login(String username, String password, OnLoginFinishedListener listener);
}
