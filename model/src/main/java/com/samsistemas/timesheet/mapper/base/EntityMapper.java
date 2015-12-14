package com.samsistemas.timesheet.mapper.base;

import android.content.ContentValues;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

/**
 * Interface used to map an Entity as ContentValues, and map a Custom object as an Entity.
 *
 * @author jonatan.salas
 * @param <T>
 * @param <U>
 */
public interface EntityMapper<T, U> {

    /**
     * Method that puts the attributes of a model class inside a ContentValues object.
     *
     * @param objectToMap - T class object to map as ContentValues.
     * @return a ContentValues object ready for use.
     */
    ContentValues asContentValues(@NonNull T objectToMap);

    /**
     * Method that puts a Cursor object content inside a Model class to specify
     * when implementing this interface.
     *
     * @param objectToMap - U class object to map as class T object.
     * @return a model class object ready for use.
     */
    T asEntity(@Nullable U objectToMap);

    /**
     *
     * @param objectToMap
     * @return
     */
    List<T> asEntityList(@Nullable U objectToMap);
}
