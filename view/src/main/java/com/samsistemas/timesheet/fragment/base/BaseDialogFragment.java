package com.samsistemas.timesheet.fragment.base;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;

/**
 * @author jonatan.salas
 */
public abstract class BaseDialogFragment extends DialogFragment {
    protected Context mContext;

    public BaseDialogFragment(Context context) {
        this.mContext = context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(mContext, getThemeResourceId());
        builder.setView(getDialogView(getLayoutResourceId()));
        return buildDialog(builder);
    }

    @LayoutRes
    public abstract int getLayoutResourceId();

    @StyleRes
    public abstract int getThemeResourceId();

    public abstract View getDialogView(@LayoutRes int id);

    public abstract Dialog editViewParameters(Dialog dialog);

    public Dialog buildDialog(AlertDialog.Builder builder) {
        return editViewParameters(builder.create());
    }
}
