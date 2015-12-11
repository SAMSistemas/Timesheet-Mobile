package com.samsistemas.timesheet.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
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
import static com.samsistemas.timesheet.util.SharedPreferenceKeys.*;
import com.samsistemas.timesheet.facade.ClientFacade;
import com.samsistemas.timesheet.facade.JobLogFacade;
import com.samsistemas.timesheet.facade.ProjectFacade;
import com.samsistemas.timesheet.facade.TaskTypeFacade;
import com.samsistemas.timesheet.model.Client;
import com.samsistemas.timesheet.model.JobLog;
import com.samsistemas.timesheet.model.Person;
import com.samsistemas.timesheet.model.Project;
import com.samsistemas.timesheet.model.TaskType;
import com.samsistemas.timesheet.navigation.MenuNavigator;
import com.samsistemas.timesheet.util.ToolbarUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author jonatan.salas
 */
public class AddHoursActivity extends AppCompatActivity {
    private Spinner mTaskSpinner;
    private Spinner mClientSpinner;
    private Spinner mProjectSpinner;

    private final Person person = new Person();
    private final Project project = new Project();
    private final TaskType taskType = new TaskType();
    private final Client client = new Client();
    private final JobLog jobLogToSave = new JobLog();

    private CharSequence hours;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Use this to check troubles
        //DevUtil.enableStrictModeChecker();
        setContentView(R.layout.activity_add_hours);
        setToolbar();
        setTaskSpinner();
        setHourSpinner();
        setProjectSpinner();
        setClientSpinner();
        fetchData();
        saveJobLog();
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
        MenuNavigator.newInstance().navigate(this);
    }

    private void fetchData() {
        new FetchTaskTypeAdapterTask().execute();
        new FetchClientAdapterTask().execute();
        new FetchProjectAdapterTask().execute();
    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ActionBar actionBar = getSupportActionBar();
        if(null != actionBar)
            ToolbarUtil.styleWithBackButton(actionBar, getString(R.string.action_add_hour));

        CollapsingToolbarLayout toolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolbarLayout.setTitleEnabled(false);
    }

    private void setTaskSpinner() {
        mTaskSpinner = (Spinner) findViewById(R.id.task_spinner);
        mTaskSpinner.getBackground().setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.accent), PorterDuff.Mode.SRC_ATOP);
        mTaskSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String name = (String) parent.getAdapter().getItem(position);
                taskType.setName(name);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setHourSpinner() {
        ArrayAdapter<CharSequence> hoursArrayAdapter = ArrayAdapter.createFromResource(getApplication(), R.array.hours, android.R.layout.simple_spinner_dropdown_item);
        hoursArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner hourSpinner = (Spinner) findViewById(R.id.hours_spinner);
        hourSpinner.getBackground().setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.accent), PorterDuff.Mode.SRC_ATOP);
        hourSpinner.setAdapter(hoursArrayAdapter);
        hourSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                hours = (CharSequence) parent.getAdapter().getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setClientSpinner() {
        mClientSpinner = (Spinner) findViewById(R.id.client_spinner);
        mClientSpinner.getBackground().setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.accent), PorterDuff.Mode.SRC_ATOP);
        mClientSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String name = (String) parent.getAdapter().getItem(position);
                client.setName(name);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setProjectSpinner() {
        mProjectSpinner = (Spinner) findViewById(R.id.project_spinner);
        mProjectSpinner.getBackground().setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.accent), PorterDuff.Mode.SRC_ATOP);
        mProjectSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String name = (String) parent.getAdapter().getItem(position);
                project.setName(name);
                project.setClient(client);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void saveJobLog() {
        final EditText descriptionEditText = (EditText) findViewById(R.id.description);
        final EditText solicitudeNumberEditText = (EditText) findViewById(R.id.solicitude);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final SharedPreferences prefs = getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
                final String username = prefs.getString(USERNAME, "");
                final String password = prefs.getString(PASSWORD, "");

                String description = descriptionEditText.getText().toString().trim();
                int solicitudeNumber = Integer.valueOf(solicitudeNumberEditText.getText().toString().trim());

                jobLogToSave.setHours(hours.toString())
                        .setObservations(description)
                        .setSolicitude(solicitudeNumber)
                        .setWorkDate(new Date(System.currentTimeMillis()))
                        .setPerson(person.setUsername(username)
                                         .setPassword(password))
                        .setProject(project)
                        .setTaskType(taskType);

                new SaveJobLogOnServerTask(getApplicationContext()).execute(jobLogToSave);
            }
        });
    }

    private class FetchClientAdapterTask extends AsyncTask<Void, Void, List<String>> {
        @Override
        protected List<String> doInBackground(Void... params) {
            final List<Client> clients = ClientFacade.newInstance().findAll(getApplicationContext());
            final List<String> stringList = new ArrayList<>(clients.size());

            for(int i = 0; i < clients.size(); i++) {
                stringList.add(i, clients.get(i).getName());
            }

            return stringList;
        }

        @Override
        protected void onPostExecute(List<String> clients) {
            ArrayAdapter<String> clientArrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, clients);
            clientArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            mClientSpinner.setAdapter(clientArrayAdapter);
            clientArrayAdapter.notifyDataSetChanged();
        }
    }

    private class FetchTaskTypeAdapterTask extends AsyncTask<Void, Void, List<String>> {
        @Override
        protected List<String> doInBackground(Void... params) {
            final List<TaskType> taskTypes = TaskTypeFacade.newInstance().findAll(getApplicationContext());
            final List<String> stringList = new ArrayList<>(taskTypes.size());

            for(int i = 0; i < taskTypes.size(); i++) {
                stringList.add(i, taskTypes.get(i).getName());
            }

            return stringList;
        }

        @Override
        protected void onPostExecute(List<String> taskTypes) {
            ArrayAdapter<String> taskArrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, taskTypes);
            taskArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            mTaskSpinner.setAdapter(taskArrayAdapter);
            taskArrayAdapter.notifyDataSetChanged();
        }
    }

    private class FetchProjectAdapterTask extends AsyncTask<Void, Void, List<String>> {

        @Override
        protected List<String> doInBackground(Void... params) {
            final List<Project> projects = ProjectFacade.newInstance().findAll(getApplicationContext());
            final List<String> stringList = new ArrayList<>(projects.size());

            for(int i = 0; i < projects.size(); i++) {
                stringList.add(i, projects.get(i).getName());
            }

            return stringList;
        }

        @Override
        protected void onPostExecute(List<String> projects) {
            ArrayAdapter<String> projectArrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, projects);
            projectArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            mProjectSpinner.setAdapter(projectArrayAdapter);
            projectArrayAdapter.notifyDataSetChanged();
        }
    }

    public class SaveJobLogOnServerTask extends AsyncTask<JobLog, Void, Boolean> {
        private final Context mContext;

        public SaveJobLogOnServerTask(Context context) {
            this.mContext = context;
        }

        @Override
        protected Boolean doInBackground(JobLog... params) {
            return JobLogFacade.newInstance().insert(mContext, params[0]);
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if(result)
                Snackbar.make(mTaskSpinner, "Good luck, joblog saved!!", Snackbar.LENGTH_SHORT).show();
            else
                Snackbar.make(mTaskSpinner, "Bad luck, joblog not saved!!", Snackbar.LENGTH_SHORT).show();
        }
    }
}
