package com.samsistemas.timesheet.common.utility;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author jonatan.salas
 */
public class PreferenceUtility {
    private static final String PREFERENCE_FILENAME = "timesheet_prefs";
    private static final String SESSION_KEY = "session_id";

    private PreferenceUtility() { }

    public static SharedPreferences getDefaultPreferences(final Context ctx) {
        return ThreadUtility.runInBackGround(new ThreadUtility.CallBack<SharedPreferences>() {
            @Override
            public SharedPreferences execute() {
                return ctx.getSharedPreferences(PREFERENCE_FILENAME, Context.MODE_PRIVATE);
            }
        });
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

    public static void setSessionId(final Context  ctx, final Long id) {
        ThreadUtility.runInBackGround(new ThreadUtility.CallBack<Void>() {
            @Override
            public Void execute() {
                final SharedPreferences.Editor editor = getDefaultEditor(ctx);
                editor.putLong(SESSION_KEY, id);
                editor.apply();

                return null;
            }
        });
    }
}
