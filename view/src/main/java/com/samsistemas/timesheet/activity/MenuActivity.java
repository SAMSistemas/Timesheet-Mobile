package com.samsistemas.timesheet.activity;

import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;

import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.samsistemas.calendarview.util.CalendarUtil;
import com.samsistemas.calendarview.util.TypefaceUtil;
import com.samsistemas.calendarview.widget.CalendarView;
import com.samsistemas.timesheet.R;
import com.samsistemas.timesheet.activity.base.BaseAppCompatActivity;
import com.samsistemas.timesheet.adapter.JobLogAdapter;
import com.samsistemas.timesheet.adapter.ModelAdapter;
import com.samsistemas.timesheet.navigation.AccountNavigator;
import com.samsistemas.timesheet.navigation.SettingsNavigator;
import com.samsistemas.timesheet.navigation.base.AddHoursNavigator;
import com.samsistemas.timesheet.util.DateUtil;
import com.samsistemas.timesheet.viewmodel.JobLogViewModel;

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
public class MenuActivity extends BaseAppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, CalendarView.OnDateSelectedListener, CalendarView.OnMonthChangedListener {
    private RecyclerView.Adapter mAdapter;
    private CalendarView mCalendarView;
    private RecyclerView mRecyclerView;
    private Date mTodayDate;
    private TextView mDateTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        setTitle(R.string.action_view_calendar);
        setToolbar();
        setCollapsingToolbarLayout();
        setCalendarView();
        setRecyclerView();

        mTodayDate = new Date(System.currentTimeMillis());
        mDateTitle = (TextView) findViewById(R.id.date);
        mDateTitle.setTypeface(getRobotoMediumTypeface());
        mDateTitle.setText(DateUtil.formatDate(getApplicationContext(), mTodayDate));

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddHoursNavigator.newInstance().navigateWithAnimation(MenuActivity.this, view);
            }
        });
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
                AddHoursNavigator.newInstance()
                        .navigateWithAnimation(MenuActivity.this, navigationView);
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
    }

    @Override
    public void onMonthChanged(@NonNull Date currentMonth) {
        Calendar nextCalendar = getCalendar();
        nextCalendar.setTime(currentMonth);

        Calendar todayCalendar = getCalendar();
        todayCalendar.setTime(mTodayDate);

        if(CalendarUtil.isSameMonth(nextCalendar, todayCalendar)) {
            mCalendarView.setCurrentDay(mTodayDate);
            mDateTitle.setText(DateUtil.formatDate(getApplicationContext(), mTodayDate));
        } else {
            //We want this to not display any text..
            mDateTitle.setText(null);
        }
    }

    protected void setToolbar() {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setNavigationDrawer(toolbar);
    }

    protected void setCollapsingToolbarLayout() {
        final CollapsingToolbarLayout toolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolbarLayout.setTitleEnabled(false);
    }

    protected void setNavigationDrawer(@NonNull Toolbar toolbar) {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, 0, 0);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    protected void setCalendarView() {
        mTodayDate = new Date(System.currentTimeMillis());
        mCalendarView = (CalendarView) findViewById(R.id.calendar_view);

        mCalendarView.setFirstDayOfWeek(Calendar.MONDAY);
        mCalendarView.setNextButtonColor(R.color.accent);
        mCalendarView.setBackButtonColor(R.color.accent);
        mCalendarView.setCurrentDay(mTodayDate);
        mCalendarView.setIsOverflowDateVisible(true);
        mCalendarView.refreshCalendar(getCalendar());
        mCalendarView.setOnDateSelectedListener(this);
        mCalendarView.setOnMonthChangedListener(this);
    }

    protected void setRecyclerView() {
        final ModelAdapter modelAdapter = new ModelAdapter(getApplicationContext());
        List<JobLogViewModel> items = modelAdapter.getJobLogsByDate(mTodayDate);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        //This is done to optimize how the default views are showed. If there is no
        //item in the List, then it only shows an empty view.
        if(items.size() > 0)
            mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new JobLogAdapter(getApplicationContext(), items);

        //After creating we set it and tell the observer to show the changes over RecyclerView.
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    private Calendar getCalendar() {
        return Calendar.getInstance(Locale.getDefault());
    }

    private Typeface getRobotoMediumTypeface() {
        return TypefaceUtil.getCustomTypeface(getApplicationContext(), R.string.roboto_medium);
    }
}
