package com.samsistemas.timesheet.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.samsistemas.timesheet.R;
import com.samsistemas.timesheet.util.DrawableUtil;

import java.lang.ref.WeakReference;

/**
 * @author jonatan.salas
 */
public class VerifyConnectionFragment extends DialogFragment implements DialogInterface.OnClickListener {
    public static final String TAG = VerifyConnectionFragment.class.getSimpleName();
    private WeakReference<Activity> mActivityReference;

    public VerifyConnectionFragment() {}

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder;

        if(null != mActivityReference) {
            builder = new AlertDialog.Builder(mActivityReference.get(), R.style.AppTheme_Dialog_light);
            prepare(builder);
            return builder.create();
        }

        return super.onCreateDialog(savedInstanceState);
    }

    /**
     *
     * @param builder
     */
    private void prepare(AlertDialog.Builder builder) {
        builder.setIcon(getIcon(R.drawable.ic_signal_wifi_off_white))
                .setTitle(R.string.internet_dialog_title)
                .setMessage(R.string.internet_dialog_message)
                .setPositiveButton(R.string.internet_dialog_positive_button, this)
                .setNegativeButton(R.string.internet_dialog_negative_button, this);
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if (DialogInterface.BUTTON_NEGATIVE == which) {
            dialog.dismiss();
            mActivityReference.get().finish();
        } else if(DialogInterface.BUTTON_POSITIVE == which) {
            dialog.dismiss();
            final Intent settingsIntent = new Intent(WifiManager.ACTION_PICK_WIFI_NETWORK);
            settingsIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            mActivityReference.get().startActivity(settingsIntent);
        }
    }

    /**
     *
     * @param id
     * @return
     */
    private Drawable getIcon(@DrawableRes int id) {
        return DrawableUtil.modifyDrawableColor(
                getContext(),
                id,
                R.color.material_teal,
                PorterDuff.Mode.SRC_ATOP
        );
    }

    /** Attributes setters and getters **/
    public void setActivityReference(WeakReference<Activity> activityReference) {
        this.mActivityReference = activityReference;
    }

    public WeakReference<Activity> getActivityReference() {
        return mActivityReference;
    }
}
