package com.samsistemas.timesheet.mapper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.samsistemas.timesheet.data.R;
import com.samsistemas.timesheet.entity.WorkPositionEntity;
import com.samsistemas.timesheet.mapper.base.EntityMapper;

/**
 * @author jonatan.salas
 */
public class WorkPositionEntityMapper implements EntityMapper<WorkPositionEntity, Cursor> {

    @Override
    public ContentValues asContentValues(@NonNull Context context, @NonNull WorkPositionEntity workPositionEntity) {
        final ContentValues values = new ContentValues(2);

        values.put(context.getString(R.string.work_position_id), workPositionEntity.getWorkPositionId());
        values.put(context.getString(R.string.work_position_description), workPositionEntity.getDescription());

        return values;
    }

    @Override
    public WorkPositionEntity asEntity(@NonNull Context context, @Nullable Cursor cursor) {
        if (null != cursor && cursor.moveToFirst()) {
            return new WorkPositionEntity()
                    .setWorkPositionId(cursor.getLong(cursor.getColumnIndexOrThrow(context.getString(R.string.work_position_id))))
                    .setDescription(cursor.getString(cursor.getColumnIndexOrThrow(context.getString(R.string.work_position_description))));
        }

        return null;
    }
}
