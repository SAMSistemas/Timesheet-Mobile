package com.samsistemas.timesheet.screen.addhours.fragment;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.ArrayRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.jonisaa.commons.fragment.CallbackFragment;
import com.jonisaa.commons.utility.DeveloperUtility;

import com.samsistemas.timesheet.R;
import com.samsistemas.timesheet.domain.Client;
import com.samsistemas.timesheet.domain.Project;
import com.samsistemas.timesheet.domain.TaskType;
import com.samsistemas.timesheet.screen.addhours.adapter.ClientAdapter;
import com.samsistemas.timesheet.screen.addhours.adapter.ProjectAdapter;
import com.samsistemas.timesheet.screen.addhours.adapter.TaskTypeAdapter;
import com.samsistemas.timesheet.screen.addhours.presenter.AddHoursPresenter;
import com.samsistemas.timesheet.screen.addhours.view.AddHoursView;

import java.util.List;

import butterknife.Bind;

/**
 * @author jonatan.salas
 */
public class AddHoursFragment extends CallbackFragment<AddHoursPresenter> implements AddHoursView,
        AdapterView.OnItemSelectedListener, View.OnClickListener {

    private ArrayAdapter<CharSequence> hoursAdapter;
    private TaskTypeAdapter taskTypeAdapter;
    private ProjectAdapter projectAdapter;
    private ClientAdapter clientAdapter;

    @Bind(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.hours_spinner)
    Spinner hourSpinner;

    @Bind(R.id.task_spinner)
    Spinner taskTypeSpinner;

    @Bind(R.id.client_spinner)
    Spinner clientSpinner;

    @Bind(R.id.project_spinner)
    Spinner projectSpinner;

    @Bind(R.id.fab)
    FloatingActionButton saveButton;

    @Bind(R.id.description)
    EditText description;

    @Bind(R.id.solicitude)
    EditText solicitudeNumber;

    @Override
    public int getLayout() {
        return R.layout.fragment_add_hours;
    }

    @Nullable
    @Override
    public View onViewCreated(@Nullable View view) {
        final int accentColor = ContextCompat.getColor(getContext(), R.color.accent);

        toolbarLayout.setTitleEnabled(false);

        taskTypeSpinner.getBackground().setColorFilter(accentColor, PorterDuff.Mode.SRC_ATOP);
        hourSpinner.getBackground().setColorFilter(accentColor, PorterDuff.Mode.SRC_ATOP);
        clientSpinner.getBackground().setColorFilter(accentColor, PorterDuff.Mode.SRC_ATOP);
        projectSpinner.getBackground().setColorFilter(accentColor, PorterDuff.Mode.SRC_ATOP);

        hourSpinner.setOnItemSelectedListener(this);
        taskTypeSpinner.setOnItemSelectedListener(this);
        clientSpinner.setOnItemSelectedListener(this);
        projectSpinner.setOnItemSelectedListener(this);

        saveButton.setOnClickListener(this);

        return view;
    }

    @NonNull
    @Override
    public AddHoursPresenter createPresenter() {
        return AddHoursPresenter.getInstance(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        DeveloperUtility.enableStrictModeApi(true);

        getPresenter().showStyledActionBar();
        getPresenter().showHoursInSpinner();
        getPresenter().showTaskTypesInSpinner();
        getPresenter().showClientsInSpinner();
        getPresenter().showProjectsInSpinner();
    }

    @Override
    public void styleBar() {
        final AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        activity.setTheme(R.style.AppTheme_NoActionBar);
        final ActionBar actionBar = activity.getSupportActionBar();

        if (null != actionBar) {
            actionBar.setTitle(getString(R.string.action_add_hour));
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        if (null != toolbar) {
            getToolbarCallback().synchronize(toolbar);
        }
    }

    @Override
    public void loadHours(@ArrayRes int hoursArrayId, @LayoutRes int layoutItemId) {
        if (null == hoursAdapter) {
            hoursAdapter = ArrayAdapter.createFromResource(getContext(), hoursArrayId, layoutItemId);
            hoursAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        } else {
            Snackbar.make(hourSpinner, "Error when trying to create hours adapter!", Snackbar.LENGTH_SHORT).show();
        }

        hourSpinner.setAdapter(hoursAdapter);
        hoursAdapter.notifyDataSetChanged();
    }

    @Override
    public void loadTaskTypes(@Nullable List<TaskType> taskTypeList) {
        if (null == taskTypeAdapter && null != taskTypeList) {
            taskTypeAdapter = new TaskTypeAdapter(getContext(), null);
            taskTypeAdapter.setList(taskTypeList);
        } else {
            if (null != taskTypeList) {
                taskTypeAdapter.setList(null);
                taskTypeAdapter.setList(taskTypeList);
            } else {
                Snackbar.make(taskTypeSpinner, "Error loading task types data. ", Snackbar.LENGTH_SHORT).show();
            }
        }

        taskTypeSpinner.setAdapter(taskTypeAdapter);
        taskTypeAdapter.notifyDataSetChanged();
    }

    @Override
    public void loadClients(@Nullable List<Client> clientList) {
        if (null == clientAdapter && null != clientList) {
            clientAdapter = new ClientAdapter(getContext(), clientList);
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
    public void loadProjects(@Nullable List<Project> projectList) {
        if (null == projectAdapter && null != projectList) {
            projectAdapter = new ProjectAdapter(getContext(), projectList);
        } else {
            if (null != projectList) {
                projectAdapter.setList(null);
                projectAdapter.setList(projectList);
            } else {
                Snackbar.make(projectSpinner, "Error loading projects data. ", Snackbar.LENGTH_SHORT).show();
            }
        }

        projectSpinner.setAdapter(projectAdapter);
        projectAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //Nothing to do here.
    }

    @Override
    public void onClick(View v) {
        //boolean result = getPresenter().saveOrUpdateAsync(true, new JobLog());
    }
}
