package com.samsistemas.timesheet.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.samsistemas.timesheet.controller.base.BaseController;
import com.samsistemas.timesheet.data.R;
import com.samsistemas.timesheet.helper.UriHelper;
import com.samsistemas.timesheet.model.TaskType;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that implements the TaskTypeController interface.
 *
 * @author jonatan.salas
 */
public class TaskTypeController implements BaseController<TaskType> {

    @Override
    public boolean insert(@NonNull Context context, @NonNull TaskType taskType) {
        final Uri taskTypeUri = UriHelper.buildTaskTypeUri(context);
        final ContentValues taskTypeValues = taskType.asContentValues(context);

        final Uri resultUri = context.getContentResolver().insert(taskTypeUri, taskTypeValues);

        return (null != resultUri);
    }

    @Override
    public boolean bulkInsert(@NonNull Context context, @NonNull List<TaskType> taskTypes) {
        final Uri taskTypesUri = UriHelper.buildTaskTypeUri(context);
        final ContentValues[] taskTypesValues = new ContentValues[taskTypes.size()];

        for(int i = 0; i < taskTypes.size(); i++) {
            taskTypesValues[i] = taskTypes.get(i).asContentValues(context);
        }

        final int count = context.getContentResolver().bulkInsert(taskTypesUri, taskTypesValues);

        return (0 != count);
    }

    @Override
    public TaskType get(@NonNull Context context, long id) {
        final Uri taskTypeUri = UriHelper.buildTaskTypeUriWithId(context, id);
        final Cursor taskTypeCursor = context.getContentResolver().query(taskTypeUri, null, null, null, null);

        return new TaskType().save(context, taskTypeCursor);
    }

    @Override
    public List<TaskType> listAll(@NonNull Context context) {
        final Uri taskTypeUri = UriHelper.buildTaskTypeUri(context);
        final Cursor taskTypeCursor = context.getContentResolver().query(taskTypeUri, null, null, null, null);

        List<TaskType> taskTypes = new ArrayList<>();

        if(null != taskTypeCursor && taskTypeCursor.moveToFirst()) {
            for(int i = 0; i < taskTypeCursor.getCount(); i++) {
                taskTypes.add(new TaskType().save(context, taskTypeCursor));
            }
        }

        return taskTypes;
    }

    @Override
    public boolean update(@NonNull Context context, @NonNull TaskType taskType) {
        final Uri taskTypeUri = UriHelper.buildTaskTypeUri(context);
        final ContentValues taskTypeValues = taskType.asContentValues(context);
        final String whereClause = context.getString(R.string.task_type_id) + " =? ";
        final String[] whereArgs = new String[] { String.valueOf(taskType.getTaskTypeId()) };

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
