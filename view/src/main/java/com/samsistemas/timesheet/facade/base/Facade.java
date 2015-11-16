package com.samsistemas.timesheet.facade.base;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

/**
 *
 * @param <T>
 */
public interface Facade<T> {

    T findById(@NonNull Context context, long id);

    List<T> findAll(@NonNull Context context);

    boolean insert(@NonNull Context context, T object);

    boolean update(@NonNull Context context, T object);

    boolean deleteById(@NonNull Context context, long id);
}
