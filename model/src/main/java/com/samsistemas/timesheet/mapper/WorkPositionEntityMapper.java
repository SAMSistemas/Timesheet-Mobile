package com.samsistemas.timesheet.mapper;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.samsistemas.timesheet.entity.WorkPositionEntity;
import com.samsistemas.timesheet.mapper.base.EntityMapper;

/**
 * @author jonatan.salas
 */
public class WorkPositionEntityMapper implements EntityMapper<WorkPositionEntity, Cursor> {
    private static final String ID_WORK_POSITION = "id";
    private static final String DESCRIPTION = "description";

    @Override
    public ContentValues asContentValues(@NonNull WorkPositionEntity workPositionEntity) {
        ContentValues values = new ContentValues(2);

        values.put(ID_WORK_POSITION, workPositionEntity.getId());
        values.put(DESCRIPTION, workPositionEntity.getDescription());

        return values;
    }

    @Override
    public WorkPositionEntity asEntity(@Nullable Cursor cursor) {
        if (null != cursor && cursor.moveToFirst()) {
            WorkPositionEntity entity = new WorkPositionEntity();

            entity.setId(cursor.getLong(cursor.getColumnIndexOrThrow(ID_WORK_POSITION)));
            entity.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(DESCRIPTION)));

            return entity;
        }

        return null;
    }
}
