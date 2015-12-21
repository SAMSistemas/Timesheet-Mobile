package com.samsistemas.timesheet.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.wifi.WifiManager;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.annotation.StyleRes;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.samsistemas.timesheet.R;
import com.samsistemas.timesheet.fragment.base.BaseDialogFragment;
import com.samsistemas.timesheet.util.DrawableUtil;

/**
 * @author jonatan.salas
 */
public class VerifyConnectionFragment extends BaseDialogFragment {
    public static final String TAG = VerifyConnectionFragment.class.getSimpleName();

    @LayoutRes
    @Override
    public int getLayoutResourceId() {
        return 0;
    }

    @StyleRes
    @Override
    public int getThemeResourceId() {
        return R.style.AppTheme_Dialog_light;
    }

    @StringRes
    @Override
    public int getTitleResourceId() {
        return R.string.internet_dialog_title;
    }

    @StringRes
    @Override
    public int getMessageResourceId() {
        return R.string.internet_dialog_message;
    }

    @NonNull
    @Override
    public Drawable getIconDrawable() {
        return DrawableUtil.modifyDrawableColor(
                getContext().getApplicationContext(),
                R.drawable.ic_signal_wifi_off_white,
                R.color.material_teal
        );
    }

    @Nullable
    @Override
    public View getDialogView(@LayoutRes int id) {
        return null;
    }

    @StringRes
    @Override
    public int getPositiveButtonMessage() {
        return R.string.internet_dialog_positive_button;
    }

    @StringRes
    @Override
    public int getNegativeButtonMessage() {
        return R.string.internet_dialog_negative_button;
    }

    @Override
    public Dialog buildDialog(AlertDialog.Builder builder) {
        return builder.create();
    }

    @Override
    public void onPositiveButtonClick(DialogInterface dialog) {
        dialog.dismiss();
        final Intent settingsIntent = new Intent(WifiManager.ACTION_PICK_WIFI_NETWORK);
        settingsIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        mContext.startActivity(settingsIntent);
    }

    @Override
    public void onNegativeButtonClick(DialogInterface dialog) {
        dialog.dismiss();
        mContext.finish();
    }
}
