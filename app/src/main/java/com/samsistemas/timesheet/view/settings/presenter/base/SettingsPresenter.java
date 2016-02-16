package com.samsistemas.timesheet.view.settings.presenter.base;

import com.samsistemas.timesheet.view.settings.view.SettingsView;

/**
 * @author jonatan.salas
 */
public interface SettingsPresenter {

    void logout(final Long sessionId);

    void onDestroy();

    void setSettingsView(SettingsView settingsView);
}
