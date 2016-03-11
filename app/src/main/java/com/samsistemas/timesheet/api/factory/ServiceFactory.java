package com.samsistemas.timesheet.api.factory;

import android.support.annotation.Nullable;
import android.util.Base64;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author jonatan.salas
 */
public class ServiceFactory {
    private static final String AUTH = "Authorization";
    private static final String ACCEPT = "Accept";
    private static final String MIME_TYPE = "application/json";

    private static final String API_BASE_URL = "https://10.0.0.53:8080";

    private static OkHttpClient httpClient = new OkHttpClient();

    private static Retrofit.Builder builder = new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create());

    public static <T> T createService(Class<T> serviceClass) {
        return createService(serviceClass, null, null);
    }

    public static <T> T createService(Class<T> serviceClass, @Nullable String username, @Nullable String password) {
        if (null != username && null != password) {
            final String credentials = username + ":" + password;
            final String basic =
                    "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);

            httpClient.interceptors().add(new Interceptor() {
                @Override
                public Response intercept(Interceptor.Chain chain) throws IOException {
                    Request original = chain.request();

                    Request.Builder requestBuilder = original.newBuilder()
                            .header(AUTH, basic)
                            .header(ACCEPT, MIME_TYPE)
                            .method(original.method(), original.body());

                    Request request = requestBuilder.build();

                    return chain.proceed(request);
                }
            });
        }

        final Retrofit retrofit = builder.client(httpClient).build();
        return retrofit.create(serviceClass);
    }
}
