package com.samsistemas.timesheet.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;

import com.samsistemas.timesheet.R;
import static com.samsistemas.timesheet.constant.SessionConst.*;
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
        final BaseSessionController sessionController = ControllerFactory.getSessionController();
        final String key = preference.getKey();

        if(key.equals(LOGGED_IN)) {
            sessionController.deleteUserSession(getContext(), this);
            return true;
        }

        return false;
    }

    @Override
    public void delete() {
        LoginNavigator.newInstance().navigate(getActivity());
    }
}
