package com.samsistemas.timesheet.common.utility;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author jonatan.salas
 */
public class PreferenceUtility {
    private static final String PREFERENCE_FILENAME = "timesheet_prefs";
    public static final String SESSION_KEY = "session_id";

    private PreferenceUtility() { }

    public static SharedPreferences getDefaultPreferences(Context ctx) {
        return ctx.getSharedPreferences(PREFERENCE_FILENAME, Context.MODE_PRIVATE);
    }


    public static SharedPreferences.Editor getDefaultEditor(Context ctx) {
        return getDefaultPreferences(ctx).edit();
    }

    public static Long getSessionId(final Context ctx) {
        return ThreadUtility.runInBackGround(new ThreadUtility.CallBack<Long>() {
            @Override
            public Long execute() {
                return getDefaultPreferences(ctx).getLong(SESSION_KEY, 0L);
            }
        });
    }
}
