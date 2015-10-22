package com.samsistemas.timesheet.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
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

    /**
     *
     * @param builder
     */
    private void prepare(AlertDialog.Builder builder) {
        builder.setIcon(getIcon(R.drawable.ic_person_white))
                .setTitle(R.string.password_dialog_title)
                .setView(getContentView(R.layout.edit_password_content))
                .setPositiveButton(R.string.password_dialog_positive_button, this)
                .setNegativeButton(R.string.password_dialog_negative_button, this);
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {

    }

    /**
     *
     * @param id
     * @return
     */
    public Drawable getIcon(@DrawableRes int id) {
        return DrawableUtil.modifyDrawableColor(
                getContext(),
                id,
                R.color.material_teal,
                PorterDuff.Mode.SRC_ATOP
        );
    }

    /**
     *
     * @param id
     * @return
     */
    private View getContentView(@LayoutRes int id) {
        View content = LayoutInflater.from(getContext()).inflate(id, null, false);
        styleView(content);

        return content;
    }

    /**
     *
     * @param view
     */
    private void styleView(@NonNull View view) {
        TextInputLayout latestLayout = (TextInputLayout) view.findViewById(R.id.latest_layout);
        TextInputLayout newLayout = (TextInputLayout) view.findViewById(R.id.new_layout);
        EditText latestPassword = (EditText) view.findViewById(R.id.latest_password);
        EditText newPassword = (EditText) view.findViewById(R.id.new_password);

        Typeface roboto = TypefaceUtil.getCustomTypeface(getContext(), R.string.roboto_light);

        latestLayout.setTypeface(roboto);
        latestPassword.setTypeface(roboto);
        newLayout.setTypeface(roboto);
        newPassword.setTypeface(roboto);
    }

    /**
     *
     * @param dialog
     * @return
     */
    private Dialog setDialogParams(Dialog dialog) {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        Activity activity = mActivityReference.get();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);

        //Hacemos que el width del dialog tenga un tamaño del 95% de la pantalla en modo portrait.
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
