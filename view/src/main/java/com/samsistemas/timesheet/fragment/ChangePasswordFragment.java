package com.samsistemas.timesheet.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import com.samsistemas.timesheet.R;
import com.samsistemas.timesheet.util.DrawableUtil;
import com.samsistemas.timesheet.util.TypefaceUtil;

import java.lang.ref.WeakReference;

/**
 * @author jonatan.salas
 */
public class ChangePasswordFragment extends DialogFragment implements DialogInterface.OnClickListener {
    public static final String TAG = ChangePasswordFragment.class.getSimpleName();
    private WeakReference<Activity> mActivityReference;

    public ChangePasswordFragment() {}

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder;

        if(null != mActivityReference) {
            builder = new AlertDialog.Builder(mActivityReference.get(), R.style.AppTheme_Dialog_light);
            prepare(builder);
            Dialog dialog = builder.create();
            return setDialogParams(dialog);
        }

        return super.onCreateDialog(savedInstanceState);
    }

    private void prepare(AlertDialog.Builder builder) {
        final Drawable drawable = DrawableUtil.modifyDrawableColor(
                getContext(),
                R.drawable.ic_person_white_24dp,
                R.color.material_teal
        );
        builder.setIcon(drawable)
                .setTitle(R.string.password_dialog_title)
                .setView(styleView())
                .setPositiveButton(R.string.password_dialog_positive_button, this)
                .setNegativeButton(R.string.password_dialog_negative_button, this);
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {

    }

    private View styleView() {
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

    private Dialog setDialogParams(Dialog dialog) {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        Activity activity = mActivityReference.get();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);

        int width = (int) (displaymetrics.widthPixels * 0.95);

        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.width = width;
        dialog.getWindow().setAttributes(params);

        return dialog;
    }

    /** Attributes setters and getters **/
    public void setActivityReference(WeakReference<Activity> activityReference) {
        this.mActivityReference = activityReference;
    }
}
