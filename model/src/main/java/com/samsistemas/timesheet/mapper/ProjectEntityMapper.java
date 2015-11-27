package com.samsistemas.timesheet.mapper;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.samsistemas.timesheet.entity.ProjectEntity;
import com.samsistemas.timesheet.mapper.base.EntityMapper;
import com.samsistemas.timesheet.util.ConversionUtil;

import java.util.Date;

/**
 * @author jonatan.salas
 */
public class ProjectEntityMapper implements EntityMapper<ProjectEntity, Cursor> {
    private static final String ID_PROJECT = "id";
    private static final String ID_CLIENT = "id_client";
    private static final String NAME = "name";
    private static final String SHORT_NAME = "short_name";
    private static final String START_DATE = "start_date";
    private static final String ENABLED = "enabled";

    @Override
    public ContentValues asContentValues(@NonNull ProjectEntity projectEntity) {
        ContentValues values = new ContentValues(6);
        int projectEnabled = ConversionUtil.booleanToInt(projectEntity.isEnabled());

        values.put(ID_PROJECT, projectEntity.getId());
        values.put(ID_CLIENT, projectEntity.getClientId());
        values.put(NAME, projectEntity.getName());
        values.put(SHORT_NAME, projectEntity.getShortName());
        values.put(START_DATE, projectEntity.getStartDate().getTime());
        values.put(ENABLED, projectEnabled);

        return values;
    }

    @Override
    public ProjectEntity asEntity(@Nullable Cursor cursor) {
        if (null != cursor) {
            final int available = cursor.getInt(cursor.getColumnIndexOrThrow(ENABLED));
            final long millis = cursor.getLong(cursor.getColumnIndexOrThrow(START_DATE));
            final boolean projectEnabled = ConversionUtil.intToBoolean(available);

            ProjectEntity entity = new ProjectEntity();

            entity.setId(cursor.getLong(cursor.getColumnIndexOrThrow(ID_PROJECT)));
            entity.setClientId(cursor.getLong(cursor.getColumnIndexOrThrow(ID_CLIENT)))
                  .setName(cursor.getString(cursor.getColumnIndexOrThrow(NAME)))
                  .setShortName(cursor.getString(cursor.getColumnIndexOrThrow(SHORT_NAME)))
                  .setStartDate(new Date(millis))
                  .setEnabled(projectEnabled);

            return entity;
        }

        return null;
    }
}
