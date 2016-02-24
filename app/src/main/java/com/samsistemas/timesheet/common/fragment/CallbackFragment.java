package com.samsistemas.timesheet.common.fragment;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.jonisaa.commons.fragment.*;
import com.jonisaa.commons.presenter.BasePresenter;
import com.samsistemas.timesheet.common.callback.NestedFragmentCallback;
import com.samsistemas.timesheet.common.callback.ToolbarCallback;

/**
 *
 * @param <U>
 */
public class CallbackFragment<U extends BasePresenter> extends BaseFragment<U> {
    private ToolbarCallback toolbarCallback;
    private NestedFragmentCallback nestedFragmentCallback;

    @Override
    public int getLayout() {
        return 0;
    }

    @Nullable
    @Override
    public View onViewCreated(@Nullable View view) {
        return null;
    }

    @NonNull
    @Override
    public U createPresenter() {
        return null;
    }

    public ToolbarCallback getToolbarCallback() {
        return toolbarCallback;
    }

    public CallbackFragment setToolbarCallback(ToolbarCallback toolbarCallback) {
        this.toolbarCallback = toolbarCallback;
        return this;
    }

    public NestedFragmentCallback getNestedFragmentCallback() {
        return nestedFragmentCallback;
    }

    public CallbackFragment setNestedFragmentCallback(NestedFragmentCallback nestedFragmentCallback) {
        this.nestedFragmentCallback = nestedFragmentCallback;
        return this;
    }
}
