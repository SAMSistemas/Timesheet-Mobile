package com.samsistemas.timesheet.helper;

import android.content.Context;
import android.support.annotation.NonNull;

import com.samsistemas.timesheet.data.R;

/**
 * @author jonatan.salas
 */
public class URLHelper {
    protected static URLHelper instance = null;
    protected Context mContext;

    protected URLHelper(Context context) {
        this.mContext = context;
    }

    public String buildLoginUrl() {
        return getBaseUrl() + mContext.getString(R.string.login_url);
    }

    public String buildAllJobLogsUrl() {
        return getBaseUrl() + mContext.getString(R.string.job_log_all);
    }

    public String buildCreateJobLogUrl() {
        return getBaseUrl() + mContext.getString(R.string.job_log_create);
    }

    public String buildShowPersonUrl(@NonNull String username) {
        return getBaseUrl() + mContext.getString(R.string.person_show) + username;
    }

    public String buildAllProjectsByUsernameUrl(@NonNull String username) {
        return getBaseUrl() + mContext.getString(R.string.project_all) + username;
    }

    protected String getBaseUrl() {
        return mContext.getString(R.string.base_url);
    }

    public static URLHelper newInstance(Context context) {
        if(null == instance)
            instance = new URLHelper(context);
        return instance;
    }
}
