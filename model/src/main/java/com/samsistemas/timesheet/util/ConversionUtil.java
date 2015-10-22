package com.samsistemas.timesheet.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.io.ByteArrayOutputStream;

/**
 * Utility class that adds methods to help with data type conversion.
 *
 * @author jonatan.salas
 */
public class ConversionUtil {

    /**
     * Method that converts a boolean value to its int representation.
     *
     * @param value - the value to convert.
     * @return 1 or 0, depending the value of the boolean var.
     */
    public static int booleanToInt(boolean value) {
        return value ? 1:0;
    }

    /**
     * Method that converts an int value to its boolean representation.
     *
     * @param value - int value to convert, the method expects an int value of 1 or 0.
     * @return true or false, depending of the value. If the value it isn't 1 or 0 it returns false by default.
     */
    public static boolean intToBoolean(int value) {
        switch (value) {
            case 1:
                return true;
            case 0:
                return false;
            default:
                return false;
        }
    }

    /**
     * Method that handles the conversion from drawable to byte[].
     *
     * @param drawable - the drawable to convert.
     * @return a byte[] of data to store or null.
     */
    public static byte[] drawableToByteArray(Drawable drawable) {
        if (null != drawable) {
            final ByteArrayOutputStream stream = new ByteArrayOutputStream();
            final Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();

            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);

            return stream.toByteArray();
        }

        return null;
    }

    /**
     * Method that handles the conversion from byte[] to drawable.
     *
     * @param stream - the byte[] stream to convert to drawable.
     * @return a drawable ready to use or null.
     */
    public static Drawable byteArrayToDrawable(byte[] stream) {
        if(null != stream) {
            final Bitmap bitmap = BitmapFactory.decodeByteArray(stream, 0, stream.length);
            return new BitmapDrawable(null, bitmap);
        }

        return null;
    }
}
