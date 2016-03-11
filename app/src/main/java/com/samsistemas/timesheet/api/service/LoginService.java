package com.samsistemas.timesheet.api.service;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @author jonatan.salas
 */
public interface LoginService {

    @POST("/login")
    Call<Void> login(@Query("username") String username, @Query("password") String password);
}
