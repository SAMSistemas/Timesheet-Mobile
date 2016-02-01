package com.samsistemas.timesheet.controllers.base;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.samsistemas.timesheet.model.SessionEntity;

import static com.samsistemas.timesheet.util.SharedPreferenceKeys.*;

/**
 * Session MainController implementation.
 *
 * @author jonatan.salas
 */
public class SessionController implements BaseSessionController<SessionEntity> {

    @Override
    public void createUserSession(@NonNull Context context, @NonNull SessionEntity entity) {
        final SharedPreferences.Editor editor = getSharedPreferences(context).edit();

        editor.putLong(SESSION_ID, entity.getSessionId())
              .putLong(USER_ID, entity.getUserId())
              .putString(USERNAME, entity.getUsername())
              .putString(PASSWORD, entity.getPassword())
              .putString(AUTH_HEADER, entity.getAuthHeader())
              .putBoolean(LOGGED_IN, entity.isLogged())
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
        return getSharedPreferences(context).getBoolean(LOGGED_IN, false);
    }

    /**
     * Method that gets the SharedPreferences for this app.
     *
     * @param context - the context used to get the SharedPreferences.
     * @return a SharedPreferences singleton object.
     */
    private SharedPreferences getSharedPreferences(@NonNull Context context) {
        return context.getSharedPreferences(
                FILENAME,
                Context.MODE_PRIVATE
        );
    }
}
