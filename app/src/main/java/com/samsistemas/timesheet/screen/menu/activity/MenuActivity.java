package com.samsistemas.timesheet.screen.menu.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.samsistemas.timesheet.R;
import com.samsistemas.timesheet.common.callback.NestedFragmentCallback;
import com.samsistemas.timesheet.common.callback.ToolbarCallback;
import com.samsistemas.timesheet.common.fragment.BaseFragment;
import com.samsistemas.timesheet.screen.account.fragment.AccountFragment;
import com.samsistemas.timesheet.screen.addhours.fragment.AddHoursFragment;
import com.samsistemas.timesheet.screen.menu.fragment.MenuFragment;
import com.samsistemas.timesheet.screen.settings.activity.SettingsActivity;
import com.samsistemas.timesheet.utility.ActivityUtility;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Class used as MenuActivity. It manages multiples fragments instances by
 * a NavigationDrawer.
 *
 * @author jonatan.salas
 */
public class MenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private TextView fullname;
    private TextView username;

    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @Bind(R.id.navigation_view)
    NavigationView navigationView;

    private final ToolbarCallback toolbarCallback = new ToolbarCallback() {
        @Override
        public void synchronize(@NonNull Toolbar toolbar) {
            final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(MenuActivity.this, drawerLayout, toolbar, 0, 0);
            drawerLayout.setDrawerListener(toggle);
            toggle.syncState();
        }
    };

    private final NestedFragmentCallback nestedFragmentCallback = new NestedFragmentCallback() {
        @Override
        public void placeNestedFragment() {
            final BaseFragment fragment = new AddHoursFragment();
            fragment.setToolbarCallback(toolbarCallback);

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment, fragment)
                    .commit();
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ButterKnife.bind(this);
        setTitle(R.string.action_view_calendar);

        if (null == savedInstanceState) {
            BaseFragment fragment = new MenuFragment();
            fragment.setToolbarCallback(toolbarCallback);
            fragment.setNestedFragmentCallback(nestedFragmentCallback);

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment, fragment)
                    .commit();
        }

        //Style headerView for NavigationView
        final View headerView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.drawer_header, navigationView, false);
        fullname = (TextView) headerView.findViewById(R.id.username);
        username = (TextView) headerView.findViewById(R.id.email);

        navigationView.addHeaderView(headerView);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        final boolean isChecked = item.isChecked();
        final int id = item.getItemId();

        if (!isChecked) {
            item.setChecked(true);
        }

        BaseFragment fragment = null;

        switch (id) {
            case R.id.action_view_calendar:
                fragment = new MenuFragment();
                break;
            case R.id.action_add_hour:
                fragment = new AddHoursFragment();
                break;
            case R.id.action_account:
                fragment = new AccountFragment();
                break;
            case R.id.action_settings:
                ActivityUtility
                        .startActivityWithAnimation(this, SettingsActivity.class, navigationView);
                break;
            default: break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);

        if (null != fragment) {
            fragment.setToolbarCallback(toolbarCallback);

            if (fragment instanceof MenuFragment) {
                fragment.setNestedFragmentCallback(nestedFragmentCallback);
            }

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment, fragment)
                    .commit();
        }

        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            finish();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull final MenuItem item) {
        final int id = item.getItemId();

        switch (id) {
            case android.support.v7.appcompat.R.id.home:
                onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }
}
