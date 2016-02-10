package com.samsistemas.timesheet.screen.login.validation;

import android.support.annotation.NonNull;

import com.samsistemas.timesheet.screen.login.validation.base.Validator;

import java.util.regex.Pattern;

/**
 * Class used to validate a Password String representation.
 *
 * @author jonatan.salas
 */
public class PasswordValidator implements Validator {
    //This regular expression evaluates password
    //with a-z and A-Z characters with a max length of 8.
    private static final String PASSWORD_REGEX = "[a-zA-Z]{8}";
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_REGEX);
    private static PasswordValidator sInstance = null;

    @Override
    public boolean validate(@NonNull final String password) {
        return (!password.isEmpty()
                && PASSWORD_PATTERN.matcher(password).matches());
    }

    /**
     * Static method used to get a Singleton instance of the PasswordValidator class.
     *
     * @return a singleton object.
     */
    public static PasswordValidator newInstance() {
        if (null == sInstance) sInstance = new PasswordValidator();
        return sInstance;
    }
}
