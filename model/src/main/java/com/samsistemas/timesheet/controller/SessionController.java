package com.samsistemas.timesheet.controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.samsistemas.timesheet.controller.base.BaseSessionController;
import com.samsistemas.timesheet.data.R;
import com.samsistemas.timesheet.entity.SessionEntity;

/**
 * Session MainController implementation.
 *
 * @author jonatan.salas
 */
public class SessionController implements BaseSessionController<SessionEntity> {

    @Override
    public void createUserSession(@NonNull Context context, @NonNull SessionEntity entity) {
        final SharedPreferences.Editor editor = getSharedPreferences(context).edit();

        editor.putLong(context.getString(R.string.session_id), entity.getSessionId())
              .putLong(context.getString(R.string.user_id), entity.getUserId())
              .putString(context.getString(R.string.username), entity.getUsername())
              .putString(context.getString(R.string.password), entity.getPassword())
              .putString(context.getString(R.string.auth_header), entity.getAuthHeader())
              .putBoolean(context.getString(R.string.logged_in), entity.isLogged())
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
