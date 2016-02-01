package com.samsistemas.timesheet.controllers.base;

import android.content.Context;
import android.support.annotation.NonNull;

/**
 * Interface that manages the User Session
 *
 * @author jonatan.salas
 * @param <T> pojo that saves the values needed
 */
public interface BaseSessionController<T> {

    /**
     * Method that creates a UserSession.
     *
     * @param context the context used to get the SharedPreferences file
     * @param object POJO class used to save the values
     */
    void createUserSession(@NonNull Context context, @NonNull final T object);

    /**
     * Method called when a session is restored
     *
     * @param restored callback interface
     */
    void restoreUserSession(@NonNull OnSessionRestored restored);

    /**
     * Method called when a Session is deleted
     *
     * @param context the context used to get the ContentResolver to delete all user data
     * @param deleted callback interface
     */
    void deleteUserSession(@NonNull Context context, @NonNull OnSessionDeleted deleted);

    /**
     * Method that verifies if a User is logged in
     *
     * @param context the context used to retrieve the SharedPreferences
     * @return a boolean flag indicating if the user is or not logged in the app
     */
    boolean isLoggedIn(@NonNull Context context);

    /**
     * Callback used when a UserSession is restored
     *
     * @author jonatan.salas
     */
    interface OnSessionRestored {

        /**
         * Method used when a UserSession is restored
         */
        void restore();
    }

    /**
     * Callback used when a UserSession is deleted
     *
     * @author jonatan.salas
     */
    interface OnSessionDeleted {

        /**
         * Method used when a UserSession is deleted
         */
        void delete();
    }
}
