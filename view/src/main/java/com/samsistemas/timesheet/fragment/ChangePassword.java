package com.samsistemas.timesheet.fragment;

import android.app.Dialog;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import com.samsistemas.timesheet.R;
import com.samsistemas.timesheet.fragment.base.BaseDialogFragment;
import com.samsistemas.timesheet.util.DrawableUtil;
import com.samsistemas.timesheet.util.TypefaceUtil;

/**
 * @author jonatan.salas
 */
public class ChangePassword extends BaseDialogFragment {
    public static final String TAG = ChangePassword.class.getSimpleName();

    public ChangePassword() { }

    @Override
    public int getLayoutResourceId() {
        return R.layout.edit_password_content;
    }

    @Override
    public int getThemeResourceId() {
        return R.style.AppTheme_Dialog_light;
    }

    @Override
    public int getTitleResourceId() {
        return R.string.password_dialog_title;
    }

    @NonNull
    @Override
    public Drawable getIconDrawable() {
        return DrawableUtil.modifyDrawableColor(
                getContext(),
                R.drawable.ic_person_white_24dp,
                R.color.material_teal
        );
    }

    @NonNull
    @Override
    public View getDialogView(@LayoutRes int id) {
        View content = LayoutInflater.from(getContext()).inflate(R.layout.edit_password_content, null, false);

        TextInputLayout latestLayout = (TextInputLayout) content.findViewById(R.id.latest_layout);
        TextInputLayout newLayout = (TextInputLayout) content.findViewById(R.id.new_layout);
        EditText latestPassword = (EditText) content.findViewById(R.id.latest_password);
        EditText newPassword = (EditText) content.findViewById(R.id.new_password);

        Typeface roboto = TypefaceUtil.getCustomTypeface(getContext(), R.string.roboto_light);

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
}
