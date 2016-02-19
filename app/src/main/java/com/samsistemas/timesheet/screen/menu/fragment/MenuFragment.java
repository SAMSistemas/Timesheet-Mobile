package com.samsistemas.timesheet.screen.menu.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.samsistemas.calendarview.util.CalendarUtil;
import com.samsistemas.calendarview.widget.CalendarView;
import com.samsistemas.timesheet.R;
import com.samsistemas.timesheet.common.fragment.BaseFragment;
import com.samsistemas.timesheet.domain.JobLog;
import com.samsistemas.timesheet.screen.menu.adapter.JobLogAdapter;
import com.samsistemas.timesheet.utility.DateUtility;
import com.samsistemas.timesheet.utility.SimpleTouchItemHelperCallback;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author jonatan.salas
 */
public class MenuFragment extends BaseFragment {
    private JobLogAdapter mAdapter;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.toolbar_layout)
    CollapsingToolbarLayout mCollapsingToolbarLayout;

    @Bind(R.id.fab)
    FloatingActionButton mFab;

    @Bind(R.id.date)
    TextView mDateTitle;

    @Bind(R.id.calendar_view)
    CalendarView mCalendarView;

    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_menu, container, false);
        ButterKnife.bind(this, view);

        //Style CollapsingToolbarLayout
        mCollapsingToolbarLayout.setTitleEnabled(false);

        //Style CalendarView
        mCalendarView.setFirstDayOfWeek(Calendar.MONDAY);
        mCalendarView.setNextButtonColor(R.color.accent);
        mCalendarView.setBackButtonColor(R.color.accent);
        mCalendarView.setCurrentDay(new Date(System.currentTimeMillis()));
        mCalendarView.setIsOverflowDateVisible(true);
        mCalendarView.refreshCalendar(Calendar.getInstance(Locale.getDefault()));

        //Style RecyclerView
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setNestedScrollingEnabled(true);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new JobLogAdapter(getActivity(), new ArrayList<JobLog>());
        mRecyclerView.setAdapter(mAdapter);
        final ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SimpleTouchItemHelperCallback(mAdapter));
        itemTouchHelper.attachToRecyclerView(mRecyclerView);
        mAdapter.notifyDataSetChanged();

        Date date = new Date(System.currentTimeMillis());

        mDateTitle.setText(DateUtility.formatDate(getContext(), date));

        mCalendarView.setOnDateClickListener(new CalendarView.OnDateClickListener() {

            @Override
            public void onDateClick(@NonNull Date date) {
                mDateTitle.setText(DateUtility.formatDate(getContext(), date));
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
                    mDateTitle.setText(DateUtility.formatDate(getContext(), new Date(System.currentTimeMillis())));
                } else {
                    //We want this to display an announce, telling the user that has not any date selected..
                    mDateTitle.setText(getString(R.string.no_date_selected));
                }
            }
        });
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != getNestedFragmentCallback()) {
                    getNestedFragmentCallback().placeNestedFragment();
                }
            }
        });


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
        appCompatActivity.setSupportActionBar(mToolbar);

        final ActionBar actionBar = appCompatActivity.getSupportActionBar();

        if (null != actionBar) {
            actionBar.setTitle(R.string.action_view_calendar);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        if (null != getToolbarCallback()) {
            getToolbarCallback().synchronize(mToolbar);
        }
    }

    @Override
    public void onDestroyView() {
        ButterKnife.unbind(this);
        super.onDestroyView();
    }
}
