package com.samsistemas.timesheet.common.utility;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

/**
 * Util class that add methods to work with Typefaces.
 *
 * @author jonatan.salas
 */
public final class TypefaceUtil {
    private static Typeface sTypeface = null;

    private TypefaceUtil() { }

    /**
     * Method that gets a Typeface from a directory.
     *
     * @param context - the context where this method is going to invoke.
     * @param typefaceId - the id of the typeface path to get.
     * @return a Singleton typeface object.
     */
    public static Typeface getCustomTypeface(@NonNull final Context context, @StringRes int typefaceId) {
        if (null == sTypeface) {
            String typefacePath = context.getString(typefaceId);
            sTypeface = Typeface.createFromAsset(context.getAssets(), typefacePath);
        }

        return sTypeface;
    }
}
