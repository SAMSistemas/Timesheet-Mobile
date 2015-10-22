package com.samsistemas.timesheet.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.samsistemas.timesheet.data.R;
import com.samsistemas.timesheet.model.base.BaseModel;

import java.util.Date;

/**
 * Domain class that represents the data contained in the table JobLog.
 *
 * @author jonatan.salas
 */
public class JobLog implements BaseModel<JobLog>, Parcelable {
    private long jobLogId;
    private long projectId;
    private long personId;
    private long taskTypeId;
    private String hours;
    private Date workDate;
    private int solicitude;
    private String observations;

    public JobLog() {}

    /**
     * Protected constructor with parameter.
     *
     * @param in - parcel containing class attributes data.
     */
    protected JobLog(Parcel in) {
        jobLogId = in.readLong();
        projectId = in.readLong();
        personId = in.readLong();
        taskTypeId = in.readLong();
        hours = in.readString();
        long tmpWorkDate = in.readLong();
        workDate = tmpWorkDate != -1 ? new Date(tmpWorkDate) : null;
        solicitude = in.readInt();
        observations = in.readString();
    }


    /** Attributes setters and getters **/
    public JobLog setJobLogId(long jobLogId) {
        this.jobLogId = jobLogId;
        return this;
    }

    public JobLog setProjectId(long projectId) {
        this.projectId = projectId;
        return this;
    }

    public JobLog setPersonId(long personId) {
        this.personId = personId;
        return this;
    }

    public JobLog setTaskTypeId(long taskTypeId) {
        this.taskTypeId = taskTypeId;
        return this;
    }

    public JobLog setHours(String hours) {
        this.hours = hours;
        return this;
    }

    public JobLog setWorkDate(Date workDate) {
        this.workDate = workDate;
        return this;
    }

    public JobLog setSolicitude(int solicitude) {
        this.solicitude = solicitude;
        return this;
    }

    public JobLog setObservations(String observations) {
        this.observations = observations;
        return this;
    }

    public long getJobLogId() {
        return jobLogId;
    }

    public long getProjectId() {
        return projectId;
    }

    public long getPersonId() {
        return personId;
    }

    public long getTaskTypeId() {
        return taskTypeId;
    }

    public String getHours() {
        return hours;
    }

    public Date getWorkDate() {
        return workDate;
    }

    public int getSolicitude() {
        return solicitude;
    }

    public String getObservations() {
        return observations;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(jobLogId);
        dest.writeLong(projectId);
        dest.writeLong(personId);
        dest.writeLong(taskTypeId);
        dest.writeString(hours);
        dest.writeLong(workDate != null ? workDate.getTime() : -1L);
        dest.writeInt(solicitude);
        dest.writeString(observations);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<JobLog> CREATOR = new Parcelable.Creator<JobLog>() {
        @Override
        public JobLog createFromParcel(Parcel in) {
            return new JobLog(in);
        }

        @Override
        public JobLog[] newArray(int size) {
            return new JobLog[size];
        }
    };

    @Override
    public ContentValues asContentValues(@NonNull Context context) {
        final ContentValues values = new ContentValues(8);

        values.put(context.getString(R.string.job_log_id), jobLogId);
        values.put(context.getString(R.string.job_log_project_id), projectId);
        values.put(context.getString(R.string.job_log_person_id), personId);
        values.put(context.getString(R.string.job_log_task_type_id), taskTypeId);
        values.put(context.getString(R.string.job_log_hours), hours);
        values.put(context.getString(R.string.job_log_work_date), workDate.getTime());
        values.put(context.getString(R.string.job_log_solicitude), solicitude);
        values.put(context.getString(R.string.job_log_observations), observations);

        return values;
    }

    @Override
    public JobLog save(@NonNull Context context, Cursor cursor) {
        long millis = cursor.getLong(cursor.getColumnIndexOrThrow(context.getString(R.string.job_log_work_date)));

        final JobLog jobLog = new JobLog();

        jobLog.setJobLogId(cursor.getLong(cursor.getColumnIndexOrThrow(context.getString(R.string.job_log_id))))
              .setProjectId(cursor.getLong(cursor.getColumnIndexOrThrow(context.getString(R.string.job_log_project_id))))
              .setPersonId(cursor.getLong(cursor.getColumnIndexOrThrow(context.getString(R.string.job_log_person_id))))
              .setTaskTypeId(cursor.getLong(cursor.getColumnIndexOrThrow(context.getString(R.string.job_log_task_type_id))))
              .setHours(cursor.getString(cursor.getColumnIndexOrThrow(context.getString(R.string.job_log_hours))))
              .setWorkDate(new Date(millis))
              .setSolicitude(cursor.getInt(cursor.getColumnIndexOrThrow(context.getString(R.string.job_log_solicitude))))
              .setObservations(cursor.getString(cursor.getColumnIndexOrThrow(context.getString(R.string.job_log_observations))));

        if(!cursor.isClosed())
            cursor.close();

        return jobLog;
    }
}
