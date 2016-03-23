package com.samsistemas.timesheet.screen.login.callback;

import android.support.annotation.NonNull;

import com.samsistemas.timesheet.screen.login.callback.base.BaseCallback;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

/**
 * @author jonatan.salas
 */
public class LoginCallBack extends BaseCallback<Boolean> {

    public LoginCallBack(@NonNull String username, @NonNull String password) {
        super(username, password);
    }

    @Override
    public Boolean execute() {
        final Call<Void> response = getService().login(getUsername(), getPassword());
        Boolean login = false;

        try {
            final Response<Void> resp = response.execute();
            final Integer statusCode = resp.code();
            login = (statusCode == 200);
        } catch (IOException ex){
            ex.printStackTrace();
        }

        return login;
    }
}
