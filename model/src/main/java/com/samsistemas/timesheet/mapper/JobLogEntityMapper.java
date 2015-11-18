package com.samsistemas.timesheet.mapper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.samsistemas.timesheet.data.R;
import com.samsistemas.timesheet.entity.JobLogEntity;
import com.samsistemas.timesheet.mapper.base.EntityMapper;

import java.util.Date;

/**
 * @author jonatan.salas
 */
public class JobLogEntityMapper implements EntityMapper<JobLogEntity, Cursor> {

    @Override
    public ContentValues asContentValues(@NonNull Context context, @NonNull JobLogEntity objectToMap) {
        final ContentValues values = new ContentValues(8);

        values.put(context.getString(R.string.job_log_id), objectToMap.getJobLogId());
        values.put(context.getString(R.string.job_log_project_id), objectToMap.getProjectId());
        values.put(context.getString(R.string.job_log_person_id), objectToMap.getPersonId());
        values.put(context.getString(R.string.job_log_task_type_id), objectToMap.getTaskTypeId());
        values.put(context.getString(R.string.job_log_hours), objectToMap.getHours());
        values.put(context.getString(R.string.job_log_work_date), objectToMap.getWorkDate().getTime());
        values.put(context.getString(R.string.job_log_solicitude), objectToMap.getSolicitude());
        values.put(context.getString(R.string.job_log_observations), objectToMap.getObservations());

        return values;
    }

    @Override
    public JobLogEntity asEntity(@NonNull Context context, @Nullable Cursor cursor) {
        if(null != cursor && cursor.moveToFirst()) {
            long millis = cursor.getLong(cursor.getColumnIndexOrThrow(context.getString(R.string.job_log_work_date)));

            final JobLogEntity jobLogEntity = new JobLogEntity();

            jobLogEntity.setJobLogId(cursor.getLong(cursor.getColumnIndexOrThrow(context.getString(R.string.job_log_id))))
                    .setProjectId(cursor.getLong(cursor.getColumnIndexOrThrow(context.getString(R.string.job_log_project_id))))
                    .setPersonId(cursor.getLong(cursor.getColumnIndexOrThrow(context.getString(R.string.job_log_person_id))))
                    .setTaskTypeId(cursor.getLong(cursor.getColumnIndexOrThrow(context.getString(R.string.job_log_task_type_id))))
                    .setHours(cursor.getString(cursor.getColumnIndexOrThrow(context.getString(R.string.job_log_hours))))
                    .setWorkDate(new Date(millis))
                    .setSolicitude(cursor.getInt(cursor.getColumnIndexOrThrow(context.getString(R.string.job_log_solicitude))))
                    .setObservations(cursor.getString(cursor.getColumnIndexOrThrow(context.getString(R.string.job_log_observations))));

            return jobLogEntity;
        }

        return null;
    }
}
