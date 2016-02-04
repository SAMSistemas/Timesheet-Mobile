package com.samsistemas.timesheet.settings.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.content.IntentCompat;
import android.support.v7.preference.Preference;
import android.support.v7.preference.XpPreferenceFragment;

import com.samsistemas.timesheet.R;
import com.samsistemas.timesheet.login.activity.LoginActivity;
import com.samsistemas.timesheet.settings.presenter.SettingsPresenterImpl;
import com.samsistemas.timesheet.settings.presenter.base.SettingsPresenter;
import com.samsistemas.timesheet.settings.view.SettingsView;

/**
 * @author jonatan.salas
 */
public class SettingsFragment extends XpPreferenceFragment implements SettingsView {
//    private static final String SESSION_KEY = "session_id";
    private SettingsPresenter settingsPresenter;

    public SettingsFragment() {
        setHasOptionsMenu(false);
        settingsPresenter = new SettingsPresenterImpl();
    }

    @Override
    public void onCreatePreferences2(Bundle bundle, String s) {
        addPreferencesFromResource(R.xml.prefs);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        settingsPresenter.setSettingsView(this);
    }

    @Override
    public void onDestroy() {
        settingsPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public boolean onPreferenceTreeClick(@NonNull Preference preference) {
//        final SharedPreferences preferences = getPreferenceManager().getSharedPreferences();
//        final Long sessionId = preferences.getLong(SESSION_KEY, 0);
        final Long sessionId = 0L;

        final String logout = getString(R.string.action_logout);
        final String preferenceTitle = preference.getTitle().toString();

        if (preferenceTitle.equals(logout)) {
            settingsPresenter.logout(sessionId);
            return true;
        }

        return false;
    }

    @Override
    public void showLogoutError() {
        Snackbar.make(getListView(), "Error trying to logout. Please, try again", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void navigateToLogin() {
        final Intent intent = new Intent(getActivity().getApplicationContext(), LoginActivity.class);

        intent.setFlags(
                Intent.FLAG_ACTIVITY_NEW_TASK |
                IntentCompat.FLAG_ACTIVITY_CLEAR_TASK |
                Intent.FLAG_ACTIVITY_CLEAR_TOP
        );

        startActivity(intent);
        getActivity().finish();
    }
}
