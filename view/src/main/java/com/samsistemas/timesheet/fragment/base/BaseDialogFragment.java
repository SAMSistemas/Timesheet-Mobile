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
 * Base class for DialogFragments
 *
 * @author jonatan.salas
 */
public abstract class BaseDialogFragment extends DialogFragment implements DialogInterface.OnClickListener {
    protected Activity mContext;

    /**
     * Public constructor
     */
    public BaseDialogFragment() { }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(mContext, getThemeResourceId());

        builder.setIcon(getIconDrawable())
               .setTitle(getTitleResourceId())
               .setView(getDialogView(getLayoutResourceId()))
               .setPositiveButton(getPositiveButtonMessage(), this)
               .setNegativeButton(getNegativeButtonMessage(), this);

        if (getMessageResourceId() != 0) {
            builder.setMessage(getMessageResourceId());
        }

        return buildDialog(builder);
    }

    /**
     *
     * @return
     */
    @LayoutRes
    public abstract int getLayoutResourceId();

    /**
     *
     * @return
     */
    @StyleRes
    public abstract int getThemeResourceId();

    /**
     *
     * @return
     */
    @StringRes
    public abstract int getTitleResourceId();

    /**
     *
     * @return
     */
    @StringRes
    public abstract int getMessageResourceId();

    /**
     *
     * @return
     */
    @NonNull
    public abstract Drawable getIconDrawable();

    /**
     *
     * @param id
     * @return
     */
    @Nullable
    public abstract View getDialogView(@LayoutRes int id);

    /**
     *
     * @return
     */
    @StringRes
    public abstract int getPositiveButtonMessage();

    /**
     *
     * @return
     */
    @StringRes
    public abstract int getNegativeButtonMessage();

    /**
     *
     * @param builder
     * @return
     */
    public abstract Dialog buildDialog(AlertDialog.Builder builder);

    /**
     *
     * @param dialog
     */
    public abstract void onPositiveButtonClick(DialogInterface dialog);

    /**
     *
     * @param dialog
     */
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

    /**
     *
     * @param context
     */
    public void setContext(Activity context) {
        this.mContext = context;
    }
}
