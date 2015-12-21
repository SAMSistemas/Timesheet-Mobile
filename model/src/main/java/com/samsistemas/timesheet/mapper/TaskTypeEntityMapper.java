package com.samsistemas.timesheet.mapper;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.samsistemas.timesheet.entity.TaskTypeEntity;
import com.samsistemas.timesheet.mapper.base.EntityMapper;
import com.samsistemas.timesheet.util.ConversionUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jonatan.salas
 */
public class TaskTypeEntityMapper implements EntityMapper<TaskTypeEntity, Cursor> {
    private static final String LOG_TAG = TaskTypeEntityMapper.class.getSimpleName();
    private static final String ID_TASK_TYPE = "id";
    private static final String NAME = "name";
    private static final String ENABLED = "enabled";

    @Override
    public ContentValues asContentValues(@NonNull TaskTypeEntity taskTypeEntity) {
        ContentValues values = new ContentValues(3);
        int taskTypeEnabled = ConversionUtil.booleanToInt(taskTypeEntity.isEnabled());

        values.put(ID_TASK_TYPE, taskTypeEntity.getId());
        values.put(NAME, taskTypeEntity.getName());
        values.put(ENABLED, taskTypeEnabled);

        return values;
    }

    @Override
    public TaskTypeEntity asEntity(@Nullable Cursor cursor) {
        TaskTypeEntity entity = new TaskTypeEntity();

        try {
            if (null != cursor && cursor.getCount() == 0) {
                return entity;
            }

            if (null != cursor && cursor.moveToFirst()) {
                final int available = cursor.getInt(cursor.getColumnIndexOrThrow(ENABLED));
                final boolean taskTypeEnabled = ConversionUtil.intToBoolean(available);

                entity.setId(cursor.getInt(cursor.getColumnIndexOrThrow(ID_TASK_TYPE)));
                entity.setName(cursor.getString(cursor.getColumnIndexOrThrow(NAME)))
                      .setEnabled(taskTypeEnabled);
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
    public List<TaskTypeEntity> asEntityList(@Nullable Cursor cursor) {
        List<TaskTypeEntity> entityList = new ArrayList<>();

        try {
            if (null != cursor && cursor.getCount() == 0) {
                return entityList;
            }

            if (null != cursor && cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {
                    final int available = cursor.getInt(cursor.getColumnIndexOrThrow(ENABLED));
                    final boolean taskTypeEnabled = ConversionUtil.intToBoolean(available);

                    TaskTypeEntity entity = new TaskTypeEntity();

                    entity.setId(cursor.getInt(cursor.getColumnIndexOrThrow(ID_TASK_TYPE)));
                    entity.setName(cursor.getString(cursor.getColumnIndexOrThrow(NAME)))
                          .setEnabled(taskTypeEnabled);

                    entityList.add(entity);

                    cursor.moveToNext();
                }
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
