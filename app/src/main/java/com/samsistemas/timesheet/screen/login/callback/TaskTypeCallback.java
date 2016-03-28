package com.samsistemas.timesheet.screen.login.callback;

import android.support.annotation.NonNull;

import com.samsistemas.timesheet.domain.TaskType;
import com.samsistemas.timesheet.screen.login.callback.base.BaseCallback;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * @author jonatan.salas
 */
public class TaskTypeCallback extends BaseCallback<List<TaskType>> {
    private final String workPosition;

    public TaskTypeCallback(@NonNull String username, @NonNull String password, @NonNull String workPosition) {
        super(username, password);
        this.workPosition = workPosition;
    }

    @Override
    public List<TaskType> execute() {
        final Call<List<TaskType>> response = getService().findTaskTypesByWorkPosition(workPosition);
        List<TaskType> taskTypes = null;

        try {
            final Response<List<TaskType>> resp = response.execute();
            taskTypes = resp.body();
        } catch (IOException ex){
            ex.printStackTrace();
        }

        return taskTypes;
    }
}
