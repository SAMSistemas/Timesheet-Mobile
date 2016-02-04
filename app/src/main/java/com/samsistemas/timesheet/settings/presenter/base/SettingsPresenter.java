package com.samsistemas.timesheet.settings.presenter.base;

import com.samsistemas.timesheet.settings.view.SettingsView;

/**
 * @author jonatan.salas
 */
public interface SettingsPresenter {

    void logout(final Long sessionId);

    void onDestroy();

    void setSettingsView(SettingsView settingsView);
}
