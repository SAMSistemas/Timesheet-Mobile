package com.samsistemas.timesheet.adapter;

import android.content.Context;
import android.support.annotation.NonNull;

import com.samsistemas.timesheet.controller.base.BaseController;
import com.samsistemas.timesheet.factory.ControllerFactory;
import com.samsistemas.timesheet.model.Client;
import com.samsistemas.timesheet.model.JobLog;
import com.samsistemas.timesheet.model.Person;
import com.samsistemas.timesheet.model.Project;
import com.samsistemas.timesheet.model.TaskForPosition;
import com.samsistemas.timesheet.model.TaskType;
import com.samsistemas.timesheet.model.WorkPosition;
import com.samsistemas.timesheet.viewmodel.ClientViewModel;
import com.samsistemas.timesheet.viewmodel.JobLogViewModel;
import com.samsistemas.timesheet.viewmodel.ProjectViewModel;
import com.samsistemas.timesheet.viewmodel.TaskTypeViewModel;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author jonatan.salas
 */
public class ModelAdapter {
    private static BaseController<Client> clientBaseController = ControllerFactory.getClientController();
    private static BaseController<WorkPosition> workPositionBaseController = ControllerFactory.getWorkPositionController();
    private static BaseController<Person> personBaseController = ControllerFactory.getPersonController();
    private static BaseController<TaskType> taskTypeBaseController = ControllerFactory.getTaskTypeController();
    private static BaseController<TaskForPosition> taskForPositionBaseController = ControllerFactory.getTaskForPositionController();
    private static BaseController<Project> projectBaseController = ControllerFactory.getProjectController();
    private static BaseController<JobLog> jobLogBaseController = ControllerFactory.getJobLogController();

    private WeakReference<Context> mContextReference;

    /**
     *
     * @param context
     */
    public ModelAdapter(@NonNull Context context) {
        this.mContextReference = new WeakReference<>(context);
    }

    /**
     *
     * @return
     */
    public List<ClientViewModel> getClients() {
        List<Client> clientList = clientBaseController.listAll(mContextReference.get());
        List<ClientViewModel> clientViewModels = new ArrayList<>(clientList.size());

        for(int i = 0; i < clientList.size(); i++) {
            clientViewModels.add(new ClientViewModel(clientList.get(i)));
        }

        return clientViewModels;
    }

    /**
     *
     * @return
     */
    public List<ProjectViewModel> getProjects() {
        List<Project> projectList = projectBaseController.listAll(mContextReference.get());
        List<ProjectViewModel> projectViewModels = new ArrayList<>(projectList.size());

        for(int i = 0; i < projectList.size(); i++) {
            projectViewModels.add(new ProjectViewModel(projectList.get(i)));
        }

        return projectViewModels;
    }

    /**
     *
     * @return
     */
    public List<TaskTypeViewModel> getTaskForWorKPosition() {
        final Person person = personBaseController.get(mContextReference.get(), 0);
        List<TaskForPosition> taskForPositions = taskForPositionBaseController.listAll(mContextReference.get());
        List<TaskType> taskTypes = taskTypeBaseController.listAll(mContextReference.get());
        List<TaskTypeViewModel> taskTypeViewModels = new ArrayList<>();

        List<Long> taskTypesIds = new ArrayList<>();

        for(int i = 0; i < taskForPositions.size(); i++) {
            if(person.getWorkPositionId() == taskForPositions.get(i).getWorkPositionId()) {
                taskTypesIds.add(taskForPositions.get(i).getTaskTypeId());
            }
        }

        for(int i = 0; i < taskTypes.size(); i++) {
            for(int j = 0; j < taskTypesIds.size(); j++) {
                if(taskTypes.get(i).getTaskTypeId() == taskTypesIds.get(j)) {
                    taskTypeViewModels.add(new TaskTypeViewModel(taskTypes.get(i)));
                }
            }
        }

        return taskTypeViewModels;
    }

    /**
     *
     * @param date
     * @return
     */
    public List<JobLogViewModel> getJobLogsByDate(Date date) {
        List<JobLog> jobLogs = jobLogBaseController.listAll(mContextReference.get());
        List<JobLogViewModel> jobLogViewModels = new ArrayList<>();
        List<TaskTypeViewModel> taskTypes = getTaskForWorKPosition();

        for(int i = 0; i < jobLogs.size(); i++) {
            for (int j = 0; j < taskTypes.size(); j++) {
                if (jobLogs.get(i).getWorkDate().equals(date)) {
                    jobLogViewModels.add(new JobLogViewModel(jobLogs.get(i), taskTypes.get(j)));
                }
            }
        }

        return jobLogViewModels;
    }

    public List<JobLogViewModel> getJobLogsByMonth(@NonNull Date month) {
        List<JobLog> jobLogs = jobLogBaseController.listAll(mContextReference.get());
        List<JobLogViewModel> jobLogViewModels = new ArrayList<>();
        List<TaskTypeViewModel> taskTypes = getTaskForWorKPosition();

        final Calendar monthCalendar = Calendar.getInstance();
        monthCalendar.setTime(month);

        for(int i = 0; i < jobLogs.size(); i++) {
            for (int j = 0; j < taskTypes.size(); j++) {
                Calendar workCalendar = Calendar.getInstance();
                workCalendar.setTime(jobLogs.get(i).getWorkDate());

                if (workCalendar.get(Calendar.MONTH) == monthCalendar.get(Calendar.MONTH) &&
                    workCalendar.get(Calendar.YEAR) == monthCalendar.get(Calendar.YEAR)) {
                    jobLogViewModels.add(new JobLogViewModel(jobLogs.get(i), taskTypes.get(j)));
                }
            }
        }

        return jobLogViewModels;
    }

    /**
     *
     * @param jobLog
     * @return
     */
    public boolean insertJobLog(@NonNull JobLog jobLog) {
        return jobLogBaseController.insert(mContextReference.get(), jobLog);
    }

    /**
     *
     * @param jobLog
     * @return
     */
    public boolean updateJobLog(@NonNull JobLog jobLog) {
        return jobLogBaseController.update(mContextReference.get(), jobLog);
    }

    /**
     *
     * @param id
     * @return
     */
    public boolean deleteJobLog(long id) {
        return jobLogBaseController.delete(mContextReference.get(), id);
    }

    public int getWorkHours(long personId) {
        return personBaseController.get(mContextReference.get(), personId).getWorkHours();
    }
}
