package com.samsistemas.timesheet.common.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;

import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.IntentCompat;
import android.support.v4.content.Loader;
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
import com.samsistemas.timesheet.common.loader.PersonLoader;
import com.samsistemas.timesheet.common.model.JobLog;
import com.samsistemas.timesheet.common.model.Person;
import com.samsistemas.timesheet.common.navigation.AccountNavigator;
import com.samsistemas.timesheet.common.navigation.SettingsNavigator;
import com.samsistemas.timesheet.common.util.DateUtil;
import com.samsistemas.timesheet.common.util.SimpleTouchItemHelperCallback;

import static com.samsistemas.timesheet.common.util.LoaderId.PERSON_LOADER_ID;

import static com.samsistemas.timesheet.common.util.AppConstants.DATE_KEY;
import static com.samsistemas.timesheet.common.util.AppConstants.DATE_TEMPLATE;

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
        initPersonLoader();
//        Calendar calendar = Calendar.getInstance(Locale.getDefault());
//        String month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
//        String year = String.valueOf(calendar.get(Calendar.YEAR));

//        startFetchJobLogService(month, year);
//        initJobLogLoader(new Date(System.currentTimeMillis()));
    }

    @Override
    public void populateViews() {
        mDateTitle.setTypeface(getRobotoMediumTypeface());
        mDateTitle.setText(DateUtil.formatDate(getApplicationContext(), new Date(System.currentTimeMillis())));
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
                        AccountNavigator.newInstance().navigateWithAnimation(MenuActivity.this, mNavigationView);
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
                mDateTitle.setText(DateUtil.formatDate(getApplicationContext(), date));
                //We call this in order to show the data available for the date user-selected
//                initJobLogLoader(date);
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
                    mDateTitle.setText(DateUtil.formatDate(getApplicationContext(), new Date(System.currentTimeMillis())));
                } else {
//                    String month = String.valueOf(nextCalendar.get(Calendar.MONTH) + 1);
//                    String year = String.valueOf(nextCalendar.get(Calendar.YEAR));
//                    startFetchJobLogService(month, year);
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
//                    mCredentials.add(data.getUsername());
//                    mCredentials.add(data.getPassword());
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

//    private void initJobLogLoader(final Date date) {
//        getSupportLoaderManager().initLoader(JOBLOG_LOADER_ID, null, new LoaderManager.LoaderCallbacks<List<JobLog>>() {
//
//            @Override
//            public Loader<List<JobLog>> onCreateLoader(int id, Bundle args) {
//                if (id == JOBLOG_LOADER_ID) {
//                    return new JobLogsLoader(getApplicationContext());
//                }
//
//                return null;
//            }
//
//            @Override
//            public void onLoadFinished(Loader<List<JobLog>> loader, List<JobLog> data) {
//                if (null != data && !data.isEmpty()) {
//                    initializeFilteredList(data, date);
//                }
//            }
//
//            @Override
//            public void onLoaderReset(Loader<List<JobLog>> loader) {
//                if (!loader.isReset()) {
//                    loader.reset();
//                }
//            }
//        }).forceLoad();
//    }

//    private void initializeFilteredList(List<JobLog> data, Date date) {
//        if (null != data) {
//            List<JobLog> filteredList = new ArrayList<>();
//            Date selectedDate = DateUtil.getDateWithOutTime(date);
//
//            for (int i = 0; i < data.size(); i++) {
//                Date workDate = DateUtil.getDateWithOutTime(data.get(i).getWorkDate());
//
//                if (workDate.compareTo(selectedDate) == 0) {
//                    filteredList.add(data.get(i));
//                }
//            }
//
//            if(!filteredList.isEmpty()) {
//                mAdapter.setItems(null);
//                mAdapter.setItems(filteredList);
//                mAdapter.notifyDataSetChanged();
//            } else {
//                mAdapter.setItems(null);
//                mAdapter.notifyDataSetChanged();
//                Snackbar.make(mRecyclerView, "Ups, It seems you don't have any joblog for this date", Snackbar.LENGTH_SHORT).show();
//            }
//        } else {
//            mAdapter.setItems(null);
//            mAdapter.notifyDataSetChanged();
//            Snackbar.make(mRecyclerView, "Ups, It seems you don't have any joblog for this month", Snackbar.LENGTH_SHORT).show();
//        }
//    }

    private void startAddHoursActivity() {
        final Intent addHoursIntent = new Intent(getApplicationContext(), AddHoursActivity.class);
        addHoursIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        addHoursIntent.putExtra(DATE_KEY, mDateString);

        Bundle options = ScaleUpAnimator.newInstance().saveAnimation(mNavigationView);
        ActivityCompat.startActivity(MenuActivity.this, addHoursIntent, options);
    }

//    private void startFetchJobLogService(String month, String year) {
//        Intent intent = new Intent(Intent.ACTION_SYNC, null, this, FetchJobLogDataService.class);
//
//        if (!mCredentials.isEmpty()) {
//            intent.putExtra(URL, URLHelper.buildAllJobLogsUrl(getApplicationContext()))
//                    .putExtra(USERNAME, mCredentials.get(0))
//                    .putExtra(PASSWORD, mCredentials.get(1))
//                    .putExtra(MONTH, month)
//                    .putExtra(YEAR, year);
//
//            startService(intent);
//        } else {
//            //Snackbar.make(mRecyclerView, "Error loading credentials", Snackbar.LENGTH_SHORT).show();
//        }
//    }
}
