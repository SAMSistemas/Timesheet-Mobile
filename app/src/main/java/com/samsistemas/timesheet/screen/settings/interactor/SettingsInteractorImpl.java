package com.samsistemas.timesheet.screen.settings.interactor;

import android.os.Handler;

import com.samsistemas.timesheet.domain.Session;
import com.samsistemas.timesheet.screen.settings.interactor.base.SettingsInteractor;
import com.samsistemas.timesheet.screen.settings.listener.OnLogoutFinishedListener;

/**
 * @author jonatan.salas
 */
public class SettingsInteractorImpl implements SettingsInteractor {

    @Override
    public void logout(final Long sessionId, final OnLogoutFinishedListener listener) {
        new Handler().postDelayed(new Runnable() {
            @Override public void run() {
                boolean error = false;
                if (sessionId == null) {
                    listener.onLogoutError();
                    error = true;
                }

                if (!error) {
                    Session session = Session.findById(Session.class, sessionId);
                    Session.delete(session);
                    listener.onLogoutSuccess();
                }
            }
        }, 0);
    }
}
