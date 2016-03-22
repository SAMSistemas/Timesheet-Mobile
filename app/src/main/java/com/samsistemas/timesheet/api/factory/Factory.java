package com.samsistemas.timesheet.api.factory;

import android.support.annotation.NonNull;

import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * @author jonatan.salas
 */
public abstract class Factory {

    @NonNull
    public <T> T createService(@NonNull final Class<T> tClass) {
        return buildRetrofit().create(tClass);
    }

    @NonNull
    protected abstract Retrofit buildRetrofit();

    @NonNull
    protected abstract OkHttpClient buildClient();

    @NonNull
    protected abstract Gson buildGson();
}
