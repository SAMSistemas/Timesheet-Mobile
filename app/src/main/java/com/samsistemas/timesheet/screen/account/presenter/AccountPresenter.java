package com.samsistemas.timesheet.screen.account.presenter;

import android.support.annotation.NonNull;

import com.jonisaa.commons.presenter.BasePresenter;
import com.samsistemas.timesheet.utility.ThreadUtility;
import com.samsistemas.timesheet.domain.Person;
import com.samsistemas.timesheet.domain.Session;
import com.samsistemas.timesheet.screen.account.view.AccountView;

/**
 * @author jonatan.salas
 */
public class AccountPresenter extends BasePresenter<AccountView> {

    private AccountPresenter(@NonNull AccountView view) {
        super(view);
    }

    public void styleBar(String title) {
        if (null != getView()) {
            getView().styleActionBar(title);
        }
    }

    public void setAccountData(final Long sessionId) {
        final Session session = ThreadUtility.runInBackground(new ThreadUtility.CallBack<Session>() {
            @Override
            public Session execute() {
                return Session.findById(Session.class, sessionId);
            }
        });

        Person person = null;

        if (null != session && session.getActive()) {
            person = session.getPerson();
        }

        if (null != getView()) {
            getView().bindAccountInfo(person);
        }
    }

    public static AccountPresenter getInstance(@NonNull AccountView view) {
        return new AccountPresenter(view);
    }
}
