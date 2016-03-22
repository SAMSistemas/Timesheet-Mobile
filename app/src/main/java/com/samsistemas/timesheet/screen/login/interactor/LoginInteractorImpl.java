package com.samsistemas.timesheet.screen.login.interactor;

import com.orm.SugarRecord;
import com.samsistemas.timesheet.screen.login.callback.LoginCallBack;
import com.samsistemas.timesheet.screen.login.callback.PersonCallBack;
import com.samsistemas.timesheet.utility.ThreadUtility;
import com.samsistemas.timesheet.domain.Person;
import com.samsistemas.timesheet.domain.Session;

import com.samsistemas.timesheet.screen.login.interactor.base.LoginInteractor;
import com.samsistemas.timesheet.screen.login.listener.OnCreateSessionListener;
import com.samsistemas.timesheet.screen.login.listener.OnLoginFinishedListener;
import com.samsistemas.timesheet.screen.login.validation.EmailValidator;
import com.samsistemas.timesheet.screen.login.validation.PasswordValidator;

/**
 * @author jonatan.salas
 */
public class LoginInteractorImpl implements LoginInteractor {

    @Override
    public void login(final String username,
                      final String password,
                      final OnLoginFinishedListener listener,
                      final OnCreateSessionListener sessionListener) {
        boolean error = false;

        if (!EmailValidator.newInstance().validate(username)) {
            listener.onUsernameError();
            error = true;
        }

        if (!PasswordValidator.newInstance().validate(password)) {
            listener.onPasswordError();
            error = true;
        }

        if (!error) {
            final LoginCallBack loginCallBack = new LoginCallBack(username, password);
            final Boolean login = ThreadUtility.runInBackground(loginCallBack);

            if (login) {
                if (null != sessionListener) {
                    final Person person = ThreadUtility.runInBackground(new PersonCallBack(username, password));
                    SugarRecord.save(person);

                    final Session session = new Session()
                            .setActive(true)
                            .setPerson(person);

                    final Long id = SugarRecord.save(session);

//                            ThreadUtility.runInBackground(new ThreadUtility.CallBack<Long>() {
//                        @Override
//                        public Long execute() {
//                            Session session = new Session()
//                                    .setActive(true)
//                                    .setPerson(person);
//
//                            return SugarRecord.save(session);
//                        }
//                    });

                    sessionListener.onSessionCreate(id);
                }

                listener.onLoginSuccess();
            } else {
                listener.onLoginFailure();
            }
        }
    }
}
