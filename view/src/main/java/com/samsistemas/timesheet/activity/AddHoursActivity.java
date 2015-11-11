package com.samsistemas.timesheet.activity;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Spinner;

import com.samsistemas.timesheet.R;
import com.samsistemas.timesheet.adapter.ClientSpinnerAdapter;
import com.samsistemas.timesheet.adapter.HourSpinnerAdapter;
import com.samsistemas.timesheet.adapter.ProjectSpinnerAdapter;
import com.samsistemas.timesheet.adapter.TaskSpinnerAdapter;
import com.samsistemas.timesheet.navigation.MenuNavigator;
import com.samsistemas.timesheet.util.ToolbarUtil;
import com.samsistemas.timesheet.viewmodel.ClientViewModel;
import com.samsistemas.timesheet.viewmodel.ProjectViewModel;
import com.samsistemas.timesheet.viewmodel.TaskTypeViewModel;

import java.util.ArrayList;

/**
 *
 * @author jonatan.salas
 */
public class AddHoursActivity extends AppCompatActivity {
    Spinner mTaskSpinner;
    Spinner mClientSpinner;
    Spinner mHourSpinner;
    Spinner mProjectSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hours);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ActionBar actionBar = getSupportActionBar();
        if(null != actionBar)
            ToolbarUtil.styleWithBackButton(actionBar, getString(R.string.action_add_hour));

        CollapsingToolbarLayout toolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolbarLayout.setTitleEnabled(false);

        final TaskSpinnerAdapter taskAdapter = new TaskSpinnerAdapter(getApplicationContext(), new ArrayList<TaskTypeViewModel>());

        mTaskSpinner = (Spinner) findViewById(R.id.task_spinner);
        mTaskSpinner.getBackground().setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.accent), PorterDuff.Mode.SRC_ATOP);
        mTaskSpinner.setAdapter(taskAdapter);

        final ClientSpinnerAdapter clientAdapter = new ClientSpinnerAdapter(getApplicationContext(), new ArrayList<ClientViewModel>());

        mClientSpinner = (Spinner) findViewById(R.id.client_spinner);
        mClientSpinner.getBackground().setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.accent), PorterDuff.Mode.SRC_ATOP);
        mClientSpinner.setAdapter(clientAdapter);

        final ProjectSpinnerAdapter projectAdapter = new ProjectSpinnerAdapter(getApplicationContext(), new ArrayList<ProjectViewModel>());

        mProjectSpinner = (Spinner) findViewById(R.id.project_spinner);
        mProjectSpinner.getBackground().setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.accent), PorterDuff.Mode.SRC_ATOP);
        mProjectSpinner.setAdapter(projectAdapter);

        final HourSpinnerAdapter hourAdapter = new HourSpinnerAdapter(getApplicationContext(), new ArrayList<Integer>());

        mHourSpinner = (Spinner) findViewById(R.id.hours_spinner);
        mHourSpinner.getBackground().setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.accent), PorterDuff.Mode.SRC_ATOP);
        mHourSpinner.setAdapter(hourAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO JS: perform joblog Save
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_accounts, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.action_mode_edit:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        MenuNavigator.newInstance().navigate(this);
    }
}
