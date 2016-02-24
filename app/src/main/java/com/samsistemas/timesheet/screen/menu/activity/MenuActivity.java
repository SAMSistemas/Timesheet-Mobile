package com.samsistemas.timesheet.screen.menu.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.jonisaa.commons.activity.BaseFragmentActivity;
import com.jonisaa.commons.fragment.CallbackFragment;

import com.samsistemas.timesheet.R;
import com.samsistemas.timesheet.screen.account.fragment.AccountFragment;
import com.samsistemas.timesheet.screen.addhours.fragment.AddHoursFragment;
import com.samsistemas.timesheet.screen.menu.fragment.MenuFragment;
import com.samsistemas.timesheet.screen.settings.activity.SettingsActivity;
import com.samsistemas.timesheet.utility.ActivityUtility;

import butterknife.Bind;

/**
 * Class used as MenuActivity. It manages multiples fragments instances by
 * a NavigationDrawer.
 *
 * @author jonatan.salas
 */
public class MenuActivity extends BaseFragmentActivity<CallbackFragment>
        implements NavigationView.OnNavigationItemSelectedListener {
    private TextView fullname;
    private TextView username;

    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @Bind(R.id.navigation_view)
    NavigationView navigationView;

    private final CallbackFragment.ToolbarCallback toolbarCallback = new CallbackFragment.ToolbarCallback() {
        @Override
        public void synchronize(@NonNull Toolbar toolbar) {
            final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(MenuActivity.this, drawerLayout, toolbar, 0, 0);
            drawerLayout.setDrawerListener(toggle);
            toggle.syncState();
        }
    };

    private final CallbackFragment.NestedFragmentCallback nestedFragmentCallback = new CallbackFragment.NestedFragmentCallback() {
        @Override
        public void placeNestedFragment() {
            final AddHoursFragment fragment = new AddHoursFragment();
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
        setTitle(R.string.action_view_calendar);

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

        CallbackFragment fragment = null;

        switch (id) {
            case R.id.action_view_calendar:
                fragment = getFragment();
                break;
            case R.id.action_add_hour:
                fragment = new AddHoursFragment();
                fragment.setToolbarCallback(toolbarCallback);
                break;
            case R.id.action_account:
                fragment = new AccountFragment();
                fragment.setToolbarCallback(toolbarCallback);
                break;
            case R.id.action_settings:
                ActivityUtility
                        .startActivityWithAnimation(this, SettingsActivity.class, navigationView);
                break;
            default: break;
        }

        if (null != fragment) {
            final CallbackFragment callbackFragment = fragment;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment, callbackFragment)
                            .commit();
                }
            }, 250);
        }

        drawerLayout.closeDrawer(GravityCompat.START);
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

    @Override
    public int getLayout() {
        return R.layout.activity_menu;
    }

    @Override
    public int getContent() {
        return R.id.fragment;
    }

    @Nullable
    @Override
    public CallbackFragment createFragment() {
        final CallbackFragment fragment = new MenuFragment();
        fragment.setToolbarCallback(toolbarCallback);
        fragment.setNestedFragmentCallback(nestedFragmentCallback);

        return fragment;
    }

    @Override
    public void restoreFragmentState(@Nullable Bundle bundle) {

    }
}
