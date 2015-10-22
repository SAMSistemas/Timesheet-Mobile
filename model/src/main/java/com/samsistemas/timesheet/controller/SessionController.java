package com.samsistemas.timesheet.controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.samsistemas.timesheet.controller.base.BaseSessionController;
import com.samsistemas.timesheet.data.R;

/**
 * Session MainController implementation.
 *
 * @author jonatan.salas
 */
public class SessionController implements BaseSessionController {

    @Override
    public void createUserSession(@NonNull Context context, @NonNull String email) {
        final SharedPreferences.Editor editor = getSharedPreferences(context).edit();

        editor.putString(context.getString(R.string.key_email), email)
              .putBoolean(context.getString(R.string.is_logged_in), true)
              .apply();
    }

    @Override
    public void restoreUserSession(@NonNull OnSessionRestored onSessionRestored) {
        onSessionRestored.restore();
    }

    @Override
    public void deleteUserSession(@NonNull Context context, @NonNull OnSessionDeleted onSessionDeleted) {
        final SharedPreferences.Editor editor = getSharedPreferences(context).edit();

        editor.clear()
              .apply();

        onSessionDeleted.delete();
    }

    @Override
    public boolean isLoggedIn(@NonNull Context context) {
        return getSharedPreferences(context).getBoolean(context.getString(R.string.is_logged_in), false);
    }

    /**
     * Method that gets the SharedPreferences for this app.
     *
     * @param context - the context used to get the SharedPreferences.
     * @return a SharedPreferences singleton object.
     */
    protected SharedPreferences getSharedPreferences(@NonNull Context context) {
        return context.getSharedPreferences(
                context.getString(R.string.preference_filename),
                Context.MODE_PRIVATE
        );
    }
}
