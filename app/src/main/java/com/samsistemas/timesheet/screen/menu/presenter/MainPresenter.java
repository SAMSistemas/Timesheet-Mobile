package com.samsistemas.timesheet.screen.menu.presenter;

import android.content.Context;
import android.support.annotation.Nullable;

import com.jonisaa.commons.presenter.BasePresenter;

import com.samsistemas.timesheet.domain.Person;
import com.samsistemas.timesheet.domain.Session;
import com.samsistemas.timesheet.screen.menu.view.MainView;
import com.samsistemas.timesheet.utility.PreferenceUtility;
import com.samsistemas.timesheet.utility.ThreadUtility;

/**
 * @author jonatan.salas
 */
public class MainPresenter extends BasePresenter<MainView> {

    public MainPresenter(@Nullable MainView view) {
        super(view);
    }

    public void setUsernameData(Context context) {
        if (null != getView()) {
            final Long id = PreferenceUtility.getSessionId(context);

            final Session session = ThreadUtility.runInBackground(new ThreadUtility.CallBack<Session>() {
                @Override
                public Session execute() {
                    return Session.findById(Session.class, id);
                }
            });

            Person person = null;

            if (null != session && session.getActive()) {
                person = session.getPerson();
            }

            getView().addPersonDataToNavigationDrawer(person);
        }
    }

    public static MainPresenter getInstance(MainView view) {
        return new MainPresenter(view);
    }
}
