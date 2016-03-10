package com.samsistemas.timesheet.api.service;

import retrofit.Call;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * @author jonatan.salas
 */
public interface LoginService {

    @POST("/login")
    Call<Void> login(@Query("username") String username, @Query("password") String password);
}
