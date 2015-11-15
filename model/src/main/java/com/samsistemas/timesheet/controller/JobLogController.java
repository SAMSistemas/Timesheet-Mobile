package com.samsistemas.timesheet.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.samsistemas.timesheet.controller.base.BaseController;
import com.samsistemas.timesheet.data.R;
import com.samsistemas.timesheet.factory.MapperFactory;
import com.samsistemas.timesheet.helper.UriHelper;
import com.samsistemas.timesheet.entity.JobLogEntity;
import com.samsistemas.timesheet.mapper.base.EntityMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that implements the JobLogController interface.
 *
 * @author jonatan.salas
 */
public class JobLogController implements BaseController<JobLogEntity> {
    protected EntityMapper<JobLogEntity, Cursor> joblogMapper;

    public JobLogController() {
        this.joblogMapper = MapperFactory.getJoblogMapper();
    }

    @Override
    public boolean insert(@NonNull Context context, @NonNull JobLogEntity jobLogEntity) {
        final Uri jobLogUri = UriHelper.buildJobLogUri(context);
        final ContentValues jobLogValues = joblogMapper.asContentValues(context, jobLogEntity);

        final Uri resultUri = context.getContentResolver().insert(jobLogUri, jobLogValues);

        return (null != resultUri);
    }

    @Override
    public boolean bulkInsert(@NonNull Context context, @NonNull List<JobLogEntity> jobLogEntities) {
        final Uri jobLogsUri = UriHelper.buildJobLogUri(context);
        final ContentValues[] jobLogsValues = new ContentValues[jobLogEntities.size()];

        for(int i = 0; i < jobLogEntities.size(); i++) {
            jobLogsValues[i] = joblogMapper.asContentValues(context, jobLogEntities.get(i));
        }

        final int count = context.getContentResolver().bulkInsert(jobLogsUri, jobLogsValues);

        return (0 != count);
    }

    @Override
    public JobLogEntity get(@NonNull Context context, long id) {
        final Uri jobLogUri = UriHelper.buildJobLogUriWithId(context, id);
        final Cursor jobLogCursor = context.getContentResolver().query(jobLogUri, null, null, null, null);

        return joblogMapper.asEntity(context, jobLogCursor);
    }

    @Override
    public List<JobLogEntity> listAll(@NonNull Context context) {
        final Uri jobLogUri = UriHelper.buildJobLogUri(context);
        final Cursor jobLogsCursor = context.getContentResolver().query(jobLogUri, null, null, null, null);

        List<JobLogEntity> jobLogEntities = new ArrayList<>();

        if(null != jobLogsCursor && jobLogsCursor.moveToFirst()) {
            for(int i = 0; i < jobLogsCursor.getCount(); i++) {
                jobLogEntities.add(joblogMapper.asEntity(context, jobLogsCursor));
            }
        }

        return jobLogEntities;
    }

    @Override
    public boolean update(@NonNull Context context, @NonNull JobLogEntity jobLogEntity) {
        final Uri jobLogUri = UriHelper.buildJobLogUri(context);
        final ContentValues jobLogValues = joblogMapper.asContentValues(context, jobLogEntity);
        final String whereClause = context.getString(R.string.job_log_id) + " =? ";
        final String[] whereArgs = new String[] { String.valueOf(jobLogEntity.getJobLogId()) };

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
