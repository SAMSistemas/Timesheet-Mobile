package com.samsistemas.timesheet.screen.login.validation;

import android.support.annotation.NonNull;
import android.util.Patterns;

import com.samsistemas.timesheet.screen.login.validation.base.Validator;

import java.util.regex.Pattern;

/**
 * Class used to validate an Email String representation.
 *
 * @author jonatan.salas
 */
//TODO JS: modified validator.
public class EmailValidator implements Validator {
    private static final Pattern EMAIL_PATTERN = Patterns.EMAIL_ADDRESS;
    private static EmailValidator sInstance = null;

    @Override
    public boolean validate(@NonNull final String email) {
        return (!email.isEmpty()); //&&
                //EMAIL_PATTERN.matcher(email).matches());
    }

    /**
     * Static method used to get a Singleton instance of the EmailValidator class.
     *
     * @return a singleton object.
     */
    public static EmailValidator newInstance() {
        if (null == sInstance) sInstance = new EmailValidator();
        return sInstance;
    }
}
