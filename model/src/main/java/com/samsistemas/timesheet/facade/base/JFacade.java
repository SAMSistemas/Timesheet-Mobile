package com.samsistemas.timesheet.facade.base;

import android.content.Context;
import android.support.annotation.NonNull;

public interface JFacade<T> extends Facade<T> {

    void insert(@NonNull Context context, T object, OnDataFetchListener<Boolean> listener);

    void update(@NonNull Context context, T object, OnDataFetchListener<Boolean> listener);
}
