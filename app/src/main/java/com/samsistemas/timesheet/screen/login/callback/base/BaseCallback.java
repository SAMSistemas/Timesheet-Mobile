package com.samsistemas.timesheet.screen.login.callback.base;

import android.support.annotation.NonNull;

import com.samsistemas.timesheet.api.factory.RestServiceFactory;
import com.samsistemas.timesheet.api.service.TimesheetService;
import com.samsistemas.timesheet.utility.ThreadUtility;

/**
 *
 * @param <T>
 */
public class BaseCallback<T> implements ThreadUtility.CallBack<T> {
    private static final String BASE_URL = "http://10.0.0.53:8080";
    private final TimesheetService service;
    private final String username;
    private final String password;

    public BaseCallback(@NonNull final String username, @NonNull final String password) {
        this.service = RestServiceFactory
                .getInstance(username, password, BASE_URL)
                .createService(TimesheetService.class);
        this.username = username;
        this.password = password;
    }

    @Override
    public T execute() {
        return null;
    }

    public TimesheetService getService() {
        return service;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}
