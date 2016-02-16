package com.samsistemas.timesheet.screen.menu.activity;

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
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.samsistemas.calendarview.util.CalendarUtil;
import com.samsistemas.calendarview.util.TypefaceUtil;
import com.samsistemas.calendarview.widget.CalendarView;
import com.samsistemas.timesheet.R;
import com.samsistemas.timesheet.common.activity.base.BaseAppCompatActivity;
import com.samsistemas.timesheet.screen.addhours.activity.AddHoursActivity;
import com.samsistemas.timesheet.screen.menu.adapter.JobLogAdapter;
import com.samsistemas.timesheet.utility.ActivityUtility;
import com.samsistemas.timesheet.utility.DateUtility;
import com.samsistemas.timesheet.utility.SimpleTouchItemHelperCallback;
import com.samsistemas.timesheet.domain.JobLog;
import com.samsistemas.timesheet.screen.account.activity.AccountActivity;
import com.samsistemas.timesheet.screen.settings.activity.SettingsActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Class used as MenuActivity. It manages multiples fragments instances by
 * a NavigationDrawer.
 *
 * @author jonatan.salas
 */
public class MenuActivity extends BaseAppCompatActivity {
    public static final String DATE_KEY = "date";
    public static final String DATE_TEMPLATE = "dd-MM-yyyy";

    private JobLogAdapter mAdapter;
    private TextView mFullName;
    private TextView mUsername;
    private String mDateString;
//    private List<String> mCredentials = new ArrayList<>(2);

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.toolbar_layout)
    CollapsingToolbarLayout mCollapsingToolbarLayout;

    @Bind(R.id.fab)
    FloatingActionButton mFab;

    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @Bind(R.id.navigation_view)
    NavigationView mNavigationView;

    @Bind(R.id.date)
    TextView mDateTitle;

    @Bind(R.id.calendar_view)
    CalendarView mCalendarView;

    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @Override
    public int getLayoutResourceId() {
        return R.layout.activity_menu;
    }

    @Override
    public void setUserInterface() {
        ButterKnife.bind(this);
        setTitle(R.string.action_view_calendar);
        setSupportActionBar(mToolbar);

        //Style CollapsingToolbarLayout
        mCollapsingToolbarLayout.setTitleEnabled(false);

        //Style CalendarView
        mCalendarView.setFirstDayOfWeek(Calendar.MONDAY);
        mCalendarView.setNextButtonColor(R.color.accent);
        mCalendarView.setBackButtonColor(R.color.accent);
        mCalendarView.setCurrentDay(new Date(System.currentTimeMillis()));
        mCalendarView.setIsOverflowDateVisible(true);
        mCalendarView.refreshCalendar(Calendar.getInstance(Locale.getDefault()));

        //Style Navigation Drawer
        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, 0, 0);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        //Style headerView for NavigationView
        final View headerView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.drawer_header, mNavigationView, false);
        mFullName = (TextView) headerView.findViewById(R.id.username);
        mUsername = (TextView) headerView.findViewById(R.id.email);

        mNavigationView.addHeaderView(headerView);

        //Style RecyclerView
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setNestedScrollingEnabled(true);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new JobLogAdapter(this, new ArrayList<JobLog>());
        mRecyclerView.setAdapter(mAdapter);
        final ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SimpleTouchItemHelperCallback(mAdapter));
        itemTouchHelper.attachToRecyclerView(mRecyclerView);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void initialize() {
        Date date = new Date(System.currentTimeMillis());
        mDateString = new SimpleDateFormat(DATE_TEMPLATE, Locale.getDefault()).format(date);
    }

    @Override
    public void populateViews() {
        mDateTitle.setTypeface(getRobotoMediumTypeface());
        mDateTitle.setText(DateUtility.formatDate(getApplicationContext(), new Date(System.currentTimeMillis())));
    }

    @Override
    public void setListeners() {
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
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
                        break;

                    case R.id.action_add_hour:
                        ActivityUtility.startActivityWithAnimation(MenuActivity.this, AddHoursActivity.class, mNavigationView);
                        break;

                    case R.id.action_account:
                        ActivityUtility.startActivityWithAnimation(MenuActivity.this, AccountActivity.class, mNavigationView);
                        break;

                    case R.id.action_settings:
                        ActivityUtility.startActivityWithAnimation(MenuActivity.this, SettingsActivity.class, mNavigationView);
                        break;
                }

                mDrawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
        mCalendarView.setOnDateClickListener(new CalendarView.OnDateClickListener() {

            @Override
            public void onDateClick(@NonNull Date date) {
                mDateTitle.setText(DateUtility.formatDate(getApplicationContext(), date));
                SimpleDateFormat sdf = new SimpleDateFormat(DATE_TEMPLATE, Locale.getDefault());
                mDateString = sdf.format(date);
            }
        });
        mCalendarView.setOnMonthChangedListener(new CalendarView.OnMonthChangedListener() {
            @Override
            public void onMonthChanged(@NonNull Date date) {
                final Calendar nextCalendar = Calendar.getInstance(Locale.getDefault());
                nextCalendar.setTime(date);

                final Calendar todayCalendar = Calendar.getInstance(Locale.getDefault());
                todayCalendar.setTime(new Date(System.currentTimeMillis()));

                if (CalendarUtil.isSameMonth(nextCalendar, todayCalendar)) {
                    mCalendarView.setCurrentDay(new Date(System.currentTimeMillis()));
                    mDateTitle.setText(DateUtility.formatDate(getApplicationContext(), new Date(System.currentTimeMillis())));
                } else {
                    //We want this to display an announce, telling the user that has not any date selected..
                    mDateTitle.setText(getString(R.string.no_date_selected));
                }
            }
        });
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityUtility.startActivityWithAnimation(MenuActivity.this, AddHoursActivity.class, mNavigationView);
            }
        });
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

    private Typeface getRobotoMediumTypeface() {
        return TypefaceUtil.getCustomTypeface(getApplicationContext(), R.string.roboto_medium);
    }
}
