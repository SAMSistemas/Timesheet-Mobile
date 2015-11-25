package com.samsistemas.timesheet.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;

import com.samsistemas.timesheet.R;
import com.samsistemas.timesheet.activity.base.BaseAppCompatActivity;
import com.samsistemas.timesheet.controller.base.BaseSessionController;
import com.samsistemas.timesheet.service.NetworkStateService;
import com.samsistemas.timesheet.util.DevUtil;
import com.samsistemas.timesheet.util.ToolbarUtil;
import com.samsistemas.timesheet.navigation.MenuNavigator;
import com.samsistemas.timesheet.fragment.VerifyConnectionFragment;

import java.lang.ref.WeakReference;

/**
 * Activity class that represents a Login UI controller.
 *
 * @author jonatan.salas
 */
public class LoginActivity extends BaseAppCompatActivity implements BaseSessionController.OnSessionRestored, NetworkStateService.OnNetworkStateReceived {
    private NetworkStateService mNetworkStateService = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Use this to check troubles
        //DevUtil.enableStrictModeChecker();
        setContentView(R.layout.activity_login);
        final ActionBar actionBar = getSupportActionBar();

        if(null != actionBar)
            ToolbarUtil.styleBar(actionBar, R.string.login);

        mNetworkStateService = getNetworkStateService(this);
        registerReceiver(mNetworkStateService, getIntentFilter());
        getSessionController().restoreUserSession(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        getSessionController().restoreUserSession(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
        getSessionController().restoreUserSession(this);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getSessionController().restoreUserSession(this);
    }

    @Override
    protected void onDestroy() {
        if (null != mNetworkStateService) {
            unregisterReceiver(mNetworkStateService);
        }

        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void restore() {
        if (getSessionController().isLoggedIn(getApplicationContext())) {
            MenuNavigator.newInstance().navigate(this);
        }
    }

    @Override
    public void checkNetworkState(Context context, Boolean isConnected) {
        if(!isConnected) {
            VerifyConnectionFragment verifyConnection = new VerifyConnectionFragment();
            verifyConnection.setActivityReference(new WeakReference<Activity>(this));
            verifyConnection.show(getSupportFragmentManager(), VerifyConnectionFragment.TAG);
        }
    }
}
