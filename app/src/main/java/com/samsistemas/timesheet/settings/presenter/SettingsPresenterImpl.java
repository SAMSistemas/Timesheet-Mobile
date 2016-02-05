package com.samsistemas.timesheet.settings.presenter;

import com.samsistemas.timesheet.settings.interactor.SettingsInteractorImpl;
import com.samsistemas.timesheet.settings.interactor.base.SettingsInteractor;
import com.samsistemas.timesheet.settings.listener.OnLogoutFinishedListener;
import com.samsistemas.timesheet.settings.presenter.base.SettingsPresenter;
import com.samsistemas.timesheet.settings.view.SettingsView;

/**
 * @author jonatan.salas
 */
public class SettingsPresenterImpl implements SettingsPresenter, OnLogoutFinishedListener {
    private SettingsInteractor settingsInteractor;
    private SettingsView settingsView;

    public SettingsPresenterImpl() {
        this.settingsInteractor = new SettingsInteractorImpl();
    }

    @Override
    public void logout(Long sessionId) {
        settingsInteractor.logout(sessionId, this);
    }

    @Override
    public void setSettingsView(SettingsView settingsView) {
        this.settingsView = settingsView;
    }

    @Override
    public void onDestroy() {
        settingsView = null;
    }

    @Override
    public void onLogoutError() {
        if (settingsView != null) {
            settingsView.showLogoutError();
        }
    }

    @Override
    public void onLogoutSuccess() {
        if (settingsView != null) {
            settingsView.navigateToLogin();
        }
    }
}
