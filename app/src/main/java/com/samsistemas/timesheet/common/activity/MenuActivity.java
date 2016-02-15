package com.samsistemas.timesheet.common.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;

import android.support.v4.app.ActivityCompat;
import android.support.v4.content.IntentCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
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
import com.samsistemas.timesheet.common.adapter.JobLogAdapter;
import com.samsistemas.timesheet.common.animation.ScaleUpAnimator;
import com.samsistemas.timesheet.common.utility.DateUtility;
import com.samsistemas.timesheet.domain.JobLog;
import com.samsistemas.timesheet.common.navigation.SettingsNavigator;
import com.samsistemas.timesheet.common.utility.SimpleTouchItemHelperCallback;
import com.samsistemas.timesheet.screen.account.activity.AccountActivity;

import static com.samsistemas.timesheet.common.utility.AppConstants.DATE_KEY;
import static com.samsistemas.timesheet.common.utility.AppConstants.DATE_TEMPLATE;

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
                        startAddHoursActivity();
                        break;

                    case R.id.action_account:
                        final Bundle options = ScaleUpAnimator.newInstance().saveAnimation(mNavigationView);
                        final Intent intent = new Intent(getApplicationContext(), AccountActivity.class);

                        intent.setFlags(
                                Intent.FLAG_ACTIVITY_NEW_TASK |
                                        IntentCompat.FLAG_ACTIVITY_CLEAR_TASK |
                                        Intent.FLAG_ACTIVITY_CLEAR_TOP
                        );

                        ActivityCompat.startActivity(MenuActivity.this, intent, options);
                        finish();
                        break;
                    case R.id.action_settings:
                        SettingsNavigator.newInstance().navigateWithAnimation(MenuActivity.this, mNavigationView);
                        break;
                }

                mDrawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
        mCalendarView.setOnDateSelectedListener(new CalendarView.OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull Date date) {
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
                startAddHoursActivity();
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

    private void startAddHoursActivity() {
        final Intent addHoursIntent = new Intent(getApplicationContext(), AddHoursActivity.class);
        addHoursIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        addHoursIntent.putExtra(DATE_KEY, mDateString);

        Bundle options = ScaleUpAnimator.newInstance().saveAnimation(mNavigationView);
        ActivityCompat.startActivity(MenuActivity.this, addHoursIntent, options);
    }
}
