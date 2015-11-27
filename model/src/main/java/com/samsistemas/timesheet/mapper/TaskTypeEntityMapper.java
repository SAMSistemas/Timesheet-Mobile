package com.samsistemas.timesheet.mapper;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.samsistemas.timesheet.entity.TaskTypeEntity;
import com.samsistemas.timesheet.mapper.base.EntityMapper;
import com.samsistemas.timesheet.util.ConversionUtil;

/**
 * @author jonatan.salas
 */
public class TaskTypeEntityMapper implements EntityMapper<TaskTypeEntity, Cursor> {
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
        if (null != cursor) {
            final int available = cursor.getInt(cursor.getColumnIndexOrThrow(ENABLED));
            final boolean taskTypeEnabled = ConversionUtil.intToBoolean(available);

            TaskTypeEntity entity = new TaskTypeEntity();

            entity.setId(cursor.getInt(cursor.getColumnIndexOrThrow(ID_TASK_TYPE)));
            entity.setName(cursor.getString(cursor.getColumnIndexOrThrow(NAME)))
                  .setEnabled(taskTypeEnabled);

            return entity;
        }

        return null;
    }
}
