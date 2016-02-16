package com.samsistemas.timesheet.view.account.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.annotation.StyleRes;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import com.samsistemas.timesheet.R;
import com.samsistemas.timesheet.common.fragment.base.BaseDialogFragment;

/**
 * @author jonatan.salas
 */
public class ChangePasswordFragment extends BaseDialogFragment {
    public static final String TAG = ChangePasswordFragment.class.getSimpleName();

    public ChangePasswordFragment() { }

    @LayoutRes
    @Override
    public int getLayoutResourceId() {
        return R.layout.edit_password_content;
    }

    @StyleRes
    @Override
    public int getThemeResourceId() {
        return R.style.AppTheme_Dialog_light;
    }

    @StringRes
    @Override
    public int getTitleResourceId() {
        return R.string.password_dialog_title;
    }

    @StringRes
    @Override
    public int getMessageResourceId() {
        return 0;
    }

    @NonNull
    @Override
    public Drawable getIconDrawable() {
        return ContextCompat.getDrawable(getContext(), R.drawable.ic_person_white_24dp);
    }

    @Nullable
    @Override
    public View getDialogView(@LayoutRes int id) {
        View content = LayoutInflater.from(getContext()).inflate(R.layout.edit_password_content, null, false);

        TextInputLayout latestLayout = (TextInputLayout) content.findViewById(R.id.latest_layout);
        TextInputLayout newLayout = (TextInputLayout) content.findViewById(R.id.new_layout);
        EditText latestPassword = (EditText) content.findViewById(R.id.latest_password);
        EditText newPassword = (EditText) content.findViewById(R.id.new_password);

        Typeface roboto = Typeface.createFromAsset(getActivity().getAssets(), getString(R.string.roboto_light));

        latestLayout.setTypeface(roboto);
        latestPassword.setTypeface(roboto);
        newLayout.setTypeface(roboto);
        newPassword.setTypeface(roboto);

        return content;
    }

    @Override
    public int getPositiveButtonMessage() {
        return R.string.password_dialog_positive_button;
    }

    @Override
    public int getNegativeButtonMessage() {
        return R.string.password_dialog_negative_button;
    }

    @Override
    public Dialog buildDialog(AlertDialog.Builder builder) {
        Dialog dialog = builder.create();

        DisplayMetrics displaymetrics = new DisplayMetrics();
        mContext.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);

        int width = (int) (displaymetrics.widthPixels * 0.95);

        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.width = width;
        dialog.getWindow().setAttributes(params);

        return dialog;
    }

    @Override
    public void onPositiveButtonClick(DialogInterface dialog) {

    }

    @Override
    public void onNegativeButtonClick(DialogInterface dialog) {

    }
}
