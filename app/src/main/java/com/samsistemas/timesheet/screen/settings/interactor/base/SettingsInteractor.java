package com.samsistemas.timesheet.screen.settings.interactor.base;

import com.samsistemas.timesheet.screen.settings.listener.OnLogoutFinishedListener;

/**
 * @author jonatan.salas
 */
public interface SettingsInteractor {

    void logout(final Long sessionId, OnLogoutFinishedListener listener);
}
