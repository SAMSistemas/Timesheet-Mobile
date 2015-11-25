package com.samsistemas.timesheet.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;

import com.samsistemas.timesheet.R;
import com.samsistemas.timesheet.activity.base.BaseAppCompatActivity;
import com.samsistemas.timesheet.fragment.SettingsFragment;
import com.samsistemas.timesheet.util.ToolbarUtil;
import com.samsistemas.timesheet.navigation.MenuNavigator;

/**
 * Class that represents the Settings Screen to the user.
 *
 * @author jonatan.salas
 */
public class SettingsActivity extends BaseAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Use this to check troubles
        //DevUtil.enableStrictModeChecker();
        final ActionBar actionBar = getSupportActionBar();
        if(null != actionBar)
            ToolbarUtil.styleWithBackButton(actionBar, R.string.action_settings);

        if(null == savedInstanceState) {
            addFragment(new SettingsFragment());
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int id = item.getItemId();
        if(id == android.R.id.home)
            onBackPressed();

        return true;
    }

    @Override
    public void onBackPressed() {
        MenuNavigator.newInstance().navigate(this);
    }
}
