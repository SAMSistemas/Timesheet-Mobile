package com.samsistemas.timesheet.common.navigation.base;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;

/**
 * Interface used to represent a navigation flow from one activity to
 * another.
 *
 * @author jonatan.salas
 */
public interface Navigator {

    /**
     * Method that launches a new activity.
     *
     * @param actualActivity - the activity where i am.
     */
    void navigate(@NonNull Activity actualActivity);

    /**
     * Method that navigates to a new activity and launches it using an scale up animation.
     *
     * @param actualActivity - the activity where i am.
     * @param view - the view to animate.
     */
    void navigateWithAnimation(@NonNull Activity actualActivity, @NonNull View view);

    /**
     *
     * @param actualActivity
     * @return
     */
    Intent getCompleteIntent(@NonNull Activity actualActivity);
}
