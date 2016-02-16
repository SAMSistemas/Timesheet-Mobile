package com.samsistemas.timesheet.view.login.interactor.base;

import com.samsistemas.timesheet.view.login.listener.OnCreateSessionListener;
import com.samsistemas.timesheet.view.login.listener.OnLoginFinishedListener;

/**
 * @author jonatan.salas
 */
public interface LoginInteractor {

    void login(String username, String password, OnLoginFinishedListener listener, OnCreateSessionListener sessionListener);

    void createUserSessionIfNotExits(OnCreateSessionListener sessionListener);
}
