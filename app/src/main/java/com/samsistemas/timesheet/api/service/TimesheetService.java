package com.samsistemas.timesheet.api.service;

import android.support.annotation.NonNull;

import com.samsistemas.timesheet.domain.Person;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @author jonatan.salas
 */
public interface TimesheetService {

    @POST("/login?")
    Call<Void> login(@Query("username") @NonNull final String username,
                     @Query("password") @NonNull final String password);

    @GET("/people?")
    Call<ArrayList<Person>> findPersonByUsername(@Query("username") @NonNull final String username);
}
