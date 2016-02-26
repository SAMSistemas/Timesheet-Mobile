package com.samsistemas.timesheet.screen.menu.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.jonisaa.commons.activity.BaseFragmentPresenterActivity;
import com.jonisaa.commons.fragment.CallbackFragment;

import com.samsistemas.timesheet.R;
import com.samsistemas.timesheet.domain.Person;
import com.samsistemas.timesheet.screen.account.fragment.AccountFragment;
import com.samsistemas.timesheet.screen.addhours.fragment.AddHoursFragment;
import com.samsistemas.timesheet.screen.menu.fragment.MenuFragment;
import com.samsistemas.timesheet.screen.menu.presenter.MainPresenter;
import com.samsistemas.timesheet.screen.menu.view.MainView;
import com.samsistemas.timesheet.screen.settings.activity.SettingsActivity;
import com.samsistemas.timesheet.utility.ActivityUtility;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author jonatan.salas
 */
public class MainActivity extends BaseFragmentPresenterActivity<CallbackFragment, MainPresenter>
        implements MainView, NavigationView.OnNavigationItemSelectedListener {

    private TextView fullName;
    private TextView username;

    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @Bind(R.id.navigation_view)
    NavigationView navigationView;

    @Override
    public MainPresenter createPresenter() {
        return MainPresenter.getInstance(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.action_view_calendar);

        //Style headerView for NavigationView
        View view = getLayoutInflater().inflate(R.layout.drawer_header, navigationView, false);

        fullName = ButterKnife.findById(view, R.id.username);
        username = ButterKnife.findById(view, R.id.email);

        navigationView.addHeaderView(view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        getPresenter().setUsernameData(getApplicationContext());
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
                break;
            case R.id.action_account:
                fragment = new AccountFragment();
                break;
            case R.id.action_settings:
                ActivityUtility
                        .startActivityWithAnimation(this, SettingsActivity.class, navigationView);
                break;
            default:
                break;
        }

        if (null != fragment) {
            final CallbackFragment callbackFragment = fragment;
            callbackFragment.setToolbarCallback(toolbarCallback);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment, callbackFragment)
                            .commit();
                }
            }, 150);
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
    public void restoreFragmentState(@Nullable Bundle bundle) { }

    @Override
    public void addPersonDataToNavigationDrawer(@Nullable Person person) {
        if (null != person) {
            final String name = person.getName() + " " + person.getLastName();
            final String user = person.getUsername() + "@samsistemas.com.ar";

            fullName.setText(name);
            username.setText(user);

        } else {
            Snackbar.make(navigationView, "There's a problem retrieving Person data.", Snackbar.LENGTH_SHORT).show();
        }
    }

    private final CallbackFragment.ToolbarCallback toolbarCallback = new CallbackFragment.ToolbarCallback() {
        @Override
        public void synchronize(@NonNull Toolbar toolbar) {
            final ActionBarDrawerToggle toggle =
                    new ActionBarDrawerToggle(MainActivity.this, drawerLayout, toolbar, 0, 0);
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
}
