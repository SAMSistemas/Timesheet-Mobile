package com.samsistemas.timesheet.screen.settings.presenter.base;

import com.samsistemas.timesheet.screen.settings.view.SettingsView;

/**
 * @author jonatan.salas
 */
public interface SettingsPresenter {

    void logout(final Long sessionId);

    void onDestroy();

    void setSettingsView(SettingsView settingsView);
}
