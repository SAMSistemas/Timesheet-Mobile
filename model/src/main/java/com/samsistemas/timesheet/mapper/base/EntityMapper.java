package com.samsistemas.timesheet.mapper.base;

import android.content.ContentValues;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.samsistemas.timesheet.entity.base.Entity;

import java.util.List;

/**
 * Interface used to map an Entity as ContentValues, and map a Custom object as an Entity.
 *
 * @author jonatan.salas
 * @param <T> any class to be mapped extending the Entity class
 * @param <U> any class to be mapped
 */
public interface EntityMapper<T extends Entity, U> {

    /**
     * Method that puts the attributes of an Entity class inside a ContentValues object
     *
     * @param objectToMap T class object to map as ContentValues
     * @return a ContentValues object ready for use
     */
    ContentValues asContentValues(@NonNull T objectToMap);

    /**
     * Method that maps an object as an Entity class
     *
     * @param objectToMap U class object to map as class T object
     * @return an Entity
     */
    T asEntity(@Nullable U objectToMap);

    /**
     * Method that maps an object into a List of Entities
     *
     * @param objectToMap U class object to map as class T object
     * @return an Entity list
     */
    List<T> asEntityList(@Nullable U objectToMap);
}
