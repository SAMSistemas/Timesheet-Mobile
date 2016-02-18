package com.samsistemas.timesheet.common.fragment;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.samsistemas.timesheet.common.callback.ToolbarCallback;

/**
 * @author jonatan.salas
 */
public class BaseFragment extends Fragment {
    private ToolbarCallback toolbarCallback;

    public void setToolbarCallback(@NonNull ToolbarCallback toolbarCallback) {
        this.toolbarCallback = toolbarCallback;
    }

    public ToolbarCallback getToolbarCallback() {
        return toolbarCallback;
    }
}
