package com.samsistemas.timesheet.facade;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import com.samsistemas.timesheet.controller.Controller;
import com.samsistemas.timesheet.entity.JobLogEntity;
import com.samsistemas.timesheet.facade.base.Facade;
import com.samsistemas.timesheet.facade.base.JFacade;
import com.samsistemas.timesheet.facade.base.OnDataFetchListener;
import com.samsistemas.timesheet.factory.ControllerFactory;
import com.samsistemas.timesheet.helper.URLHelper;
import com.samsistemas.timesheet.helper.UriHelper;
import com.samsistemas.timesheet.model.JobLog;
import com.samsistemas.timesheet.model.Person;
import com.samsistemas.timesheet.model.Project;
import com.samsistemas.timesheet.model.TaskType;
import com.samsistemas.timesheet.network.converter.JobLogEntityConverter;
import com.samsistemas.timesheet.util.AuthUtil;

import static com.samsistemas.timesheet.util.JSONObjectKeys.DATE;
import static com.samsistemas.timesheet.util.JSONObjectKeys.HOURS;
import static com.samsistemas.timesheet.util.JSONObjectKeys.OBSERVATION;
import static com.samsistemas.timesheet.util.JSONObjectKeys.PROJECT_NAME;
import static com.samsistemas.timesheet.util.JSONObjectKeys.SOLICITUDE;
import static com.samsistemas.timesheet.util.JSONObjectKeys.TASK_TYPE_NAME;
import static com.samsistemas.timesheet.util.JSONObjectKeys.USERNAME;

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
public final class JobLogFacade implements JFacade<JobLog> {
    private static final String TAG = JobLogFacade.class.getSimpleName();
    private static final String DATE_TEMPLATE = "dd-MM-yyyy";

    @Override
    public boolean insert(@NonNull Context context, JobLog object) {
        return false;
    }

    @Override
    public boolean update(@NonNull Context context, JobLog object) {
        return false;
    }

    private static JobLogFacade instance = null;

    private final Controller<JobLogEntity> jobLogController;
    private final Facade<Project> projectFacade;
    private final Facade<Person> personFacade;
    private final Facade<TaskType> taskTypeFacade;

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
    public void insert(@NonNull final Context context, final JobLog jobLog, final OnDataFetchListener<Boolean> listener) {
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
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                URLHelper.buildCreateJobLogUrl(context),
                json,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            final Controller<JobLogEntity> jobLogController = ControllerFactory.getJobLogController();

                            final JobLogEntityConverter jobLogConverter = JobLogEntityConverter.newInstance();
                            final JobLogEntity jobLogEntity = jobLogConverter.asObject(response);
                            final Uri uri = UriHelper.buildJobLogUri(context);

                            boolean result = jobLogController.insert(context.getApplicationContext(), jobLogEntity, uri);
                            listener.onSuccess(result);

                        } catch (JSONException ex) {
                            listener.onError(ex);
                            //Log.e(TAG, ex.getMessage(), ex.getCause());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        listener.onError(error);
                        //Log.e(TAG, error.getMessage(), error.getCause());
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

        request.setShouldCache(true);
        request.setRetryPolicy(new DefaultRetryPolicy());
        requestQueue.add(request);
    }

    @Override
    public void update(@NonNull final Context context, final JobLog jobLog, final OnDataFetchListener<Boolean> listener) {
        String dateString = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(jobLog.getWorkDate());
        JSONObject json = new JSONObject();

        try {
            json.put("id", jobLog.getId())
                .put("id_project", jobLog.getProject().getId())
                .put("id_person", jobLog.getPerson().getId())
                .put("id_tasktype", jobLog.getTaskType().getId())
                .put("hours", jobLog.getHours())
                .put("work_date", dateString)
                .put("solicitude_number", jobLog.getSolicitude())
                .put("observations", jobLog.getObservations());

        } catch (JSONException ex) {
            Log.e(TAG, ex.getMessage(), ex.getCause());
        }

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                URLHelper.buildUpdateJobLogUrl(context),
                json,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        JobLogEntityConverter converter = JobLogEntityConverter.newInstance();
                        try {
                            Uri uri = UriHelper.buildJobLogUri(context);
                            boolean result = jobLogController.insert(context, converter.asObject(response), uri);
                            listener.onSuccess(result);
                        } catch (JSONException ex) {
                            listener.onError(ex);
                            //Log.e(JobLogFacade.class.getSimpleName(), ex.getMessage(), ex.getCause());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        listener.onError(error);
                        //Log.e(JobLogFacade.class.getSimpleName(), error.getMessage(), error.getCause());
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return AuthUtil.getAuthHeaders(new String[] { jobLog.getPerson().getUsername(), jobLog.getPerson().getPassword() });
            }
        };

        request.setShouldCache(true);
        request.setRetryPolicy(new DefaultRetryPolicy());
        requestQueue.add(request);
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
