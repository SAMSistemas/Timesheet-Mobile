package com.samsistemas.timesheet.activity.base;

import android.content.IntentFilter;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.samsistemas.timesheet.controller.base.BaseSessionController;
import com.samsistemas.timesheet.factory.ControllerFactory;
import com.samsistemas.timesheet.service.NetworkStateService;

/**
 * @author jonatan.salas
 */
//TODO JS: Delete unused methods.
public class BaseAppCompatActivity extends AppCompatActivity {

    /**
     *
     * @return
     */
    public BaseSessionController getSessionController() {
        return ControllerFactory.getSessionController();
    }

    /**
     *
     * @param onReceived
     * @return
     */
    public NetworkStateService getNetworkStateService(NetworkStateService.OnNetworkStateReceived onReceived) {
        return new NetworkStateService(onReceived);
    }

    /**
     *
     * @return
     */
    public IntentFilter getIntentFilter() {
        return new IntentFilter(NetworkStateService.CONNECTIVITY_CHANGE_ACTION);
    }

    /**
     *
     * @param fragment
     */
    public void addFragment(@NonNull Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(android.R.id.content, fragment)
                .commit();
    }
}
