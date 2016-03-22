package com.samsistemas.timesheet.api.factory;

import android.support.annotation.NonNull;
import android.util.Base64;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.samsistemas.timesheet.common.adapter.ClientTypeAdapter;
import com.samsistemas.timesheet.common.adapter.JobLogTypeAdapter;
import com.samsistemas.timesheet.common.adapter.PersonTypeAdapter;
import com.samsistemas.timesheet.common.adapter.ProjectTypeAdapter;
import com.samsistemas.timesheet.common.adapter.TaskTypeAdapter;
import com.samsistemas.timesheet.domain.Client;
import com.samsistemas.timesheet.domain.JobLog;
import com.samsistemas.timesheet.domain.Person;
import com.samsistemas.timesheet.domain.Project;
import com.samsistemas.timesheet.domain.TaskType;

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
public final class BasicAuthServiceFactory extends Factory {
    private static final PersonTypeAdapter personAdapter = PersonTypeAdapter.getInstance();
    private static final ClientTypeAdapter clientAdapter = ClientTypeAdapter.getInstance();
    private static final TaskTypeAdapter taskTypeAdapter = TaskTypeAdapter.getInstance();
    private static final JobLogTypeAdapter jobLogAdapter = JobLogTypeAdapter.getInstance();
    private static final ProjectTypeAdapter projectAdapter = ProjectTypeAdapter.getInstance();

    private static BasicAuthServiceFactory factory = null;
    private final String basicAuth;
    private final String baseUrl;

    private BasicAuthServiceFactory(@NonNull final String username,
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
                .addConverterFactory(GsonConverterFactory.create(buildGson()))
                .client(buildClient())
                .build();
    }

    @NonNull
    @Override
    protected OkHttpClient buildClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(RequestInterceptor.getInstance(basicAuth))
                .build();
    }

    @NonNull
    @Override
    protected Gson buildGson() {
        final GsonBuilder builder = new GsonBuilder();

        builder.registerTypeAdapter(Person.class, personAdapter);
        builder.registerTypeAdapter(Client.class, clientAdapter);
        builder.registerTypeAdapter(JobLog.class, jobLogAdapter);
        builder.registerTypeAdapter(Project.class, projectAdapter);
        builder.registerTypeAdapter(TaskType.class, taskTypeAdapter);

        return builder.create();
    }

    public static Factory getInstance(@NonNull String username,
                                      @NonNull String password,
                                      @NonNull String baseUrl) {
        return (null == factory) ? factory = new BasicAuthServiceFactory(username, password, baseUrl) : factory;
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
