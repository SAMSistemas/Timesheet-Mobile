package com.samsistemas.timesheet.api.factory;

import android.support.annotation.NonNull;
import android.util.Base64;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.orm.SugarRecord;

import com.samsistemas.timesheet.api.factory.base.Factory;
import com.samsistemas.timesheet.common.SugarExclusionStrategy;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;

/**
 * @author jonatan.salas
 */
public class RestServiceFactory extends Factory {
    private static final Class<?> clazz = SugarRecord.class;
    private static final SugarExclusionStrategy strategy = new SugarExclusionStrategy(clazz);
    private static RestServiceFactory factory = null;
    private String authentication;

    public RestServiceFactory(@NonNull final String username,
                              @NonNull final String password,
                              @NonNull final String url) {
        super(url);
        final String credentials = username + ":" + password;
        this.authentication = "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
    }

    @NonNull
    @Override
    public Retrofit onCreateRetrofit(@NonNull Retrofit.Builder retrofitBuilder) {
        return retrofitBuilder.build();
    }

    @NonNull
    @Override
    public OkHttpClient onCreateOkHttpClient(@NonNull OkHttpClient.Builder okHttpClientBuilder) {
        return okHttpClientBuilder
                .addInterceptor(RequestInterceptor.getInstance(authentication))
                .build();
    }

    @NonNull
    @Override
    public Gson onCreateGson(@NonNull GsonBuilder gsonBuilder) {
        return gsonBuilder
                .addDeserializationExclusionStrategy(strategy)
                .addSerializationExclusionStrategy(strategy)
                .create();
    }

    public static Factory getInstance(@NonNull String username,
                                             @NonNull String password,
                                             @NonNull String baseUrl) {
        return (null == factory) ? factory = new RestServiceFactory(username, password, baseUrl) : factory;
    }

    private static final class RequestInterceptor implements Interceptor {
        private static RequestInterceptor interceptor = null;
        private String basic;

        public RequestInterceptor(@NonNull final String basic) {
            this.basic = basic;
        }

        @Override
        public Response intercept(Chain chain) throws IOException {
            final Request original = chain.request();

            Request.Builder requestBuilder = original.newBuilder()
                    .header("Authorization", basic)
                    .header("Accept", "application/json")
                    .method(original.method(), original.body());

            return chain.proceed(requestBuilder.build());
        }

        public static RequestInterceptor getInstance(@NonNull final String basic) {
            return (null == interceptor) ? interceptor = new RequestInterceptor(basic) : interceptor;
        }
    }
}
