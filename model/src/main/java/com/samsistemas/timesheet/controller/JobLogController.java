package com.samsistemas.timesheet.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.samsistemas.timesheet.controller.base.BaseController;
import com.samsistemas.timesheet.data.R;
import com.samsistemas.timesheet.helper.UriHelper;
import com.samsistemas.timesheet.model.JobLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that implements the JobLogController interface.
 *
 * @author jonatan.salas
 */
public class JobLogController implements BaseController<JobLog> {

    @Override
    public boolean insert(@NonNull Context context, @NonNull JobLog jobLog) {
        final Uri jobLogUri = UriHelper.buildJobLogUri(context);
        final ContentValues jobLogValues = jobLog.asContentValues(context);

        final Uri resultUri = context.getContentResolver().insert(jobLogUri, jobLogValues);

        return (null != resultUri);
    }

    @Override
    public boolean bulkInsert(@NonNull Context context, @NonNull List<JobLog> jobLogs) {
        final Uri jobLogsUri = UriHelper.buildJobLogUri(context);
        final ContentValues[] jobLogsValues = new ContentValues[jobLogs.size()];

        for(int i = 0; i < jobLogs.size(); i++) {
            jobLogsValues[i] = jobLogs.get(i).asContentValues(context);
        }

        final int count = context.getContentResolver().bulkInsert(jobLogsUri, jobLogsValues);

        return (0 != count);
    }

    @Override
    public JobLog get(@NonNull Context context, long id) {
        final Uri jobLogUri = UriHelper.buildJobLogUriWithId(context, id);
        final Cursor jobLogCursor = context.getContentResolver().query(jobLogUri, null, null, null, null);

        return new JobLog().save(context, jobLogCursor);
    }

    @Override
    public List<JobLog> listAll(@NonNull Context context) {
        final Uri jobLogUri = UriHelper.buildJobLogUri(context);
        final Cursor jobLogsCursor = context.getContentResolver().query(jobLogUri, null, null, null, null);

        List<JobLog> jobLogs = new ArrayList<>();

        if(null != jobLogsCursor && jobLogsCursor.moveToFirst()) {
            for(int i = 0; i < jobLogsCursor.getCount(); i++) {
                jobLogs.add(new JobLog().save(context, jobLogsCursor));
            }
        }

        return jobLogs;
    }

    @Override
    public boolean update(@NonNull Context context, @NonNull JobLog jobLog) {
        final Uri jobLogUri = UriHelper.buildJobLogUri(context);
        final ContentValues jobLogValues = jobLog.asContentValues(context);
        final String whereClause = context.getString(R.string.job_log_id) + " =? ";
        final String[] whereArgs = new String[] { String.valueOf(jobLog.getJobLogId()) };

        int updatedRows = context.getContentResolver().update(jobLogUri, jobLogValues, whereClause, whereArgs);

        return (0 != updatedRows);
    }

    @Override
    public boolean delete(@NonNull Context context, long id) {
        final Uri jobLogUri = UriHelper.buildJobLogUri(context);
        final String selection = context.getString(R.string.job_log_id) + " =? ";
        final String[] selectionArgs = new String[] { String.valueOf(id) };

        int deletedRows = context.getContentResolver().delete(jobLogUri, selection, selectionArgs);

        return (0 != deletedRows);
    }
}
