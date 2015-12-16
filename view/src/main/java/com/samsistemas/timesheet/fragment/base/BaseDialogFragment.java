package com.samsistemas.timesheet.fragment.base;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.annotation.StyleRes;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;

/**
 * @author jonatan.salas
 */
public abstract class BaseDialogFragment extends DialogFragment implements DialogInterface.OnClickListener {
    protected Activity mContext;

    public BaseDialogFragment() { }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(mContext, getThemeResourceId());

        builder.setIcon(getIconDrawable())
               .setTitle(getTitleResourceId())
               .setMessage(getMessageResourceId())
               .setView(getDialogView(getLayoutResourceId()))
               .setPositiveButton(getPositiveButtonMessage(), this)
               .setNegativeButton(getNegativeButtonMessage(), this);

        return buildDialog(builder);
    }

    @LayoutRes
    public abstract int getLayoutResourceId();

    @StyleRes
    public abstract int getThemeResourceId();

    @StringRes
    public abstract int getTitleResourceId();

    @StringRes
    public abstract int getMessageResourceId();

    @NonNull
    public abstract Drawable getIconDrawable();

    @Nullable
    public abstract View getDialogView(@LayoutRes int id);

    @StringRes
    public abstract int getPositiveButtonMessage();

    @StringRes
    public abstract int getNegativeButtonMessage();

    public abstract Dialog buildDialog(AlertDialog.Builder builder);

    public abstract void onPositiveButtonClick(DialogInterface dialog);

    public abstract void onNegativeButtonClick(DialogInterface dialog);

    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch (which) {
            case DialogInterface.BUTTON_POSITIVE:
                onPositiveButtonClick(dialog);
                break;
            case DialogInterface.BUTTON_NEGATIVE:
                onNegativeButtonClick(dialog);
                break;
        }
    }

    public void setContext(Activity context) {
        this.mContext = context;
    }
}
