package com.samsistemas.timesheet.mapper;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.samsistemas.timesheet.entity.WorkPositionEntity;
import com.samsistemas.timesheet.mapper.base.EntityMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jonatan.salas
 */
public class WorkPositionEntityMapper implements EntityMapper<WorkPositionEntity, Cursor> {
    private static final String LOG_TAG = WorkPositionEntityMapper.class.getSimpleName();
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
        WorkPositionEntity entity = new WorkPositionEntity();

        try {
            if (null != cursor && cursor.getCount() == 0) {
                return entity;
            }

            if (null != cursor && cursor.moveToFirst()) {
                entity.setId(cursor.getLong(cursor.getColumnIndexOrThrow(ID_WORK_POSITION)));
                entity.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(DESCRIPTION)));
            }

        } catch (Exception ex) {
            Log.e(LOG_TAG, ex.getMessage(), ex.getCause());
        } finally {
            if (null != cursor && !cursor.isClosed()) {
                cursor.close();
            }
        }

        return entity;
    }

    @Override
    public List<WorkPositionEntity> asEntityList(@Nullable Cursor cursor) {
        List<WorkPositionEntity> entityList = new ArrayList<>();

        try {
            if (null != cursor && cursor.getCount() == 0) {
                return entityList;
            }

            if (null != cursor && cursor.moveToFirst()) {
               do {
                    WorkPositionEntity entity = new WorkPositionEntity();

                    entity.setId(cursor.getLong(cursor.getColumnIndexOrThrow(ID_WORK_POSITION)));
                    entity.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(DESCRIPTION)));

                    entityList.add(entity);

               } while (cursor.moveToNext());
            }
        } catch (Exception ex) {
            Log.e(LOG_TAG, ex.getMessage(), ex.getCause());
        } finally {
            if (null != cursor && !cursor.isClosed()) {
                cursor.close();
            }
        }

        return entityList;
    }
}
