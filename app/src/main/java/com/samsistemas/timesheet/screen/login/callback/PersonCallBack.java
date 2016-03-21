package com.samsistemas.timesheet.screen.login.callback;

import android.support.annotation.NonNull;

import com.samsistemas.timesheet.domain.Person;
import com.samsistemas.timesheet.screen.login.callback.base.BaseCallback;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;

/**
 * @author jonatan.salas
 */
public class PersonCallBack extends BaseCallback<ArrayList<Person>> {

    public PersonCallBack(@NonNull String username, @NonNull String password) {
        super(username, password);
    }

    @Override
    public ArrayList<Person> execute() {
        final Call<ArrayList<Person>> response = getService().findPersonByUsername(getUsername());
        ArrayList<Person> persons = null;

        try {
            final Response<ArrayList<Person>> resp = response.execute();
            persons = resp.body();
        } catch (IOException ex){
            ex.printStackTrace();
        }

        return persons;
    }
}
