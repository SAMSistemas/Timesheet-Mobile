package com.samsistemas.timesheet.utility;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import static com.samsistemas.timesheet.utility.ThreadUtility.runInBackground;

/**
 * @author jonatan.salas
 */
public class PreferenceUtility {
    private static final String PREFERENCE_FILENAME = "timesheet_prefs";
    private static final String SESSION_KEY = "session_id";
    private static Context ctx = null;

    private PreferenceUtility() { }

    public static void init(@NonNull Context context) {
        ctx = context;
    }

    public static SharedPreferences getDefaultPreferences() {
        return runInBackground(new ThreadUtility.CallBack<SharedPreferences>() {
            @Override
            public SharedPreferences execute() {
                return ctx.getSharedPreferences(PREFERENCE_FILENAME, Context.MODE_PRIVATE);
            }
        });
    }

    public static SharedPreferences.Editor getDefaultEditor() {
        return getDefaultPreferences().edit();
    }

    public static Long getSessionId() {
        return runInBackground(new ThreadUtility.CallBack<Long>() {
            @Override
            public Long execute() {
                return getDefaultPreferences().getLong(SESSION_KEY, 0L);
            }
        });
    }

    public static void setSessionId(final Long id) {
        runInBackground(new ThreadUtility.CallBack<Void>() {
            @Override
            public Void execute() {
                final SharedPreferences.Editor editor = getDefaultEditor();
                editor.putLong(SESSION_KEY, id);
                editor.apply();

                return null;
            }
        });
    }
}
