package com.samsistemas.timesheet.activity;

import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;

import android.support.design.widget.Snackbar;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.samsistemas.calendarview.util.CalendarUtil;
import com.samsistemas.calendarview.util.TypefaceUtil;
import com.samsistemas.calendarview.widget.CalendarView;
import com.samsistemas.timesheet.R;
import com.samsistemas.timesheet.adapter.JobLogAdapter;
import com.samsistemas.timesheet.loader.JobLogsLoader;
import com.samsistemas.timesheet.loader.PersonLoader;
import com.samsistemas.timesheet.model.JobLog;
import com.samsistemas.timesheet.model.Person;
import com.samsistemas.timesheet.navigation.AccountNavigator;
import com.samsistemas.timesheet.navigation.SettingsNavigator;
import com.samsistemas.timesheet.navigation.base.AddHoursNavigator;
import com.samsistemas.timesheet.util.DateUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Class used as MenuActivity. It manages multiples fragments instances by
 * a NavigationDrawer.
 *
 * @author jonatan.salas
 */
public class MenuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, CalendarView.OnDateSelectedListener, CalendarView.OnMonthChangedListener {
    private static final int PERSON_LOADER_ID = 0;
    private static final int JOBLOG_LOADER_ID = 1;

    private JobLogAdapter mAdapter;

    private TextView mFullName;
    private TextView mUsername;
    private TextView mDateTitle;

    private CalendarView mCalendarView;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Use this to check troubles
        //DevUtil.enableStrictModeChecker();
        setContentView(R.layout.activity_menu);
        setTitle(R.string.action_view_calendar);
        setToolbar();
        setCollapsingToolbarLayout();
        setCalendarView();
        setRecyclerView();

        mDateTitle = (TextView) findViewById(R.id.date);
        mDateTitle.setTypeface(getRobotoMediumTypeface());
        mDateTitle.setText(DateUtil.formatDate(getApplicationContext(), new Date(System.currentTimeMillis())));

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddHoursNavigator.newInstance().navigateWithAnimation(MenuActivity.this, view);
            }
        });

        initPersonLoader();
        initJobLogLoader();
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
                break;

            case R.id.action_add_hour:
                AddHoursNavigator.newInstance().navigateWithAnimation(this, navigationView);
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
        mDateTitle.setText(DateUtil.formatDate(getApplicationContext(), selectedDate));
        //We call this in order to show the data available for the date user-selected
    }

    @Override
    public void onMonthChanged(@NonNull Date currentMonth) {
        final Calendar nextCalendar = Calendar.getInstance(Locale.getDefault());
        nextCalendar.setTime(currentMonth);

        final Calendar todayCalendar = Calendar.getInstance(Locale.getDefault());
        todayCalendar.setTime(new Date(System.currentTimeMillis()));

        if(CalendarUtil.isSameMonth(nextCalendar, todayCalendar)) {
            mCalendarView.setCurrentDay(new Date(System.currentTimeMillis()));
            mDateTitle.setText(DateUtil.formatDate(getApplicationContext(), new Date(System.currentTimeMillis())));
        } else {
            //We want this to display an announce, telling the user that has not any date selected..
            mDateTitle.setText(getString(R.string.no_date_selected));
        }
    }

    private void setToolbar() {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setNavigationDrawer(toolbar);
    }

    private void setCollapsingToolbarLayout() {
        final CollapsingToolbarLayout toolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolbarLayout.setTitleEnabled(false);
    }

    private void setNavigationDrawer(@NonNull Toolbar toolbar) {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, 0, 0);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        final View headerView = inflater.inflate(R.layout.drawer_header, null, false);
        mFullName = (TextView) headerView.findViewById(R.id.username);
        mUsername = (TextView) headerView.findViewById(R.id.email);

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.addHeaderView(headerView);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void setCalendarView() {
        mCalendarView = (CalendarView) findViewById(R.id.calendar_view);
        mCalendarView.setFirstDayOfWeek(Calendar.MONDAY);
        mCalendarView.setNextButtonColor(R.color.accent);
        mCalendarView.setBackButtonColor(R.color.accent);
        mCalendarView.setCurrentDay(new Date(System.currentTimeMillis()));
        mCalendarView.setIsOverflowDateVisible(true);
        mCalendarView.refreshCalendar(Calendar.getInstance(Locale.getDefault()));
        mCalendarView.setOnDateSelectedListener(this);
        mCalendarView.setOnMonthChangedListener(this);
    }

    private void setRecyclerView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setNestedScrollingEnabled(true);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new JobLogAdapter(getApplicationContext(), new ArrayList<JobLog>());
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    private Typeface getRobotoMediumTypeface() {
        return TypefaceUtil.getCustomTypeface(getApplicationContext(), R.string.roboto_medium);
    }

    private void initPersonLoader() {
        getSupportLoaderManager().initLoader(PERSON_LOADER_ID, null, new LoaderManager.LoaderCallbacks<Person>() {

            @Override
            public Loader<Person> onCreateLoader(int id, Bundle args) {
                if (id == PERSON_LOADER_ID) {
                    return new PersonLoader(getApplicationContext());
                }

                return null;
            }

            @Override
            public void onLoadFinished(Loader<Person> loader, Person data) {
                if (null != data) {
                    final String fullName = data.getName() + " " + data.getLastName();
                    final String fullUsername = data.getUsername() + getApplicationContext().getString(R.string.domain);
                    mFullName.setText(fullName);
                    mUsername.setText(fullUsername);
                } else {
                    loader.reset();
                    Snackbar.make(mRecyclerView, "Ops, we can't load your data now..", Snackbar.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onLoaderReset(Loader<Person> loader) {
                if (!loader.isReset()) {
                    loader.reset();
                }
            }

        }).forceLoad();
    }

    private void initJobLogLoader() {
        getSupportLoaderManager().initLoader(JOBLOG_LOADER_ID, null, new LoaderManager.LoaderCallbacks<List<JobLog>>() {

            @Override
            public Loader<List<JobLog>> onCreateLoader(int id, Bundle args) {
                if (id == JOBLOG_LOADER_ID) {
                    return new JobLogsLoader(getApplicationContext());
                }

                return null;
            }

            @Override
            public void onLoadFinished(Loader<List<JobLog>> loader, List<JobLog> data) {
                if (null != data && !data.isEmpty()) {
                    mAdapter.setItems(null);
                    mAdapter.setItems(data);
                    mRecyclerView.setAdapter(mAdapter);
                    mAdapter.notifyDataSetChanged();
                } else {
                    Snackbar.make(mRecyclerView, "Ups, It seems you don't have any joblog", Snackbar.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onLoaderReset(Loader<List<JobLog>> loader) {
                if (!loader.isReset()) {
                    loader.reset();
                }
            }
        }).forceLoad();
    }
}
