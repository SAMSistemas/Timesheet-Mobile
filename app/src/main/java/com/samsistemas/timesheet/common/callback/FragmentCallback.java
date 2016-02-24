package com.samsistemas.timesheet.common.callback;

import android.support.annotation.NonNull;

/**
 * @author jonatan.salas
 */
public interface FragmentCallback {

    void setToolbarCallback(@NonNull ToolbarCallback callback);

    void setNestedFtagmentCallback(@NonNull NestedFragmentCallback callback);
}
