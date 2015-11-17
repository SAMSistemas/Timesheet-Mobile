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
import com.samsistemas.timesheet.mapper.TaskTypeEntityMapper;
import com.samsistemas.timesheet.mapper.base.EntityMapper;

import java.util.ArrayList;
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
        final ContentValues taskTypeValues = taskTypeMapper.asContentValues(context, taskTypeEntity);
        final TaskTypeEntity entity = get(context, taskTypeEntity.getTaskTypeId());

        if (null != entity) {
            return false;
        } else {
            final Uri resultUri = context.getContentResolver().insert(taskTypeUri, taskTypeValues);
            return (null != resultUri);
        }
    }

    @Override
    public boolean bulkInsert(@NonNull Context context, @NonNull List<TaskTypeEntity> taskTypeEntities) {
        final Uri taskTypesUri = UriHelper.buildTaskTypeUri(context);
        final ContentValues[] taskTypesValues = new ContentValues[taskTypeEntities.size()];

        for(int i = 0; i < taskTypeEntities.size(); i++) {
            taskTypesValues[i] = taskTypeMapper.asContentValues(context, taskTypeEntities.get(i));
        }

        final int count = context.getContentResolver().bulkInsert(taskTypesUri, taskTypesValues);

        return (0 != count);
    }

    @Override
    public TaskTypeEntity get(@NonNull Context context, long id) {
        final Uri taskTypeUri = UriHelper.buildTaskTypeUriWithId(context, id);
        final Cursor taskTypeCursor = context.getContentResolver().query(taskTypeUri, null, null, null, null);

        return taskTypeMapper.asEntity(context, taskTypeCursor);
    }

    @Override
    public List<TaskTypeEntity> listAll(@NonNull Context context) {
        final Uri taskTypeUri = UriHelper.buildTaskTypeUri(context);
        final Cursor taskTypeCursor = context.getContentResolver().query(taskTypeUri, null, null, null, null);

        List<TaskTypeEntity> taskTypeEntities = new ArrayList<>();

        if(null != taskTypeCursor && taskTypeCursor.moveToFirst()) {
            for(int i = 0; i < taskTypeCursor.getCount(); i++) {
                taskTypeEntities.add(taskTypeMapper.asEntity(context, taskTypeCursor));
            }
        }

        return taskTypeEntities;
    }

    @Override
    public boolean update(@NonNull Context context, @NonNull TaskTypeEntity taskTypeEntity) {
        final Uri taskTypeUri = UriHelper.buildTaskTypeUri(context);
        final ContentValues taskTypeValues = taskTypeMapper.asContentValues(context, taskTypeEntity);
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
