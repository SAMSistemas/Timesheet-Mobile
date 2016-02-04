package com.samsistemas.timesheet.common.animation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.view.View;

import com.samsistemas.timesheet.common.animation.base.Animator;

/**
 * Class that creates a ScaleUpAnimation.
 *
 * @author jonatan.salas
 */
public class ScaleUpAnimator implements Animator {
    private static ScaleUpAnimator sInstance = null;

    @Override
    public Bundle saveAnimation(@NonNull final View view) {
        return makeAnimation(view).toBundle();
    }

    @Override
    public ActivityOptionsCompat makeAnimation(@NonNull final View view) {
        int startX = ((Float) ViewCompat.getX(view)).intValue();
        int startY = ((Float) ViewCompat.getY(view)).intValue();

        return ActivityOptionsCompat
                .makeScaleUpAnimation(view, startX, startY, view.getWidth(), view.getHeight());
    }

    /**
     * Static method used to get a Singleton instance of the ScaleUpAnimator class.
     *
     * @return a singleton object.
     */
    public static ScaleUpAnimator newInstance() {
        if(null == sInstance) sInstance = new ScaleUpAnimator();
        return sInstance;
    }
}
