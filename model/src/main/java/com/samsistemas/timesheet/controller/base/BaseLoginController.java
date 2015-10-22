package com.samsistemas.timesheet.controller.base;

import android.content.Context;
import android.support.annotation.NonNull;

/**
 * Interface that provides methods associated to Login operation.
 *
 * @author jonatan.salas
 */
public interface BaseLoginController {

    /***
     * Method that does the login operation.
     *
     * @param context - the context where the login is going to be used.
     * @param credentials - the email and password as a String Array.
     * @return True if the login is successful. False in the other hand.
     */
    boolean performLogin(@NonNull final Context context, @NonNull final String[] credentials);
}
