package com.samsistemas.timesheet.facade.base;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

/**
 *
 * @param <T>
 */
public interface Facade<T> {

    /**
     *
     * @param context
     * @param id
     * @return
     */
    T findById(@NonNull Context context, long id);

    /**
     *
     * @param context
     * @return
     */
    List<T> findAll(@NonNull Context context);

    /**
     *
     * @param context
     * @param object
     * @return
     */
    boolean insert(@NonNull Context context, T object);

    /**
     *
     * @param context
     * @param object
     * @return
     */
    boolean update(@NonNull Context context, T object);

    /**
     *
     * @param context
     * @param id
     * @return
     */
    boolean deleteById(@NonNull Context context, long id);
}
