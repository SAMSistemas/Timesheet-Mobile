package com.samsistemas.timesheet.common.utility;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.util.Log;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author jonatan.salas
 */
public class PreferenceUtility {
    private static final String LOG_TAG = PreferenceUtility.class.getSimpleName();
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
        final ExecutorService executor = Executors.newSingleThreadExecutor();
        final Callable<Long> callable = new Callable<Long>() {
            @Override
            public Long call() {
                return getDefaultPreferences(ctx).getLong(SESSION_KEY, 0L);
            }
        };

        final Future<Long> future = executor.submit(callable);
        executor.shutdown();
        Long sessionId = 0L;

        try {
            sessionId = future.get();
        } catch (ExecutionException | InterruptedException ex) {
            Log.d(LOG_TAG, ex.getMessage(), ex.getCause());
        }

        return sessionId;
    }
}
