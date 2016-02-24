package com.samsistemas.timesheet.screen.settings.presenter;

import android.support.annotation.NonNull;

import com.jonisaa.commons.presenter.BasePresenter;
import com.samsistemas.timesheet.screen.settings.interactor.SettingsInteractorImpl;
import com.samsistemas.timesheet.screen.settings.interactor.base.SettingsInteractor;
import com.samsistemas.timesheet.screen.settings.listener.OnLogoutFinishedListener;
import com.samsistemas.timesheet.screen.settings.view.SettingsView;

/**
 * @author jonatan.salas
 */
public class SettingsPresenter extends BasePresenter<SettingsView>
        implements OnLogoutFinishedListener {
    private SettingsInteractor settingsInteractor;

    private SettingsPresenter(@NonNull SettingsView view) {
        super(view);
        this.settingsInteractor = new SettingsInteractorImpl();
    }

    public void logout(Long sessionId) {
        settingsInteractor.logout(sessionId, this);
    }

    @Override
    public void onLogoutError() {
        if (getView() != null) {
            getView().showLogoutError();
        }
    }

    @Override
    public void onLogoutSuccess() {
        if (getView() != null) {
            getView().navigateToLogin();
        }
    }

    public static SettingsPresenter getInstance(@NonNull SettingsView view) {
        return new SettingsPresenter(view);
    }
}
