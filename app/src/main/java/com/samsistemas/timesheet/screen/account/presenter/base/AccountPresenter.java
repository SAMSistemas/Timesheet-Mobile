package com.samsistemas.timesheet.screen.account.presenter.base;

import com.samsistemas.timesheet.screen.account.view.AccountView;

/**
 * @author jonatan.salas
 */
public interface AccountPresenter {

     void styleBar(String title);

     void setAccountData(Long sessionId);

     void onBackPressed();

     void onDestroy();

     void setAccountView(AccountView accountView);
}
