package com.samsistemas.timesheet.api.factory.base;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author jonatan.salas
 */
public abstract class Factory {
    private final Retrofit.Builder retrofitBuilder;
    private final OkHttpClient.Builder okHttpClientBuilder;
    private final GsonBuilder gsonBuilder;
    private final String url;

    public Factory(String url) {
        this.retrofitBuilder = new Retrofit.Builder();
        this.okHttpClientBuilder = new OkHttpClient.Builder();
        this.gsonBuilder = new GsonBuilder();
        this.url = url;
    }

    @NonNull
    public <T> T createService(@NonNull final Class<T> tClass) {
        return buildRetrofit().create(tClass);
    }

    @NonNull
    protected Retrofit buildRetrofit() {
        retrofitBuilder
                .baseUrl(url)
                .client(buildClient())
                .addConverterFactory(GsonConverterFactory.create(buildGson()));

        return onCreateRetrofit(retrofitBuilder);
    }

    @NonNull
    protected OkHttpClient buildClient() {
        return onCreateOkHttpClient(okHttpClientBuilder);
    }

    @NonNull
    protected Gson buildGson() {
        return onCreateGson(gsonBuilder);
    }

    @NonNull
    protected abstract Retrofit onCreateRetrofit(@NonNull final Retrofit.Builder retrofitBuilder);

    @NonNull
    protected abstract OkHttpClient onCreateOkHttpClient(@NonNull final OkHttpClient.Builder okHttpClientBuilder);

    @NonNull
    protected abstract Gson onCreateGson(@NonNull final GsonBuilder gsonBuilder);
}
