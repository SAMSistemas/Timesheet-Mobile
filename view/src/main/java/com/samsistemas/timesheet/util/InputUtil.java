package com.samsistemas.timesheet.util;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.WindowManager;

/**
 * Class with util method used for Input.
 *
 * @author jonatan.salas
 */
public final class InputUtil {

    private InputUtil() { }

    /**
     * Method that enables the keyboard to the user.
     *
     * @param context - The activity as context used to get the soft input mode.
     */
    public static void showKeyboard(@NonNull Activity context) {
         context.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    }

    /**
     * Method that disables the keyboard to the user.
     *
     * @param context - The activity as context used to get the soft input mode.
     */
    public static void hideKeyboard(@NonNull Activity context) {
        context.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }
}
