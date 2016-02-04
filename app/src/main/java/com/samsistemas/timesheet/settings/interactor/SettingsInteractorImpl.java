package com.samsistemas.timesheet.settings.interactor;

import android.os.Handler;

import com.samsistemas.timesheet.settings.interactor.base.SettingsInteractor;
import com.samsistemas.timesheet.settings.listener.OnLogoutFinishedListener;

/**
 * @author jonatan.salas
 */
public class SettingsInteractorImpl implements SettingsInteractor {

    @Override
    public void logout(final Long sessionId, final OnLogoutFinishedListener listener) {
        new Handler().postDelayed(new Runnable() {
            @Override public void run() {
                boolean error = false;
                if (sessionId == null){
                    listener.onLogoutError();
                    error = true;
                }
                if (!error){
                    listener.onSuccess();
                }
            }
        }, 0);
    }
}
