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
import com.samsistemas.timesheet.entity.TaskTypeEntity;
import com.samsistemas.timesheet.mapper.base.EntityMapper;
import com.samsistemas.timesheet.util.CursorUtil;

import java.util.List;

/**
 * Class that implements the TaskTypeController interface.
 *
 * @author jonatan.salas
 */
public class TaskTypeController implements BaseController<TaskTypeEntity> {
    protected EntityMapper<TaskTypeEntity, Cursor> taskTypeMapper;

    public TaskTypeController() {
        this.taskTypeMapper = MapperFactory.getTaskTypeMapper();
    }

    @Override
    public boolean insert(@NonNull Context context, @NonNull TaskTypeEntity taskTypeEntity) {
        final Uri taskTypeUri = UriHelper.buildTaskTypeUri(context);
        final ContentValues taskTypeValues = taskTypeMapper.asContentValues(taskTypeEntity);
        final Uri resultUri = context.getContentResolver().insert(taskTypeUri, taskTypeValues);
        return (null != resultUri);
    }

    @Override
    public boolean bulkInsert(@NonNull Context context, @NonNull List<TaskTypeEntity> taskTypeEntities) {
        final Uri taskTypeUri = UriHelper.buildTaskTypeUri(context);
        final ContentValues[] taskTypeValues = new ContentValues[taskTypeEntities.size()];

        for(int i = 0; i < taskTypeEntities.size(); i++) {
            taskTypeValues[i] = taskTypeMapper.asContentValues(taskTypeEntities.get(i));
        }

        int count = context.getContentResolver().bulkInsert(taskTypeUri, taskTypeValues);

        return (count != 0);
    }

    @Override
    public TaskTypeEntity get(@NonNull Context context, long id) {
        final Uri taskTypeUri = UriHelper.buildTaskTypeUriWithId(context, id);
        Cursor taskTypeCursor = context.getContentResolver().query(taskTypeUri, null, null, null, null);

        if(null != taskTypeCursor)
            taskTypeCursor.moveToFirst();

        final TaskTypeEntity taskTypeEntity = taskTypeMapper.asEntity(taskTypeCursor);

        if(null != taskTypeCursor && taskTypeCursor.isClosed())
            taskTypeCursor.close();

        return taskTypeEntity;
    }

    @Override
    public List<TaskTypeEntity> listAll(@NonNull Context context) {
        final Uri taskTypeUri = UriHelper.buildTaskTypeUri(context);
        Cursor taskTypeCursor = context.getContentResolver().query(taskTypeUri, null, null, null, null);

        return CursorUtil.asEntityList(taskTypeCursor, taskTypeMapper);
    }

    @Override
    public boolean update(@NonNull Context context, @NonNull TaskTypeEntity taskTypeEntity) {
        final Uri taskTypeUri = UriHelper.buildTaskTypeUri(context);
        final ContentValues taskTypeValues = taskTypeMapper.asContentValues(taskTypeEntity);
        final String whereClause = context.getString(R.string.task_type_id) + " =? ";
        final String[] whereArgs = new String[] { String.valueOf(taskTypeEntity.getTaskTypeId()) };

        int updatedRows = context.getContentResolver().update(taskTypeUri, taskTypeValues, whereClause, whereArgs);

        return (0 != updatedRows);
    }

    @Override
    public boolean delete(@NonNull Context context, long id) {
        final Uri taskTypeUri = UriHelper.buildTaskTypeUri(context);
        final String selection = context.getString(R.string.task_type_id) + " =? ";
        final String[] selectionArgs = new String[] { String.valueOf(id) };

        int deletedRows = context.getContentResolver().delete(taskTypeUri, selection, selectionArgs);

        return (0 != deletedRows);
    }
}
