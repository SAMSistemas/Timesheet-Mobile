package com.samsistemas.timesheet.view.settings.interactor.base;

import com.samsistemas.timesheet.view.settings.listener.OnLogoutFinishedListener;

/**
 * @author jonatan.salas
 */
public interface SettingsInteractor {

    void logout(final Long sessionId, OnLogoutFinishedListener listener);
}
