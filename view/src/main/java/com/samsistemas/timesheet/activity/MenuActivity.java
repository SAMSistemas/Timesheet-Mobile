package com.samsistemas.timesheet.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;

import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.samsistemas.calendarview.widget.CalendarView;
import com.samsistemas.timesheet.R;
import com.samsistemas.timesheet.activity.base.BaseAppCompatActivity;
import com.samsistemas.timesheet.navigation.AccountNavigator;
import com.samsistemas.timesheet.navigation.SettingsNavigator;
import com.samsistemas.timesheet.util.DateUtil;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Class used as MenuActivity. It manages multiples fragments instances by
 * a NavigationDrawer.
 *
 * @author jonatan.salas
 */
public class MenuActivity extends BaseAppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, CalendarView.OnDateSelectedListener, CalendarView.OnMonthChangedListener {
    private static final String LOG_TAG = MenuActivity.class.getSimpleName();
    private CalendarView mCalendarView;
    private TextView mDateTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        setTitle(R.string.action_view_calendar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        CollapsingToolbarLayout toolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolbarLayout.setTitleEnabled(false);

        mDateTitle = (TextView) findViewById(R.id.date);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mCalendarView = (CalendarView) findViewById(R.id.calendar_view);
        mCalendarView.setFirstDayOfWeek(Calendar.MONDAY);
        mCalendarView.setIsOverflowDateVisible(true);
        mCalendarView.refreshCalendar(getCalendar());
        mCalendarView.setOnDateSelectedListener(this);
        mCalendarView.setOnMonthChangedListener(this);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, 0, 0);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        final ActionBar actionBar = getSupportActionBar();

        ListView listView = (ListView) findViewById(R.id.list_view);
        String[] array = new String[] {
            "lalaalalaallalalalallalala",
            "lalaalalaallalalalallalala",
            "lalaalalaallalalalallalala",
            "lalaalalaallalalalallalala",
            "lalaalalaallalalalallalala",
            "lalaalalaallalalalallalala",
            "lalaalalaallalalalallalala",
            "lalaalalaallalalalallalala",
            "lalaalalaallalalalallalala",
            "lalaalalaallalalalallalala",
            "lalaalalaallalalalallalala",
            "lalaalalaallalalalallalala",
            "lalaalalaallalalalallalala",
            "lalaalalaallalalalallalala"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, array);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
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
    public boolean onNavigationItemSelected(@NonNull final MenuItem menuItem) {
        final boolean isChecked = menuItem.isChecked();
        final int id = menuItem.getItemId();
        final NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);

        if (!isChecked) {
            menuItem.setChecked(true);
        }

        switch (id) {
            case R.id.action_view_calendar:
                setTitle(R.string.action_view_calendar);
                //addFragment(mFragment);
                break;

            case R.id.action_add_hour:
                setTitle(R.string.action_add_hour);
                //addFragment(mFragment);
                break;

            case R.id.action_account:
                AccountNavigator.newInstance().navigateWithAnimation(this, navigationView);
                break;

            case R.id.action_settings:
                SettingsNavigator.newInstance().navigateWithAnimation(this, navigationView);
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void onDateSelected(@NonNull Date selectedDate) {
        String dateTitle = DateUtil.formatDate(getApplicationContext(), selectedDate);
        mDateTitle.setText(dateTitle);
    }

    @Override
    public void onMonthChanged(@NonNull Date currentMonth) {
        mDateTitle.setText("");
    }

    private Calendar getCalendar() {
        return Calendar.getInstance(Locale.getDefault());
    }
}
