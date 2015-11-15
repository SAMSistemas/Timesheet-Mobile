package com.samsistemas.timesheet.mapper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.samsistemas.timesheet.data.R;
import com.samsistemas.timesheet.entity.ProjectEntity;
import com.samsistemas.timesheet.mapper.base.EntityMapper;
import com.samsistemas.timesheet.util.ConversionUtil;

import java.util.Date;

/**
 * @author jonatan.salas
 */
public class ProjectEntityMapper implements EntityMapper<ProjectEntity, Cursor> {

    @Override
    public ContentValues asContentValues(@NonNull Context context, @NonNull ProjectEntity projectEntity) {
        final ContentValues values = new ContentValues(6);
        final int projectEnabled = ConversionUtil.booleanToInt(projectEntity.isEnabled());

        values.put(context.getString(R.string.project_id), projectEntity.getProjectId());
        values.put(context.getString(R.string.project_client_id), projectEntity.getClientId());
        values.put(context.getString(R.string.project_name), projectEntity.getName());
        values.put(context.getString(R.string.project_short_name), projectEntity.getShortName());
        values.put(context.getString(R.string.project_start_date), projectEntity.getStartDate().getTime());
        values.put(context.getString(R.string.project_enabled), projectEnabled);

        return values;
    }

    @Override
    public ProjectEntity asEntity(@NonNull Context context, @Nullable Cursor cursor) {
        if (null != cursor && cursor.moveToFirst()) {
            final int available = cursor.getInt(cursor.getColumnIndexOrThrow(context.getString(R.string.project_enabled)));
            final long millis = cursor.getLong(cursor.getColumnIndexOrThrow(context.getString(R.string.project_start_date)));
            final boolean projectEnabled = ConversionUtil.intToBoolean(available);

            final ProjectEntity projectEntity = new ProjectEntity();

            projectEntity.setProjectId(cursor.getLong(cursor.getColumnIndexOrThrow(context.getString(R.string.project_id))))
                    .setClientId(cursor.getLong(cursor.getColumnIndexOrThrow(context.getString(R.string.project_client_id))))
                    .setName(cursor.getString(cursor.getColumnIndexOrThrow(context.getString(R.string.project_name))))
                    .setShortName(cursor.getString(cursor.getColumnIndexOrThrow(context.getString(R.string.project_short_name))))
                    .setStartDate(new Date(millis))
                    .setEnabled(projectEnabled);

            if (!cursor.isClosed())
                cursor.close();

            return projectEntity;
        }

        return null;
    }
}
