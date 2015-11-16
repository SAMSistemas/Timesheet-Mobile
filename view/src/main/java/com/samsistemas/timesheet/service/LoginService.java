package com.samsistemas.timesheet.service;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.samsistemas.timesheet.R;
import com.samsistemas.timesheet.controller.base.BaseLoginController;
import com.samsistemas.timesheet.factory.ControllerFactory;
import com.samsistemas.timesheet.util.NetworkUtil;
import com.samsistemas.timesheet.navigation.MenuNavigator;

/***
 * Service class used for login based operation. This class handles the login
 * operation inside a worker thread AsyncTask.
 *
 * @author jonatan.salas
 */
public class LoginService extends AsyncTask<String[], Void, Boolean> {
    protected BaseLoginController loginController;
    private AlertDialog mDialog;
    private Activity mContext;
    private View mView;

    /**
     * Constructor with params.
     *
     * @param context - the context where this service is going to be used.
     * @param view - the view used to transform into an animation.
     */
    public LoginService(@NonNull Activity context,
                        @NonNull final View view) {
        this.loginController = ControllerFactory.getLoginController();
        this.mContext = context;
        this.mView = view;
    }

    @UiThread
    @Override
    protected void onPreExecute() {
        if (!NetworkUtil.isConnected(mContext.getApplicationContext()))
            cancel(true);
        else {
            final AlertDialog.Builder builder = new AlertDialog.Builder(mContext, R.style.AppTheme_Dialog);
            builder.setView(R.layout.authentication_dialog);
            builder.setCancelable(true);

            mDialog = builder.create();
            mDialog.show();

            if (isCancelled())
                mContext.finish();
        }
    }

    @Override
    protected Boolean doInBackground(String[]... params) {
        boolean isLogged = false;

        try {
            Thread.sleep(1000);
            isLogged = loginController.performLogin(mContext.getApplicationContext(), params[0]);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return isLogged;
    }

    @UiThread
    @Override
    protected void onPostExecute(Boolean success) {
        mDialog.dismiss();

        if(isCancelled())
            mContext.finish();

        if(success) {
            MenuNavigator.newInstance().navigateWithAnimation(mContext, mView);

        } else if(NetworkUtil.isConnected(mContext.getApplicationContext())){
            Snackbar.make(mView, mContext.getString(R.string.login_error), Snackbar.LENGTH_LONG).show();
        }
    }

    /***
     * Method that gets a Service instance.
     *
     * @param context - the context where the service is going to be used.
     * @param view - the view to implement as an animation.
     * @return a singleton object.
     */
    public static LoginService newInstance(@NonNull Activity context, @NonNull final View view) {
        return new LoginService(context, view);
    }
}
