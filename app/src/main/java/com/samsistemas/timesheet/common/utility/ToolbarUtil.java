package com.samsistemas.timesheet.common.utility;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v7.app.ActionBar;

/**
 * Util class used to style the Toolbar.
 *
 * @author jonatan.salas
 */
public final class ToolbarUtil {

    private ToolbarUtil() { }

    /**
     * Method that style the ActionBar and adds a back button.
     *
     * @param actionBar - the ActionBar to style.
     * @param title - the title of the actionBar.
     */
    public static void styleWithBackButton(@NonNull ActionBar actionBar, @NonNull @StringRes Integer title) {
        actionBar.setTitle(title);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayUseLogoEnabled(false);
    }

    /**
     * Method that style the ActionBar and adds a back button.
     *
     * @param actionBar - the ActionBar to style.
     * @param title - the title of the actionBar.
     */
    public static void styleWithBackButton(@NonNull ActionBar actionBar, @NonNull String title) {
        actionBar.setTitle(title);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayUseLogoEnabled(false);
    }

    /**
     * Method that style the ActionBar.
     *
     * @param actionBar - the ActionBar to style.
     * @param title - the title of the ActionBar.
     */
    public static void styleBar(@NonNull ActionBar actionBar, @NonNull @StringRes Integer title) {
        actionBar.setTitle(title);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayUseLogoEnabled(false);
    }
}
