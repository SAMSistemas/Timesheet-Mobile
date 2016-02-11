package com.samsistemas.timesheet.screen.login.interactor;

import android.os.Handler;

import com.samsistemas.timesheet.domain.Person;
import com.samsistemas.timesheet.domain.Session;
import com.samsistemas.timesheet.domain.WorkPosition;

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
    public void login(final String username, final  String password,
                      final OnLoginFinishedListener listener,
                      final  OnCreateSessionListener sessionListener) {
        new Handler().postDelayed(new Runnable() {
            @Override public void run() {
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
                    createUserSessionIfNotExits(sessionListener);
                    listener.onLoginSuccess();
                }

            }
        }, 2000);
    }

    @Override
    public void createUserSessionIfNotExits(OnCreateSessionListener sessionListener) {
        if (null != sessionListener) {
            //TODO JS: get person data, save it and add a new user session.
            //Verify internet connection and everything else.
            WorkPosition workPosition = new WorkPosition();
            workPosition.setDescription("Android Developer")
                    .setServerId(1L);

            WorkPosition.save(workPosition);

            Person person = new Person();
            person.setEnabled(true)
                    .setName("Joni")
                    .setServerId(1L)
                    .setLastName("Salas")
                    .setPassword("nbbbbbbb")
                    .setPicture(null)
                    .setUsername("jonisaa")
                    .setWorkHours(6)
                    .setWorkPosition(workPosition);

            Person.save(person);

            Session session = new Session();
            session.setActive(true);
            session.setPerson(person);

            sessionListener.onSessionCreate(Session.save(session));
        }
    }
}
