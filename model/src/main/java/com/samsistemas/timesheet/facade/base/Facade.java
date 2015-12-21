package com.samsistemas.timesheet.facade.base;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

/**
 * Facade interface
 *
 * @author jonatan.salas
 * @param <T> generic class representing the model of the app
 */
public interface Facade<T> {

    /**
     * Method that gets a T object by id
     *
     * @param context the application context used to get the contentResolver object for querying
     * @param id the id to search
     * @return a T object instanced with its parameters set
     */
    T findById(@NonNull Context context, long id);

    /**
     * Method that gets a T object list
     *
     * @param context the application context used to get the contentResolver object for querying
     * @return a List of T class object with their parameters set
     */
    List<T> findAll(@NonNull Context context);

    /**
     * Method that inserts a T object into the contentResolver
     *
     * @param context the application context used to get the contentResolver object for inserting
     * @param object the T class object to insert in the database
     * @return a boolean flag indicating if the object could or couldn't be stored.
     */
    boolean insert(@NonNull Context context, T object);

    /**
     * Method that updates a T object in the contentResolver
     *
     * @param context the application context used to get the contentResolver object for updating
     * @param object the T class object to update
     * @return a boolean flag indicating if the object could or couldn't be updated
     */
    boolean update(@NonNull Context context, T object);

    /**
     * Method that deletes a T object from the database with the specified id.
     *
     * @param context the application context used to get the contentResolver object for deleting
     * @param id the id to search and delete
     * @return a boolean flag indicating if the object could or couldn't be deleted
     */
    boolean deleteById(@NonNull Context context, long id);
}
