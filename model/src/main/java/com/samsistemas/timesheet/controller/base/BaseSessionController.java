package com.samsistemas.timesheet.controller.base;

import android.content.Context;
import android.support.annotation.NonNull;

/**
 * Interface that manages the user session.
 *
 * @author jonatan.salas
 * @param <T>
 */
public interface BaseSessionController<T> {

    /**
     *
     * @param context
     * @param object
     */
    void createUserSession(@NonNull Context context, @NonNull final T object);

    /**
     *
     * @param restored
     */
    void restoreUserSession(@NonNull OnSessionRestored restored);

    /**
     *
     * @param context
     * @param deleted
     */
    void deleteUserSession(@NonNull Context context, @NonNull OnSessionDeleted deleted);

    /***
     *
     * @return
     */
    boolean isLoggedIn(@NonNull Context context);

    /**
     *
     * @author jonatan.salas
     */
    interface OnSessionRestored {

        /**
         *
         */
        void restore();
    }

    /**
     * @author jonatan.salas
     */
    interface OnSessionDeleted {

        /**
         *
         */
        void delete();
    }
}
