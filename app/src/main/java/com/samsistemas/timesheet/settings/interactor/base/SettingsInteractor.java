package com.samsistemas.timesheet.settings.interactor.base;

import com.samsistemas.timesheet.settings.listener.OnLogoutFinishedListener;

/**
 * @author jonatan.salas
 */
public interface SettingsInteractor {

    void logout(final Long sessionId, OnLogoutFinishedListener listener);
}
