package com.samsistemas.timesheet.model.base;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;

/**
 * Interface that all model have to implement. It provides some methods to help
 * with the conversion and reverse conversion of a model object to a ContentValues.
 *
 * @author jonatan.salas
 */
public interface BaseModel<T> {

    /**
     * Method that puts the attributes of a model class inside a ContentValues object.
     *
     * @param context - The context used to retrieve the keys for the values to insert.
     * @return a ContentValues object ready for use.
     */
    ContentValues asContentValues(@NonNull Context context);

    /**
     * Method that puts a ContentValues object content inside a Model class to specify
     * when implementing this interface.
     *
     * @param context - the Context used to retrieve the keys for the values to get.
     * @param cursor - the cursor with the values we need to use to set the attributes of a model class.
     * @return a model class object ready for use.
     */
    T save(@NonNull Context context, Cursor cursor);
}
