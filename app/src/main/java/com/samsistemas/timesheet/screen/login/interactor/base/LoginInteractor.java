package com.samsistemas.timesheet.screen.login.interactor.base;

import com.samsistemas.timesheet.screen.login.listener.OnCreateSessionListener;
import com.samsistemas.timesheet.screen.login.listener.OnLoginFinishedListener;

/**
 * @author jonatan.salas
 */
public interface LoginInteractor {

    void login(String username, String password, OnLoginFinishedListener listener, OnCreateSessionListener sessionListener);
}
