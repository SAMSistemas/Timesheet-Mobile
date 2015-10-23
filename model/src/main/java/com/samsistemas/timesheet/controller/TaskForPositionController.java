package com.samsistemas.timesheet.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.samsistemas.timesheet.controller.base.BaseController;
import com.samsistemas.timesheet.data.R;
import com.samsistemas.timesheet.helper.UriHelper;
import com.samsistemas.timesheet.model.TaskForPosition;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jonatan.salas
 */
public class TaskForPositionController implements BaseController<TaskForPosition> {

    @Override
    public boolean insert(@NonNull Context context, @NonNull TaskForPosition taskForPosition) {
        final Uri taskForPositionUri = UriHelper.buildTaskTypeWorkPositionUri(context);
        final ContentValues taskForPositionValues = taskForPosition.asContentValues(context);

        final Uri resultUri = context.getContentResolver().insert(taskForPositionUri, taskForPositionValues);

        return (null != resultUri);
    }

    @Override
    public boolean bulkInsert(@NonNull Context context, @NonNull List<TaskForPosition> taskForPositions) {
        final Uri taskForPositionUri = UriHelper.buildTaskTypeWorkPositionUri(context);
        final ContentValues[] taskForPositionsValues = new ContentValues[taskForPositions.size()];

        for(int i = 0; i < taskForPositions.size(); i++) {
            taskForPositionsValues[i] = taskForPositions.get(i).asContentValues(context);
        }

        final int count = context.getContentResolver().bulkInsert(taskForPositionUri, taskForPositionsValues);

        return (0 != count);
    }

    @Override
    public TaskForPosition get(@NonNull Context context, long id) {
        final Uri taskForPositionUri = UriHelper.buildTaskTypeWorkPositionUriWithId(context, id);
        final Cursor taskForPositionCursor = context.getContentResolver().query(taskForPositionUri, null, null, null, null);

        return new TaskForPosition().save(context, taskForPositionCursor);
    }

    @Override
    public List<TaskForPosition> listAll(@NonNull Context context) {
        final Uri taskForPositionUri = UriHelper.buildTaskTypeWorkPositionUri(context);
        final Cursor taskForPositionCursor = context.getContentResolver().query(taskForPositionUri, null, null, null, null);

        List<TaskForPosition> taskForPositions = new ArrayList<>();

        if(null != taskForPositionCursor && taskForPositionCursor.moveToFirst()) {
            for(int i = 0; i < taskForPositionCursor.getCount(); i++) {
                taskForPositions.add(new TaskForPosition().save(context, taskForPositionCursor));
            }

            if(!taskForPositionCursor.isClosed())
                taskForPositionCursor.close();
        }

        return taskForPositions;
    }

    @Override
    public boolean update(@NonNull Context context, @NonNull TaskForPosition taskForPosition) {
        final Uri taskForPositionUri = UriHelper.buildTaskTypeWorkPositionUri(context);
        final ContentValues taskForPositionValues = taskForPosition.asContentValues(context);
        final String whereClause = context.getString(R.string.task_type_x_work_position_id) + " =? ";
        final String[] whereArgs = new String[] { String.valueOf(taskForPosition.getWorkPositionId()) };

        int updatedRows = context.getContentResolver().update(taskForPositionUri, taskForPositionValues, whereClause, whereArgs);

        return (0 != updatedRows);
    }

    @Override
    public boolean delete(@NonNull Context context, long id) {
        final Uri taskForPositionUri = UriHelper.buildTaskTypeWorkPositionUri(context);
        final String selection = context.getString(R.string.task_type_x_work_position_id) + " =? ";
        final String[] selectionArgs = new String[] { String.valueOf(id) };

        int deletedRows = context.getContentResolver().delete(taskForPositionUri, selection, selectionArgs);

        return (0 != deletedRows);
    }
}
