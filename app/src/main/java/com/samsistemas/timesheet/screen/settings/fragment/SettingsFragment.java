package com.samsistemas.timesheet.screen.settings.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.content.IntentCompat;
import android.support.v7.preference.Preference;
import android.support.v7.preference.XpPreferenceFragment;

import com.samsistemas.timesheet.R;
import com.samsistemas.timesheet.common.callback.ToolbarCallback;
import com.samsistemas.timesheet.utility.PreferenceUtility;
import com.samsistemas.timesheet.screen.login.activity.LoginActivity;
import com.samsistemas.timesheet.screen.settings.presenter.SettingsPresenterImpl;
import com.samsistemas.timesheet.screen.settings.presenter.base.SettingsPresenter;
import com.samsistemas.timesheet.screen.settings.view.SettingsView;
import com.samsistemas.timesheet.utility.ThreadUtility;

/**
 * @author jonatan.salas
 */
public class SettingsFragment extends XpPreferenceFragment implements SettingsView {
    private SettingsPresenter settingsPresenter;
    ToolbarCallback callback;

    public SettingsFragment() {
        setHasOptionsMenu(false);
        settingsPresenter = new SettingsPresenterImpl();
    }

    @Override
    public void onCreatePreferences2(Bundle bundle, String s) {
        ThreadUtility.runInBackground(new ThreadUtility.CallBack<Void>() {
            @Override
            public Void execute() {
                addPreferencesFromResource(R.xml.prefs);
                return null;
            }
        });
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
        final Long sessionId = PreferenceUtility.getSessionId(getContext());

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
        Snackbar.make(getListView(), R.string.logout_error, Snackbar.LENGTH_SHORT).show();
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

    public void setToolbarCallback(@NonNull ToolbarCallback callback) {
        this.callback = callback;
    }
}
