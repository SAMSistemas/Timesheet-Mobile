package com.samsistemas.timesheet.activity;

import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;

import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.DefaultItemAnimator;
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
    private JobLogAdapter mAdapter;
    private CalendarView mCalendarView;
    private RecyclerView mRecyclerView;
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

        mDateTitle = (TextView) findViewById(R.id.date);
        mDateTitle.setTypeface(getRobotoMediumTypeface());
        mDateTitle.setText(DateUtil.formatDate(getApplicationContext(), getCurrentDate()));

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddHoursNavigator.newInstance().navigateWithAnimation(MenuActivity.this, view);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        //When Current View is resumed we come to initial status. We expect the user to follow today job logs.
        resetAdapter(getCurrentDate());
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
        //We call this in order to show the data available for the date user-selected.
        resetAdapter(selectedDate);
    }

    @Override
    public void onMonthChanged(@NonNull Date currentMonth) {
        final Calendar nextCalendar = getCalendar();
        nextCalendar.setTime(currentMonth);

        final Calendar todayCalendar = getCalendar();
        todayCalendar.setTime(getCurrentDate());

        //This method get All job logs for date, and paint calendar.
        //paintCalendarByMonth(currentMonth);

        if(CalendarUtil.isSameMonth(nextCalendar, todayCalendar)) {
            mCalendarView.setCurrentDay(getCurrentDate());
            mDateTitle.setText(DateUtil.formatDate(getApplicationContext(), getCurrentDate()));
            resetAdapter(getCurrentDate());
        } else {
            //We want this to display an announce, telling the user that has not any date selected..
            mDateTitle.setText(getString(R.string.no_date_selected));
            //This remove old list by date showed.
            //Expecting the user to select a new one.
            deleteAdapterData();
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
        mCalendarView = (CalendarView) findViewById(R.id.calendar_view);

        mCalendarView.setFirstDayOfWeek(Calendar.MONDAY);
        mCalendarView.setNextButtonColor(R.color.accent);
        mCalendarView.setBackButtonColor(R.color.accent);
        mCalendarView.setCurrentDay(getCurrentDate());
        mCalendarView.setIsOverflowDateVisible(true);
        mCalendarView.refreshCalendar(getCalendar());
        mCalendarView.setOnDateSelectedListener(this);
        mCalendarView.setOnMonthChangedListener(this);

        //paintCalendarByMonth(getCurrentDate());
    }

    protected void setRecyclerView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mAdapter = new JobLogAdapter(getApplicationContext(), getListFilteredByDate(getCurrentDate()));

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setNestedScrollingEnabled(true);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        //After creating we set it, telling the observer to show the changes over RecyclerView.
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    protected void resetAdapter(@NonNull Date date) {
        mAdapter.setItems(getListFilteredByDate(date));
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    protected void deleteAdapterData() {
        mAdapter.setItems(null);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    private Calendar getCalendar() {
        return Calendar.getInstance(Locale.getDefault());
    }

    private Date getCurrentDate() {
        return new Date(System.currentTimeMillis());
    }

    private Typeface getRobotoMediumTypeface() {
        return TypefaceUtil.getCustomTypeface(getApplicationContext(), R.string.roboto_medium);
    }

    private List<JobLogViewModel> getListFilteredByDate(@NonNull Date dateToFilter) {
        final ModelAdapter modelAdapter = new ModelAdapter(getApplicationContext());
        return modelAdapter.getJobLogsByDate(dateToFilter);
    }
}
