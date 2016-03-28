package com.samsistemas.timesheet.api.service;

import android.support.annotation.NonNull;

import com.samsistemas.timesheet.domain.Client;
import com.samsistemas.timesheet.domain.JobLog;
import com.samsistemas.timesheet.domain.Person;
import com.samsistemas.timesheet.domain.Project;
import com.samsistemas.timesheet.domain.TaskType;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @author jonatan.salas
 */
public interface TimesheetService {

    @POST("/login?")
    Call<Void> login(@Query("username") @NonNull final String username, @Query("password") @NonNull final String password);

    @GET("/people?")
    Call<List<Person>> findPersonsByUsername(@Query("username") @NonNull final String username);

    @GET("/projects?")
    Call<List<Project>> findProjectsByUsername(@Query("username") @NonNull final String username);

    @GET("/clients?")
    Call<List<Client>> findClientsByName(@Query("name") @NonNull final String name);

    @GET("/jobLogs")
    Call<List<JobLog>> findJobLogsByUsername(@Query("username") @NonNull final String username);

    @POST("/jobLogs")
    Call<JobLog> createJobLog(@Body @NonNull final JobLog jobLogToCreate);

    @PUT("/jobLogs/{id}")
    Call<Void> updateJobLogWithId(@Path("id") @NonNull final Integer id, @Body @NonNull final JobLog jobLogToUpdate);

    @GET("/taskTypes")
    Call<List<TaskType>> findTaskTypesByWorkPosition(@Query("work_position") @NonNull final String workPosition);
}
