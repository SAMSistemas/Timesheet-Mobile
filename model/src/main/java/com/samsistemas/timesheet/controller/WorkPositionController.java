package com.samsistemas.timesheet.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.samsistemas.timesheet.controller.base.BaseController;
import com.samsistemas.timesheet.data.R;
import com.samsistemas.timesheet.helper.UriHelper;
import com.samsistemas.timesheet.model.WorkPosition;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jonatan.salas
 */
public class WorkPositionController implements BaseController<WorkPosition> {

    @Override
    public boolean insert(@NonNull Context context, @NonNull WorkPosition workPosition) {
        final Uri workPositionUri = UriHelper.buildWorkPositionUri(context);
        final ContentValues workPositionValues = workPosition.asContentValues(context);

        final Uri resultUri = context.getContentResolver().insert(workPositionUri, workPositionValues);

        return (null != resultUri);
    }

    @Override
    public boolean bulkInsert(@NonNull Context context, @NonNull List<WorkPosition> workPositions) {
        final Uri workPositionUri = UriHelper.buildWorkPositionUri(context);
        final ContentValues[] workPositionsValues = new ContentValues[workPositions.size()];

        for(int i = 0; i < workPositions.size(); i++) {
            workPositionsValues[i] = workPositions.get(i).asContentValues(context);
        }

        final int count = context.getContentResolver().bulkInsert(workPositionUri, workPositionsValues);

        return (0 != count);
    }

    @Override
    public WorkPosition get(@NonNull Context context, long id) {
        final Uri workPositionUri = UriHelper.buildWorkPositionUriWithId(context, id);
        final Cursor workPositionCursor = context.getContentResolver().query(workPositionUri, null, null, null, null);

        return new WorkPosition().save(context, workPositionCursor);
    }

    @Override
    public List<WorkPosition> listAll(@NonNull Context context) {
        final Uri workPositionUri = UriHelper.buildWorkPositionUri(context);
        final Cursor workPositionCursor = context.getContentResolver().query(workPositionUri, null, null, null, null);

        List<WorkPosition> workPositions = new ArrayList<>();

        if(null != workPositionCursor && workPositionCursor.moveToFirst()) {
            for(int i = 0; i < workPositionCursor.getCount(); i++) {
                workPositions.add(new WorkPosition().save(context, workPositionCursor));
            }

            if(!workPositionCursor.isClosed())
                workPositionCursor.close();
        }

        return workPositions;
    }

    @Override
    public boolean update(@NonNull Context context, @NonNull WorkPosition workPosition) {
        final Uri workPositionUri = UriHelper.buildWorkPositionUri(context);
        final ContentValues workPositionValues = workPosition.asContentValues(context);
        final String whereClause = context.getString(R.string.work_position_id) + " =? ";
        final String[] whereArgs = new String[] { String.valueOf(workPosition.getWorkPositionId()) };

        int updatedRows = context.getContentResolver().update(workPositionUri, workPositionValues, whereClause, whereArgs);

        return (0 != updatedRows);
    }

    @Override
    public boolean delete(@NonNull Context context, long id) {
        final Uri workPositionUri = UriHelper.buildWorkPositionUri(context);
        final String selection = context.getString(R.string.work_position_id) + " =? ";
        final String[] selectionArgs = new String[] { String.valueOf(id) };

        int deletedRows = context.getContentResolver().delete(workPositionUri, selection, selectionArgs);

        return (0 != deletedRows);
    }
}
