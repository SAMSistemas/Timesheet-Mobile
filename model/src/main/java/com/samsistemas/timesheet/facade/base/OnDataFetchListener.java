package com.samsistemas.timesheet.facade.base;

import android.support.annotation.NonNull;


public interface OnDataFetchListener<T> {

    void onSuccess(@NonNull T response);

    void onError(@NonNull Exception error);
}
