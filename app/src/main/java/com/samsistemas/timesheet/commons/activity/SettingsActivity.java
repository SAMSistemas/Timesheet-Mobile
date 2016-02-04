package com.samsistemas.timesheet.commons.activity;

import android.support.v7.app.ActionBar;
import android.view.MenuItem;

import com.samsistemas.timesheet.R;
import com.samsistemas.timesheet.commons.activity.base.BaseAppCompatActivity;
import com.samsistemas.timesheet.commons.fragment.ApplicationSettingsFragment;
import com.samsistemas.timesheet.commons.util.ToolbarUtil;
import com.samsistemas.timesheet.commons.navigation.MenuNavigator;

/**
 * Class that represents the Settings Screen to the user.
 *
 * @author jonatan.salas
 */
public class SettingsActivity extends BaseAppCompatActivity {

    @Override
    public int getLayoutResourceId() {
        return android.R.layout.activity_list_item;
    }

    @Override
    public void setUserInterface() {
        final ActionBar actionBar = getSupportActionBar();

        if (null != actionBar) {
            ToolbarUtil.styleWithBackButton(actionBar, R.string.action_settings);
        }
    }

    @Override
    public void initialize() {
        getSupportFragmentManager()
                .beginTransaction()
                .add(android.R.id.content, new ApplicationSettingsFragment())
                .commit();
    }

    @Override
    public void populateViews() { }

    @Override
    public void setListeners() { }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
        }

        return true;
    }

    @Override
    public void onBackPressed() {
        MenuNavigator.newInstance().navigate(this);
    }
}
