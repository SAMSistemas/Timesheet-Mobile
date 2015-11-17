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
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.samsistemas.timesheet.R;
import com.samsistemas.timesheet.facade.ClientFacade;
import com.samsistemas.timesheet.facade.ProjectFacade;
import com.samsistemas.timesheet.facade.TaskTypeFacade;
import com.samsistemas.timesheet.model.Client;
import com.samsistemas.timesheet.model.Project;
import com.samsistemas.timesheet.model.TaskType;
import com.samsistemas.timesheet.navigation.MenuNavigator;
import com.samsistemas.timesheet.util.ToolbarUtil;

import java.util.ArrayList;
import java.util.List;

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

        ArrayAdapter<String> taskArrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, getTaskTypes());
        mTaskSpinner = (Spinner) findViewById(R.id.task_spinner);
        mTaskSpinner.getBackground().setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.accent), PorterDuff.Mode.SRC_ATOP);
        mTaskSpinner.setAdapter(taskArrayAdapter);

        ArrayAdapter<String> clientArrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, getClients());
        mClientSpinner = (Spinner) findViewById(R.id.client_spinner);
        mClientSpinner.getBackground().setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.accent), PorterDuff.Mode.SRC_ATOP);
        mClientSpinner.setAdapter(clientArrayAdapter);

        ArrayAdapter<String> projectArrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, getProjects());
        mProjectSpinner = (Spinner) findViewById(R.id.project_spinner);
        mProjectSpinner.getBackground().setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.accent), PorterDuff.Mode.SRC_ATOP);
        mProjectSpinner.setAdapter(projectArrayAdapter);

        ArrayAdapter<String> hoursArrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, getHours());
        mHourSpinner = (Spinner) findViewById(R.id.hours_spinner);
        mHourSpinner.getBackground().setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.accent), PorterDuff.Mode.SRC_ATOP);
        mHourSpinner.setAdapter(hoursArrayAdapter);

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

    protected List<String> getTaskTypes() {
        final List<TaskType> taskTypes = TaskTypeFacade.newInstance().findAll(getApplicationContext());
        final List<String> stringList = new ArrayList<>(taskTypes.size());

        for(int i = 0; i < taskTypes.size(); i++) {
            stringList.add(i, taskTypes.get(i).getName());
        }

        return stringList;
    }

    protected List<String> getClients() {
        final List<Client> clients = ClientFacade.newInstance().findAll(getApplicationContext());
        final List<String> stringList = new ArrayList<>(clients.size());

        for(int i = 0; i < clients.size(); i++) {
            stringList.add(i, clients.get(i).getName());
        }

        return stringList;
    }

    protected List<String> getProjects() {
        final List<Project> projects = ProjectFacade.newInstance().findAll(getApplicationContext());
        final List<String> stringList = new ArrayList<>(projects.size());

        for(int i = 0; i < projects.size(); i++) {
            stringList.add(i, projects.get(i).getName());
        }

        return stringList;
    }

    protected List<String> getHours() {
        final List<String> stringList = new ArrayList<>();

        stringList.add("0.5");
        stringList.add("1.0");
        stringList.add("1.5");
        stringList.add("2.0");
        stringList.add("2.5");
        stringList.add("3.0");
        stringList.add("3.5");
        stringList.add("4.0");
        stringList.add("4.5");
        stringList.add("5.0");
        stringList.add("5.5");
        stringList.add("6.0");
        stringList.add("6.5");
        stringList.add("7.0");
        stringList.add("7.5");
        stringList.add("8.0");
        stringList.add("8.5");
        stringList.add("9.0");

        return stringList;

    }
}
