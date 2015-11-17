package com.samsistemas.timesheet.facade;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import com.samsistemas.timesheet.controller.base.BaseController;
import com.samsistemas.timesheet.entity.JobLogEntity;
import com.samsistemas.timesheet.facade.base.Facade;
import com.samsistemas.timesheet.factory.ControllerFactory;
import com.samsistemas.timesheet.model.JobLog;
import com.samsistemas.timesheet.model.Person;
import com.samsistemas.timesheet.model.Project;
import com.samsistemas.timesheet.model.TaskType;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @author jonatan.salas
 */
public class JobLogFacade implements Facade<JobLog> {
    protected static final String TAG = JobLogFacade.class.getSimpleName();
    protected static final String DATE_TEMPLATE = "dd-MM-yyyy";

    private static JobLogFacade instance = null;

    protected BaseController<JobLogEntity> jobLogController;
    protected Facade<Project> projectFacade;
    protected Facade<Person> personFacade;
    protected Facade<TaskType> taskTypeFacade;

    protected JobLogFacade() {
        this.jobLogController = ControllerFactory.getJobLogController();
        this.projectFacade = ProjectFacade.newInstance();
        this.personFacade = PersonFacade.newInstance();
        this.taskTypeFacade = TaskTypeFacade.newInstance();
    }

    @Override
    public JobLog findById(@NonNull Context context, long id) {
        final JobLogEntity entity = jobLogController.get(context, id);
        final Project project = projectFacade.findById(context, entity.getProjectId());
        final Person person = personFacade.findById(context, entity.getPersonId());
        final TaskType taskType = taskTypeFacade.findById(context, entity.getTaskTypeId());

        final JobLog jobLog = new JobLog();

        jobLog.setId(entity.getJobLogId())
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
        final List<JobLogEntity> jobLogEntities = jobLogController.listAll(context);
        final List<JobLog> jobLogs = new ArrayList<>(jobLogEntities.size());
        final JobLog jobLog = new JobLog();
        JobLogEntity entity;
        Project project;
        Person person;
        TaskType taskType;

        for(int i = 0; i < jobLogEntities.size(); i++) {
            entity = jobLogEntities.get(i);
            project = projectFacade.findById(context, entity.getProjectId());
            person = personFacade.findById(context, entity.getPersonId());
            taskType = taskTypeFacade.findById(context, entity.getTaskTypeId());

            jobLog.setId(entity.getJobLogId())
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

    @Override
    public boolean insert(@NonNull final Context context, JobLog jobLog) {
        final JobLogEntity entity = new JobLogEntity();
        final String baseUrl = context.getString(com.samsistemas.timesheet.data.R.string.base_url);
        final String jobLogCreateUrl = baseUrl + "/jobLog/create";

        String dateString = new SimpleDateFormat(DATE_TEMPLATE, Locale.getDefault()).format(jobLog.getWorkDate());

        JSONObject jobLogToSend = new JSONObject();

        try {

            jobLogToSend.put("date", dateString)
                        .put("hours", jobLog.getHours())
                        .put("solicitude", jobLog.getSolicitude())
                        .put("observation", jobLog.getObservations())
                        .put("project_name", jobLog.getProject().getName())
                        .put("username", jobLog.getPerson().getUsername())
                        .put("task_type_name", jobLog.getTaskType().getName());

        } catch (JSONException ex) {
            Log.e(TAG, ex.getMessage(), ex.getCause());
        }

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonRequest = new JsonObjectRequest(
                Request.Method.POST,
                jobLogCreateUrl,
                jobLogToSend,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            entity.setJobLogId(response.getLong(context.getString(com.samsistemas.timesheet.data.R.string.id)));
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
        );

        requestQueue.add(jsonRequest);

        entity.setProjectId(jobLog.getProject().getId())
              .setPersonId(jobLog.getPerson().getId())
              .setTaskTypeId(jobLog.getTaskType().getId())
              .setSolicitude(jobLog.getSolicitude())
              .setHours(jobLog.getHours())
              .setObservations(jobLog.getObservations())
              .setWorkDate(jobLog.getWorkDate());

        return jobLogController.insert(context, entity);
    }

    @Override
    public boolean update(@NonNull Context context, JobLog jobLog) {
        final JobLogEntity entity = new JobLogEntity();

        entity.setJobLogId(jobLog.getId())
                .setProjectId(jobLog.getProject().getId())
                .setPersonId(jobLog.getPerson().getId())
                .setTaskTypeId(jobLog.getTaskType().getId())
                .setSolicitude(jobLog.getSolicitude())
                .setHours(jobLog.getHours())
                .setObservations(jobLog.getObservations())
                .setWorkDate(jobLog.getWorkDate());

        return jobLogController.update(context, entity);
    }

    @Override
    public boolean deleteById(@NonNull Context context, long id) {
        return jobLogController.delete(context, id);
    }

    public static JobLogFacade newInstance() {
        if(null == instance)
            instance = new JobLogFacade();
        return instance;
    }
}
