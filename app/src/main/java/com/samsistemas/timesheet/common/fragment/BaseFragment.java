package com.samsistemas.timesheet.common.fragment;

import android.support.v4.app.Fragment;

import com.samsistemas.timesheet.common.callback.NestedFragmentCallback;
import com.samsistemas.timesheet.common.callback.ToolbarCallback;

/**
 * @author jonatan.salas
 */
public class BaseFragment extends Fragment {

    private NestedFragmentCallback nestedFragmentCallback;
    private ToolbarCallback toolbarCallback;

    public void setToolbarCallback(ToolbarCallback toolbarCallback) {
        this.toolbarCallback = toolbarCallback;
    }

    public ToolbarCallback getToolbarCallback() {
        return toolbarCallback;
    }

    public void setNestedFragmentCallback(NestedFragmentCallback nestedFragmentCallback) {
        this.nestedFragmentCallback = nestedFragmentCallback;
    }

    public NestedFragmentCallback getNestedFragmentCallback() {
        return nestedFragmentCallback;
    }
}
