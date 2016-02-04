package com.samsistemas.timesheet.login.interactor;

import android.os.Handler;

import com.samsistemas.timesheet.login.interactor.base.LoginInteractor;
import com.samsistemas.timesheet.login.listener.OnLoginFinishedListener;
import com.samsistemas.timesheet.login.validation.EmailValidator;
import com.samsistemas.timesheet.login.validation.PasswordValidator;

/**
 * @author jonatan.salas
 */
public class LoginInteractorImpl implements LoginInteractor {

    @Override
    public void login(final String username, final String password, final OnLoginFinishedListener listener) {
        new Handler().postDelayed(new Runnable() {
            @Override public void run() {
                boolean error = false;
                if (!EmailValidator.newInstance().validate(username)){
                    listener.onUsernameError();
                    error = true;
                }
                if (!PasswordValidator.newInstance().validate(password)){
                    listener.onPasswordError();
                    error = true;
                }
                if (!error){
                    listener.onSuccess();
                }
            }
        }, 2000);
    }
}
