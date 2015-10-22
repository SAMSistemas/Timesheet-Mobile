package com.samsistemas.timesheet.service;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatDialog;
import android.view.View;

import com.samsistemas.timesheet.R;
import com.samsistemas.timesheet.controller.base.BaseLoginController;
import com.samsistemas.timesheet.controller.base.BaseSessionController;
import com.samsistemas.timesheet.factory.*;
import com.samsistemas.timesheet.util.NetworkUtil;
import com.samsistemas.timesheet.navigation.MenuNavigator;

/***
 * Service class used for login based operation. This class handles the login
 * operation inside a worker thread AsyncTask.
 *
 * @author jonatan.salas
 */
public class LoginService extends AsyncTask<String[], Void, Boolean> {
    private String[] mCredentials;
    private AppCompatDialog mProgressDialog;
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
        this.mContext = context;
        this.mView = view;
    }

    @UiThread
    @Override
    protected void onPreExecute() {
        if (!NetworkUtil.isConnected(mContext.getApplicationContext()))
            cancel(true);
        else {
            mProgressDialog = new AppCompatDialog(mContext);
            mProgressDialog.setContentView(R.layout.authentication_dialog);
            mProgressDialog.setCancelable(false);
            mProgressDialog.show();

            if (isCancelled())
                mContext.finish();
        }
    }

    @Override
    protected Boolean doInBackground(String[]... params) {
        boolean isLogged = false;
        final BaseLoginController loginController = getLoginController();
        storeCredentials(params[0]);

        //TODO JS: reemplazar esto cuando el servicio rest este definido.
        try {
            Thread.sleep(6000);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        if(NetworkUtil.isConnected(mContext.getApplicationContext()))
            isLogged = loginController.performLogin(mContext.getApplicationContext(), params[0]);

        return isLogged;
    }

    @UiThread
    @Override
    protected void onPostExecute(Boolean success) {
        final BaseSessionController sessionController = getSessionController();
        final String email = getCredentials()[0];
        mProgressDialog.dismiss();

        if(isCancelled())
            mContext.finish();

        if(success) {
            sessionController.createUserSession(mContext.getApplicationContext(), email);
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

    protected void storeCredentials(@NonNull final String[] credentials) {
        this.mCredentials = credentials;
    }

    protected String[] getCredentials() {
        return mCredentials;
    }

    protected BaseSessionController getSessionController() {
        return ControllerFactory.getSessionController();
    }

    protected BaseLoginController getLoginController() {
        return ControllerFactory.getLoginController();
    }
}
