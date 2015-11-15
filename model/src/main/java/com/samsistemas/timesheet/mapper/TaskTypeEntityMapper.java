package com.samsistemas.timesheet.mapper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.samsistemas.timesheet.data.R;
import com.samsistemas.timesheet.entity.TaskTypeEntity;
import com.samsistemas.timesheet.mapper.base.EntityMapper;
import com.samsistemas.timesheet.util.ConversionUtil;

/**
 * @author jonatan.salas
 */
public class TaskTypeEntityMapper implements EntityMapper<TaskTypeEntity, Cursor> {

    @Override
    public ContentValues asContentValues(@NonNull Context context, @NonNull TaskTypeEntity taskTypeEntity) {
        final ContentValues values = new ContentValues(3);
        final int taskTypeEnabled = ConversionUtil.booleanToInt(taskTypeEntity.isEnabled());

        values.put(context.getString(R.string.task_type_id), taskTypeEntity.getTaskTypeId());
        values.put(context.getString(R.string.task_type_name), taskTypeEntity.getName());
        values.put(context.getString(R.string.task_type_enabled), taskTypeEnabled);

        return values;
    }

    @Override
    public TaskTypeEntity asEntity(@NonNull Context context, @Nullable Cursor cursor) {
        if (null != cursor && cursor.moveToFirst()) {
            final int available = cursor.getInt(cursor.getColumnIndexOrThrow(context.getString(R.string.task_type_enabled)));
            final boolean taskTypeEnabled = ConversionUtil.intToBoolean(available);

            final TaskTypeEntity taskTypeEntity = new TaskTypeEntity();

            taskTypeEntity.setTaskTypeId(cursor.getInt(cursor.getColumnIndexOrThrow(context.getString(R.string.task_type_id))))
                    .setName(cursor.getString(cursor.getColumnIndexOrThrow(context.getString(R.string.task_type_name))))
                    .setEnabled(taskTypeEnabled);

            if (!cursor.isClosed())
                cursor.close();

            return taskTypeEntity;
        }

        return null;
    }
}
