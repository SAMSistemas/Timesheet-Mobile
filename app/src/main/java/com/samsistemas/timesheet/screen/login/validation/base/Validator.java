package com.samsistemas.timesheet.screen.login.validation.base;

import android.support.annotation.NonNull;

/**
 * Interface that represents a Validation.
 *
 * @author jonatan.salas
 */
public interface Validator {

    /**
     * Method that validates a String input in the needed way.
     * This method verifies using annotations the nullability of the
     * String passed and then verifies it.
     *
     * @param input - the input to validate.
     * @return true or false if matches to the specific implementation.
     */
    boolean validate(@NonNull final String input);
}
