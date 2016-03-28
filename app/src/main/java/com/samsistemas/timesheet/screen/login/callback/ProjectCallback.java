package com.samsistemas.timesheet.screen.login.callback;

import android.support.annotation.NonNull;

import com.samsistemas.timesheet.domain.Project;
import com.samsistemas.timesheet.screen.login.callback.base.BaseCallback;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * @author jonatan.salas
 */
public class ProjectCallback extends BaseCallback<List<Project>> {

    public ProjectCallback(@NonNull String username, @NonNull String password) {
        super(username, password);
    }

    @Override
    public List<Project> execute() {
        final Call<List<Project>> response = getService().findProjectsByUsername(getUsername());
        List<Project> projects = null;

        try {
            final Response<List<Project>> resp = response.execute();
            projects = resp.body();
        } catch (IOException ex){
            ex.printStackTrace();
        }

        return projects;
    }
}
