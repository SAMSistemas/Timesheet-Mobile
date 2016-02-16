package com.samsistemas.timesheet.view.addhours.activity;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.IntentCompat;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.samsistemas.timesheet.R;
import com.samsistemas.timesheet.common.activity.MenuActivity;
import com.samsistemas.timesheet.common.animation.ScaleUpAnimator;
import com.samsistemas.timesheet.common.utility.DeveloperUtility;
import com.samsistemas.timesheet.view.addhours.adapter.ClientAdapter;
import com.samsistemas.timesheet.view.addhours.adapter.ProjectAdapter;
import com.samsistemas.timesheet.view.addhours.adapter.TaskTypeAdapter;
import com.samsistemas.timesheet.view.addhours.view.AddHoursView;
import com.samsistemas.timesheet.domain.Client;
import com.samsistemas.timesheet.domain.Project;
import com.samsistemas.timesheet.domain.TaskType;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author jonatan.salas
 */
public class AddHoursActivity extends AppCompatActivity implements AddHoursView {

    @Bind(R.id.toolbar_layout) CollapsingToolbarLayout toolbarLayout;
    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.hours_spinner) Spinner hourSpinner;
    @Bind(R.id.task_spinner) Spinner taskTypeSpinner;
    @Bind(R.id.client_spinner) Spinner clientSpinner;
    @Bind(R.id.project_spinner) Spinner projectSpinner;
    @Bind(R.id.fab) FloatingActionButton saveButton;
    @Bind(R.id.description) EditText description;
    @Bind(R.id.solicitude) EditText solicitudeNumber;

    private TaskTypeAdapter taskTypeAdapter;
    private ClientAdapter clientAdapter;
    private ProjectAdapter projectAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hours);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        DeveloperUtility.enableStrictModeApi(true);

        final Intent intent = getIntent();

//        if (null != intent) {
//            String dateString = intent.getStringExtra(DATE_KEY);
//        }

        final ActionBar actionBar = getSupportActionBar();

        if (null != actionBar) {
            actionBar.setTitle(getString(R.string.action_add_hour));
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setDisplayUseLogoEnabled(false);
        }

        toolbarLayout.setTitleEnabled(false);

        taskTypeSpinner.getBackground().setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.accent), PorterDuff.Mode.SRC_ATOP);

        final ArrayAdapter<CharSequence> hourAdapter = ArrayAdapter.createFromResource(getApplication(), R.array.hours, android.R.layout.simple_spinner_dropdown_item);
        hourAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        hourSpinner.getBackground().setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.accent), PorterDuff.Mode.SRC_ATOP);
        hourSpinner.setAdapter(hourAdapter);

        clientSpinner.getBackground().setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.accent), PorterDuff.Mode.SRC_ATOP);
        projectSpinner.getBackground().setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.accent), PorterDuff.Mode.SRC_ATOP);

        hourSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Snackbar.make(parent, "You need to select some hours", Snackbar.LENGTH_SHORT).show();
            }
        });
        taskTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Snackbar.make(parent, "You need to select some TaskType", Snackbar.LENGTH_SHORT).show();
            }
        });
        clientSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                mClientSelected = (Client) parent.getAdapter().getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Snackbar.make(parent, "You need to select some Client", Snackbar.LENGTH_SHORT).show();
            }
        });
        projectSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Snackbar.make(parent, "You need to select some Project", Snackbar.LENGTH_SHORT).show();
            }
        });
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                onBackPressed();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        navigateToMenu();
    }

    @Override
    public void navigateToMenu() {
        final Intent intent = new Intent(getApplicationContext(), MenuActivity.class);

        intent.setFlags(
                Intent.FLAG_ACTIVITY_NEW_TASK |
                IntentCompat.FLAG_ACTIVITY_CLEAR_TASK |
                Intent.FLAG_ACTIVITY_CLEAR_TOP
        );

        Bundle options = ScaleUpAnimator.newInstance().saveAnimation(saveButton);
        ActivityCompat.startActivity(this, intent, options);
    }

    @Override
    public void loadTaskTypeData(@Nullable List<TaskType> taskTypeList) {
        if (null == taskTypeAdapter) {
            taskTypeAdapter = new TaskTypeAdapter(getApplicationContext(), taskTypeList);
        } else {
            if (null != taskTypeList) {
                taskTypeAdapter.setList(null);
                taskTypeAdapter.setList(taskTypeList);
            } else {
                Snackbar.make(taskTypeSpinner, "Error loading task types data. ", Snackbar.LENGTH_SHORT).show();
            }
        }

        taskTypeSpinner.setAdapter(clientAdapter);
        taskTypeAdapter.notifyDataSetChanged();
    }

    @Override
    public void loadClientsData(@Nullable List<Client> clientList) {
        if (null == clientAdapter) {
            clientAdapter = new ClientAdapter(getApplicationContext(), clientList);
        } else {
            if (null != clientList) {
                clientAdapter.setList(null);
                clientAdapter.setList(clientList);
            } else {
                Snackbar.make(clientSpinner, "Error loading clients data. ", Snackbar.LENGTH_SHORT).show();
            }
        }

        clientSpinner.setAdapter(clientAdapter);
        clientAdapter.notifyDataSetChanged();
    }

    @Override
    public void loadProjectsData(@Nullable List<Project> projectList) {
        if (null == projectAdapter) {
            projectAdapter = new ProjectAdapter(getApplicationContext(), projectList);
        } else {
            if (null != projectList) {
                projectAdapter.setList(null);
                projectAdapter.setList(projectList);
            } else {
                Snackbar.make(clientSpinner, "Error loading projects data. ", Snackbar.LENGTH_SHORT).show();
            }
        }

        projectSpinner.setAdapter(projectAdapter);
        projectAdapter.notifyDataSetChanged();
    }
}
