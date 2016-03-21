package com.samsistemas.timesheet.api.factory;

import android.support.annotation.NonNull;
import android.util.Base64;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author jonatan.salas
 */
public final class ServiceFactory extends Factory {
    private static ServiceFactory factory = null;

    private String basicAuth;
    private String baseUrl;

    private ServiceFactory(@NonNull final String username,
                           @NonNull final String password,
                           @NonNull final String baseUrl) {
        final String credentials = username + ":" + password;
        this.basicAuth = "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
        this.baseUrl = baseUrl;
    }

    @NonNull
    @Override
    protected Retrofit buildRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(buildClient())
                .build();
    }

    @NonNull
    @Override
    protected OkHttpClient buildClient() {
        final RequestInterceptor interceptor = RequestInterceptor.getInstance(basicAuth);
        return new OkHttpClient()
                .newBuilder()
                .addInterceptor(interceptor)
                .build();
    }

    public static Factory getInstance(@NonNull String username,
                                      @NonNull String password,
                                      @NonNull String baseUrl) {
        return (null == factory) ? factory = new ServiceFactory(username, password, baseUrl) : factory;
    }

    private static final class RequestInterceptor implements Interceptor {
        private static RequestInterceptor interceptor = null;
        private String basic;

        public RequestInterceptor(@NonNull final String basic) {
            this.basic = basic;
        }

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request original = chain.request();

            Request.Builder requestBuilder = original.newBuilder()
                    .header("Authorization", basic)
                    .header("Accept", "application/json")
                    .method(original.method(), original.body());

            Request request = requestBuilder.build();

            return chain.proceed(request);
        }

        public static RequestInterceptor getInstance(@NonNull String basic) {
            return (null == interceptor) ? interceptor = new RequestInterceptor(basic) : interceptor;
        }
    }
}
