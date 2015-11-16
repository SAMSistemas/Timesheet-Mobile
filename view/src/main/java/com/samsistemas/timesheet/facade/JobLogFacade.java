package com.samsistemas.timesheet.facade;

import android.content.Context;
import android.support.annotation.NonNull;

import com.samsistemas.timesheet.controller.base.BaseController;
import com.samsistemas.timesheet.entity.JobLogEntity;
import com.samsistemas.timesheet.facade.base.Facade;
import com.samsistemas.timesheet.factory.ControllerFactory;
import com.samsistemas.timesheet.model.JobLog;
import com.samsistemas.timesheet.model.Person;
import com.samsistemas.timesheet.model.Project;
import com.samsistemas.timesheet.model.TaskType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jonatan.salas
 */
public class JobLogFacade implements Facade<JobLog> {
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
    public boolean insert(@NonNull Context context, JobLog jobLog) {
        final JobLogEntity entity = new JobLogEntity();

        entity.setJobLogId(jobLog.getId())
              .setProjectId(jobLog.getProject().getId())
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
