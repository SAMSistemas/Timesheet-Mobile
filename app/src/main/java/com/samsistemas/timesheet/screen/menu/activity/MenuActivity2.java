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
import com.samsistemas.timesheet.common.callback.ToolbarCallback;
import com.samsistemas.timesheet.screen.account.activity.AccountActivity;
import com.samsistemas.timesheet.screen.addhours.activity.AddHoursActivity;
import com.samsistemas.timesheet.screen.menu.fragment.MenuFragment;
import com.samsistemas.timesheet.screen.settings.fragment.SettingsFragment;
import com.samsistemas.timesheet.utility.ActivityUtility;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Class used as MenuActivity. It manages multiples fragments instances by
 * a NavigationDrawer.
 *
 * @author jonatan.salas
 */
public class MenuActivity2 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TextView mFullName;
    private TextView mUsername;
    private String mDateString;

    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @Bind(R.id.navigation_view)
    NavigationView mNavigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu2);
        ButterKnife.bind(this);
        setTitle(R.string.action_view_calendar);

        final MenuFragment fragment = new MenuFragment();
        fragment.setToolbarCallback(new ToolbarCallback() {
            @Override
            public void synchronize(@NonNull Toolbar toolbar) {
                final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(MenuActivity2.this, mDrawerLayout, toolbar, 0, 0);
                mDrawerLayout.setDrawerListener(toggle);
                toggle.syncState();
            }
        });

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment, fragment)
                .commit();

        //Style headerView for NavigationView
        final View headerView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.drawer_header, mNavigationView, false);
        mFullName = (TextView) headerView.findViewById(R.id.username);
        mUsername = (TextView) headerView.findViewById(R.id.email);

        mNavigationView.addHeaderView(headerView);
        mNavigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        final boolean isChecked = item.isChecked();
        final int id = item.getItemId();

        if (!isChecked) {
            item.setChecked(true);
        }

        switch (id) {
            case R.id.action_view_calendar:
                setTitle(R.string.action_view_calendar);

                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment, new MenuFragment())
                        .commit();

                break;

            case R.id.action_add_hour:
                ActivityUtility.startActivityWithAnimation(MenuActivity2.this, AddHoursActivity.class, mNavigationView);
                break;

            case R.id.action_account:
                ActivityUtility.startActivityWithAnimation(MenuActivity2.this, AccountActivity.class, mNavigationView);
                break;

            case R.id.action_settings:
                setTheme(R.style.AppTheme);

                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment, new SettingsFragment())
                        .commit();
                break;
        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
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
