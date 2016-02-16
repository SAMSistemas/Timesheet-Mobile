package com.samsistemas.timesheet.view.account.presenter;

import com.samsistemas.timesheet.common.utility.ThreadUtility;
import com.samsistemas.timesheet.domain.Person;
import com.samsistemas.timesheet.domain.Session;
import com.samsistemas.timesheet.view.account.presenter.base.AccountPresenter;
import com.samsistemas.timesheet.view.account.view.AccountView;

/**
 * @author jonatan.salas
 */
public class AccountPresenterImpl implements AccountPresenter {
    private AccountView accountView;

    public AccountPresenterImpl() { }

    @Override
    public void styleBar(String title) {
        if (null != accountView) {
            accountView.styleActionBar(title);
        }
    }

    @Override
    public void setAccountData(final Long sessionId) {
        final Session session = ThreadUtility.runInBackGround(new ThreadUtility.CallBack<Session>() {
            @Override
            public Session execute() {
                return Session.findById(Session.class, sessionId);
            }
        });

        Person person = null;

        if (null != session && session.getActive()) {
            person = session.getPerson();
        }

        if (null != accountView) {
            accountView.bindAccountInfo(person);
        }
    }

    @Override
    public void onDestroy() {
        this.accountView = null;
    }

    @Override
    public void onBackPressed() {
        if (null != accountView) {
            accountView.navigateToHome();
        }
    }

    @Override
    public void setAccountView(AccountView accountView) {
        this.accountView = accountView;
    }
}
