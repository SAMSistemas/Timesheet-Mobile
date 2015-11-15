package com.samsistemas.timesheet.mapper.base;

import android.content.ContentValues;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Interface used to map an Entity as ContentValues, and map a Custom object as an Entity.
 *
 * @author jonatan.salas
 */
public interface EntityMapper<T, U> {

    /**
     * Method that puts the attributes of a model class inside a ContentValues object.
     *
     * @param context - The context used to retrieve the keys for the values to insert.
     * @param objectToMap - T class object to map as ContentValues.
     * @return a ContentValues object ready for use.
     */
    ContentValues asContentValues(@NonNull Context context, @NonNull T objectToMap);

    /**
     * Method that puts a ContentValues object content inside a Model class to specify
     * when implementing this interface.
     *
     * @param context - the Context used to retrieve the keys for the values to get.
     * @param objectToMap - U class object to map as class T object.
     * @return a model class object ready for use.
     */
    T asEntity(@NonNull Context context, @Nullable U objectToMap);
}
