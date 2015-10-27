package com.samsistemas.timesheet.viewmodel;

import com.samsistemas.timesheet.model.JobLog;

import java.util.Date;

/**
 * @author jonatan.salas
 */
public class JobLogViewModel {
    private long jobLogId;
    private Date jobLogDate;
    private String jobLogHours;
    private String jobLogObservation;
    private TaskTypeViewModel taskTypeViewModel;

    /**
     *
     * @param jobLog
     */
    public JobLogViewModel(JobLog jobLog, TaskTypeViewModel taskTypeViewModel) {
        this.jobLogId = jobLog.getJobLogId();
        this.jobLogDate = jobLog.getWorkDate();
        this.jobLogHours = jobLog.getHours();
        this.jobLogObservation = jobLog.getObservations();
        this.taskTypeViewModel = taskTypeViewModel;
    }

    public JobLogViewModel setJobLogId(long jobLogId) {
        this.jobLogId = jobLogId;
        return this;
    }

    public JobLogViewModel setJobLogDate(Date jobLogDate) {
        this.jobLogDate = jobLogDate;
        return this;
    }

    public JobLogViewModel setJobLogHours(String jobLogHours) {
        this.jobLogHours = jobLogHours;
        return this;
    }

    public JobLogViewModel setJobLogObservation(String jobLogObservation) {
        this.jobLogObservation = jobLogObservation;
        return this;
    }

    public JobLogViewModel setTaskTypeViewModel(TaskTypeViewModel taskTypeViewModel) {
        this.taskTypeViewModel = taskTypeViewModel;
        return this;
    }

    public long getJobLogId() {
        return jobLogId;
    }

    public Date getJobLogDate() {
        return jobLogDate;
    }

    public String getJobLogHours() {
        return jobLogHours;
    }

    public String getJobLogObservation() {
        return jobLogObservation;
    }

    public TaskTypeViewModel getTaskTypeViewModel() {
        return taskTypeViewModel;
    }
}
