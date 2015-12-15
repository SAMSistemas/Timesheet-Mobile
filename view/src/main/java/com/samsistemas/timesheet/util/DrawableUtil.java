package com.samsistemas.timesheet.util;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;

import com.samsistemas.timesheet.R;

/**
 * Utility class to work with drawables.
 *
 * @author jonatan.salas
 */
public class DrawableUtil {

    /**
     * Util method that modifies the drawable color.
     *
     * @param context - context used to get the drawable and the color.
     * @param drawableId - the id of the drawable to get.
     * @param colorId - the id of the color to get.
     * @return a drawable with color modified.
     */
    public static Drawable modifyDrawableColor(@NonNull final Context context,
                                               @DrawableRes final int drawableId,
                                               @ColorRes final int colorId,
                                               @NonNull PorterDuff.Mode mode) {

        Drawable coloredDrawable = ContextCompat.getDrawable(context, drawableId);
        final int color = ContextCompat.getColor(context, colorId);

        //This is the better way to apply a particular color to a drawable.
        coloredDrawable.setColorFilter(color, mode);

        return coloredDrawable;
    }

    /**
     * Util method that modifies the drawable color.
     *
     * @param context - context used to get the drawable and the color.
     * @param drawable - the drawable to modify.
     * @param colorId - the id of the color to get.
     * @return a drawable with color modified.
     */
    public static Drawable modifyDrawableColor(@NonNull final Context context,
                                               @NonNull Drawable drawable,
                                               @ColorRes final int colorId,
                                               @NonNull PorterDuff.Mode mode) {
        final int color = ContextCompat.getColor(context, colorId);

        //This is the better way to apply a particular color to a drawable.
        drawable.setColorFilter(color, mode);

        return drawable;
    }

    public static Drawable modifyDrawableColorWithBounds(@NonNull Context context, @DrawableRes int id) {
        Drawable drawable = modifyDrawableColor(context, id, R.color.primary, PorterDuff.Mode.SRC_ATOP);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        return drawable;
    }
}
