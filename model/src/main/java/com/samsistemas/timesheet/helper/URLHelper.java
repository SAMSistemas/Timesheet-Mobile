package com.samsistemas.timesheet.helper;

import android.content.Context;
import android.support.annotation.NonNull;

import com.samsistemas.timesheet.data.R;

/**
 * @author jonatan.salas
 */
public class URLHelper {

    public static String buildLoginUrl(Context context) {
        return getBaseUrl(context) + context.getString(R.string.login_url);
    }

    public static String buildAllJobLogsUrl(Context context) {
        return getBaseUrl(context) + context.getString(R.string.job_log_all);
    }

    public static String buildCreateJobLogUrl(Context context) {
        return getBaseUrl(context) + context.getString(R.string.job_log_create);
    }

    public static String buildShowPersonUrl(Context context, @NonNull String username) {
        return getBaseUrl(context) + context.getString(R.string.person_show) + username;
    }

    public static String buildAllProjectsByUsernameUrl(Context context, @NonNull String username) {
        return getBaseUrl(context) + context.getString(R.string.project_all) + username;
    }

    protected static String getBaseUrl(Context context) {
        return context.getString(R.string.base_url);
    }
}
