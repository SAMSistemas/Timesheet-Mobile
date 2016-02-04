package com.samsistemas.timesheet.commons.animation.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;

/**
 * Interface that represents an Animation.
 *
 * @author jonatan.salas
 */
public interface Animator {

    /**
     * Method that save an animation to a bundle object. This is really useful
     * when you want to apply an animation to the activity start up.
     *
     * @param view - the view used in the animation.
     * @return a bundle object.
     */
    Bundle saveAnimation(@NonNull final View view);

    /**
     * Method that makes an animation using the view passed by parameter.
     *
     * @param view - the view used in the animation.
     * @return an Animation.
     */
    ActivityOptionsCompat makeAnimation(@NonNull final View view);
}
