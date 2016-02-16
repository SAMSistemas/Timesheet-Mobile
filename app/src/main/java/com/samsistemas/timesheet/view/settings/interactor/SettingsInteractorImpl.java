package com.samsistemas.timesheet.view.settings.interactor;

import com.samsistemas.timesheet.common.utility.ThreadUtility;
import com.samsistemas.timesheet.domain.Session;
import com.samsistemas.timesheet.view.settings.interactor.base.SettingsInteractor;
import com.samsistemas.timesheet.view.settings.listener.OnLogoutFinishedListener;

/**
 * @author jonatan.salas
 */
public class SettingsInteractorImpl implements SettingsInteractor {

    @Override
    public void logout(final Long sessionId, final OnLogoutFinishedListener listener) {
        boolean error = false;

        if (sessionId == null) {
            listener.onLogoutError();
            error = true;
        }

        if (!error) {
            final Session session = ThreadUtility.runInBackground(new ThreadUtility.CallBack<Session>() {
                @Override
                public Session execute() {
                    return Session.findById(Session.class, sessionId);
                }
            });

            if (null != session && session.getActive()) {
                ThreadUtility.runInBackground(new ThreadUtility.CallBack<Void>() {
                    @Override
                    public Void execute() {
                        Session.delete(session);
                        return null;
                    }
                });

                listener.onLogoutSuccess();
            }
        }
    }
}
