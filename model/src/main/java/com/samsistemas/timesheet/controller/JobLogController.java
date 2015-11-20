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
import com.samsistemas.timesheet.util.CursorUtil;

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
        final JobLogEntity entity = get(context, jobLogEntity.getJobLogId());

        if (null != entity) {
            return false;
        } else {
            final Uri resultUri = context.getContentResolver().insert(jobLogUri, jobLogValues);
            return (null != resultUri);
        }
    }

    @Override
    public boolean bulkInsert(@NonNull Context context, @NonNull List<JobLogEntity> jobLogEntities) {
        int count = 0;

        for(JobLogEntity entity: jobLogEntities) {
            boolean inserted = insert(context, entity);
            if(inserted)
                count++;
        }

        return (count == jobLogEntities.size());
    }

    @Override
    public JobLogEntity get(@NonNull Context context, long id) {
        final Uri jobLogUri = UriHelper.buildJobLogUriWithId(context, id);
        Cursor jobLogCursor = context.getContentResolver().query(jobLogUri, null, null, null, null);
        if(null != jobLogCursor)
            jobLogCursor.moveToFirst();

        final JobLogEntity jobLogEntity = joblogMapper.asEntity(context, jobLogCursor);

        if(null != jobLogCursor && !jobLogCursor.isClosed())
            jobLogCursor.close();

        return jobLogEntity;
    }

    @Override
    public List<JobLogEntity> listAll(@NonNull Context context) {
        final Uri jobLogUri = UriHelper.buildJobLogUri(context);
        Cursor jobLogsCursor = context.getContentResolver().query(jobLogUri, null, null, null, null);

        return CursorUtil.asEntityList(context, jobLogsCursor, joblogMapper);
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
