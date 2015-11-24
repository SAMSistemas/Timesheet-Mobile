package com.samsistemas.timesheet.controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.samsistemas.timesheet.constant.SessionConst;
import com.samsistemas.timesheet.controller.base.BaseSessionController;
import com.samsistemas.timesheet.data.R;
import com.samsistemas.timesheet.entity.SessionEntity;

/**
 * Session MainController implementation.
 *
 * @author jonatan.salas
 */
public class SessionController implements BaseSessionController<SessionEntity>, SessionConst {

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

        Uri personUri = Uri.parse(context.getString(R.string.person_content_uri));
        Uri workPositionUri = Uri.parse(context.getString(R.string.work_position_content_uri));
        Uri taskTypeUri = Uri.parse(context.getString(R.string.task_type_content_uri));
        Uri clientUri = Uri.parse(context.getString(R.string.client_content_uri));
        Uri projectUri = Uri.parse(context.getString(R.string.project_content_uri));
        Uri jobLogUri = Uri.parse(context.getString(R.string.job_log_content_uri));

        context.getContentResolver().delete(personUri, null, null);
        context.getContentResolver().delete(workPositionUri, null, null);
        context.getContentResolver().delete(taskTypeUri, null, null);
        context.getContentResolver().delete(clientUri, null, null);
        context.getContentResolver().delete(projectUri, null, null);
        context.getContentResolver().delete(jobLogUri, null, null);

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
    protected SharedPreferences getSharedPreferences(@NonNull Context context) {
        return context.getSharedPreferences(
                FILENAME,
                Context.MODE_PRIVATE
        );
    }
}
