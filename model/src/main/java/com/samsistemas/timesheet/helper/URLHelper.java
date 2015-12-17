package com.samsistemas.timesheet.helper;

import android.content.Context;
import android.support.annotation.NonNull;

import com.samsistemas.timesheet.data.R;

/**
 * Utility class used to build the URLs to point the Timesheet REST API.
 *
 * @author jonatan.salas
 */
public final class URLHelper {

    /**
     * Private constructor
     */
    private URLHelper() { }

    /**
     * Helper method that builds the login URL
     *
     * @param context the application context used to retrieve the base url
     * @return a String representation of the login URL
     */
    public static String buildLoginUrl(Context context) {
        return getBaseUrl(context) + context.getString(R.string.login_url);
    }

    /**
     * Helper method that build the JobLogs URL
     *
     * @param context the application context used to retrieve the base url and the joblog path
     * @return a String representation of the joblog all URL
     */
    public static String buildAllJobLogsUrl(Context context) {
        return getBaseUrl(context) + context.getString(R.string.job_log_all);
    }

    /**
     * Helper method that build the Joblog create URL
     *
     * @param context the application context used to retrieve the base url and the joblog create path
     * @return a String representation of the joblog create URL
     */
    public static String buildCreateJobLogUrl(Context context) {
        return getBaseUrl(context) + context.getString(R.string.job_log_create);
    }

    /**
     * Helper method that build the Joblog update URL
     *
     * @param context the application context used to retrieve the base url and the joblog create path
     * @return a String representation of the joblog update URL
     */
    public static String buildUpdateJobLogUrl(Context context) {
        return getBaseUrl(context) + context.getString(R.string.job_log_update);
    }


    /**
     * Helper method that build the show person URL
     *
     * @param context the application context used to retrieve the base url and the person show url
     * @param username the username to append
     * @return a String representation of the show person URL
     */
    public static String buildShowPersonUrl(Context context, @NonNull String username) {
        return getBaseUrl(context) + context.getString(R.string.person_show) + username;
    }

    /**
     * Helper method that build the all project by username URL
     *
     * @param context the application context used to retrieve the base url and the project all by
     *                username url
     * @param username the username to append
     * @return a String representation of the all project by username URL
     */
    public static String buildAllProjectsByUsernameUrl(Context context, @NonNull String username) {
        return getBaseUrl(context) + context.getString(R.string.project_all) + username;
    }

    /**
     * Helper private method used to get the base URL
     *
     * @param context the application context used to retrieve the base url
     * @return a String representation of the base URL
     */
    private static String getBaseUrl(Context context) {
        return context.getString(R.string.base_url);
    }
}
