package com.samsistemas.timesheet.facade;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import static com.samsistemas.timesheet.util.JSONObjectKeys.*;
import com.samsistemas.timesheet.controller.Controller;
import com.samsistemas.timesheet.entity.JobLogEntity;
import com.samsistemas.timesheet.facade.base.Facade;
import com.samsistemas.timesheet.factory.ControllerFactory;
import com.samsistemas.timesheet.helper.URLHelper;
import com.samsistemas.timesheet.helper.UriHelper;
import com.samsistemas.timesheet.model.JobLog;
import com.samsistemas.timesheet.model.Person;
import com.samsistemas.timesheet.model.Project;
import com.samsistemas.timesheet.model.TaskType;
import com.samsistemas.timesheet.network.converter.JobLogEntityParser;
import com.samsistemas.timesheet.util.AuthUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author jonatan.salas
 */
public final class JobLogFacade implements Facade<JobLog> {
    private static final String TAG = JobLogFacade.class.getSimpleName();
    private static final String DATE_TEMPLATE = "dd-MM-yyyy";

    private static JobLogFacade instance = null;

    private final Controller<JobLogEntity> jobLogController;
    private final Facade<Project> projectFacade;
    private final Facade<Person> personFacade;
    private final Facade<TaskType> taskTypeFacade;

    private boolean result = false;

    private JobLogFacade() {
        this.jobLogController = ControllerFactory.getJobLogController();
        this.projectFacade = ProjectFacade.newInstance();
        this.personFacade = PersonFacade.newInstance();
        this.taskTypeFacade = TaskTypeFacade.newInstance();
    }

    @Override
    public JobLog findById(@NonNull Context context, long id) {
        final Uri uri = UriHelper.buildJobLogUriWithId(context, id);
        final JobLogEntity entity = jobLogController.get(context, uri);
        final Project project = projectFacade.findById(context, entity.getProjectId());
        final Person person = personFacade.findById(context, entity.getPersonId());
        final TaskType taskType = taskTypeFacade.findById(context, entity.getTaskTypeId());

        final JobLog jobLog = new JobLog();

        jobLog.setId(entity.getId())
              .setProject(project)
              .setPerson(person)
              .setTaskType(taskType)
              .setSolicitude(entity.getSolicitude())
              .setHours(entity.getHours())
              .setObservations(entity.getObservations())
              .setWorkDate(entity.getWorkDate());

        return jobLog;
    }

    @Override
    public List<JobLog> findAll(@NonNull Context context) {
        final Uri uri = UriHelper.buildJobLogUri(context);
        final List<JobLogEntity> jobLogEntities = jobLogController.listAll(context, uri);

        if (null != jobLogEntities) {
            final List<JobLog> jobLogs = new ArrayList<>(jobLogEntities.size());
            JobLogEntity entity;
            Project project;
            Person person;
            TaskType taskType;

            for (int i = 0; i < jobLogEntities.size(); i++) {
                entity = jobLogEntities.get(i);
                project = projectFacade.findById(context, entity.getProjectId());
                person = personFacade.findById(context, entity.getPersonId());
                taskType = taskTypeFacade.findById(context, entity.getTaskTypeId());

                JobLog jobLog = new JobLog();
                jobLog.setId(entity.getId())
                      .setProject(project)
                      .setPerson(person)
                      .setTaskType(taskType)
                      .setSolicitude(entity.getSolicitude())
                      .setHours(entity.getHours())
                      .setObservations(entity.getObservations())
                      .setWorkDate(entity.getWorkDate());

                jobLogs.add(jobLog);
            }

            return jobLogs;
        }

        return null;
    }

    @Override
    public boolean insert(@NonNull final Context context, final JobLog jobLog) {
        String dateString = "";

        try {
            dateString = new SimpleDateFormat(DATE_TEMPLATE, Locale.getDefault()).format(jobLog.getWorkDate());
        } catch (Exception ex) {
            Log.e(TAG, ex.getMessage(), ex.getCause());
        }

        JSONObject json = new JSONObject();

        try {
            json.put(DATE, dateString)
                .put(HOURS, jobLog.getHours())
                .put(SOLICITUDE, String.valueOf(jobLog.getSolicitude()))
                .put(OBSERVATION, jobLog.getObservations())
                .put(PROJECT_NAME, jobLog.getProject().getName())
                .put(USERNAME, jobLog.getPerson().getUsername())
                .put(TASK_TYPE_NAME, jobLog.getTaskType().getName());

        } catch (JSONException ex) {
            Log.e(TAG, ex.getMessage(), ex.getCause());
        }

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonRequest = new JsonObjectRequest(
                Request.Method.POST,
                URLHelper.buildCreateJobLogUrl(context),
                json,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            final Controller<JobLogEntity> jobLogController = ControllerFactory.getJobLogController();

                            final JobLogEntityParser jobLogEntityParser = JobLogEntityParser.newInstance();
                            final JobLogEntity jobLogEntity= jobLogEntityParser.convert(response);
                            final Uri uri = UriHelper.buildJobLogUri(context);

                            result = jobLogController.insert(context.getApplicationContext(), jobLogEntity, uri);
                        } catch (JSONException ex) {
                            Log.e(TAG, ex.getMessage(), ex.getCause());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, error.getMessage(), error.getCause());
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return AuthUtil.getAuthHeaders(new String[] {
                        jobLog.getPerson().getUsername(),
                        jobLog.getPerson().getPassword()
                });
            }
        };

        requestQueue.add(jsonRequest);

        return result;
    }

    @Override
    public boolean update(@NonNull Context context, JobLog jobLog) {
        final Uri uri = UriHelper.buildJobLogUri(context);
        final JobLogEntity entity = new JobLogEntity();

        entity.setId(jobLog.getId());
        entity.setProjectId(jobLog.getProject().getId())
              .setPersonId(jobLog.getPerson().getId())
              .setTaskTypeId(jobLog.getTaskType().getId())
              .setSolicitude(jobLog.getSolicitude())
              .setHours(jobLog.getHours())
              .setObservations(jobLog.getObservations())
              .setWorkDate(jobLog.getWorkDate());

        return jobLogController.update(context, entity, uri);
    }

    @Override
    public boolean deleteById(@NonNull Context context, long id) {
        final Uri uri = UriHelper.buildJobLogUri(context);
        return jobLogController.delete(context, uri, id);
    }

    /**
     * Method that gets a singleton instance.
     *
     * @return a singleton object.
     */
    public static JobLogFacade newInstance() {
        if (null == instance) {
            instance = new JobLogFacade();
        }
        return instance;
    }
}
