package com.samsistemas.timesheet.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;

import com.samsistemas.timesheet.R;
import com.samsistemas.timesheet.controller.base.BaseSessionController;
import com.samsistemas.timesheet.factory.ControllerFactory;
import com.samsistemas.timesheet.navigation.LoginNavigator;

/**
 * @author jonatan.salas
 */
public class SettingsFragment extends PreferenceFragmentCompat implements BaseSessionController.OnSessionDeleted {

    public SettingsFragment() {
        setHasOptionsMenu(false);
    }

    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        addPreferencesFromResource(R.xml.pref_general);
    }

    @Override
    public boolean onPreferenceTreeClick(@NonNull Preference preference) {
        final BaseSessionController sessionController = getSessionController();
        final String key = preference.getKey();

        if(key.equals(getString(R.string.is_logged_in))) {
            sessionController.deleteUserSession(getApplicationContext(), this);
            return true;
        }

        return false;
    }

    @Override
    public void delete() {
        LoginNavigator.newInstance().navigate(getActivity());
    }

    /**
     *
     * @return
     */
    private Context getApplicationContext() {
        return getContext().getApplicationContext();
    }

    /**
     *
     * @return
     */
    protected BaseSessionController getSessionController() {
        return ControllerFactory.getSessionController();
    }
}
