package com.samsistemas.timesheet.utility;

import android.test.AndroidTestCase;

import com.samsistemas.timesheet.R;
import com.samsistemas.timesheet.screen.login.validation.EmailValidator;
import com.samsistemas.timesheet.screen.login.validation.PasswordValidator;

/**
 * Class to test the validation classes.
 *
 * @author jonatan.salas
 */
public class ValidatorTest extends AndroidTestCase {

    /***
     * Method that test the class EmailValidator.
     */
    public void testEmailValidator() {
        final EmailValidator emailValidator = EmailValidator.newInstance();

        final String email1 = mContext.getString(R.string.email1);
        final String email2 = mContext.getString(R.string.email2);
        final String email3 = mContext.getString(R.string.email3);

        assertEquals(true, emailValidator.validate(email1));
        assertEquals(false, emailValidator.validate(email2));
        assertEquals(false, emailValidator.validate(email3));
    }

    /***
     * Method that test the class PasswordValidator.
     */
    public void testPasswordValidator() {
        final PasswordValidator passwordValidator = PasswordValidator.newInstance();

        final String password1 = mContext.getString(R.string.password1);
        final String password2 = mContext.getString(R.string.password2);
        final String password3 = mContext.getString(R.string.password3);
        final String password4 = mContext.getString(R.string.password4);
        final String password5 = mContext.getString(R.string.password5);
        final String password6 = mContext.getString(R.string.password6);

        assertEquals(true, passwordValidator.validate(password1));
        assertEquals(false, passwordValidator.validate(password2));
        assertEquals(false, passwordValidator.validate(password3));
        assertEquals(false, passwordValidator.validate(password4));
        assertEquals(false, passwordValidator.validate(password5));
        assertEquals(false, passwordValidator.validate(password6));
    }
}
