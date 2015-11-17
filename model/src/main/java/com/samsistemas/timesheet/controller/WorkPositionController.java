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
import com.samsistemas.timesheet.entity.WorkPositionEntity;
import com.samsistemas.timesheet.mapper.base.EntityMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jonatan.salas
 */
public class WorkPositionController implements BaseController<WorkPositionEntity> {
    protected EntityMapper<WorkPositionEntity, Cursor> workPositionMapper;

    public WorkPositionController() {
        this.workPositionMapper = MapperFactory.getWorkPositionMapper();
    }

    @Override
    public boolean insert(@NonNull Context context, @NonNull WorkPositionEntity workPositionEntity) {
        final Uri workPositionUri = UriHelper.buildWorkPositionUri(context);
        final ContentValues workPositionValues = workPositionMapper.asContentValues(context, workPositionEntity);
        final WorkPositionEntity entity = get(context, workPositionEntity.getWorkPositionId());

        if (null != entity) {
            return false;
        } else {
            final Uri resultUri = context.getContentResolver().insert(workPositionUri, workPositionValues);
            return (null != resultUri);
        }
    }

    @Override
    public boolean bulkInsert(@NonNull Context context, @NonNull List<WorkPositionEntity> workPositionEntities) {
        final Uri workPositionUri = UriHelper.buildWorkPositionUri(context);
        final ContentValues[] workPositionsValues = new ContentValues[workPositionEntities.size()];

        for(int i = 0; i < workPositionEntities.size(); i++) {
            workPositionsValues[i] = workPositionMapper.asContentValues(context, workPositionEntities.get(i));
        }

        final int count = context.getContentResolver().bulkInsert(workPositionUri, workPositionsValues);

        return (0 != count);
    }

    @Override
    public WorkPositionEntity get(@NonNull Context context, long id) {
        final Uri workPositionUri = UriHelper.buildWorkPositionUriWithId(context, id);
        final Cursor workPositionCursor = context.getContentResolver().query(workPositionUri, null, null, null, null);

        return workPositionMapper.asEntity(context, workPositionCursor);
    }

    @Override
    public List<WorkPositionEntity> listAll(@NonNull Context context) {
        final Uri workPositionUri = UriHelper.buildWorkPositionUri(context);
        final Cursor workPositionCursor = context.getContentResolver().query(workPositionUri, null, null, null, null);

        List<WorkPositionEntity> workPositionEntities = new ArrayList<>();

        if(null != workPositionCursor && workPositionCursor.moveToFirst()) {
            for(int i = 0; i < workPositionCursor.getCount(); i++) {
                workPositionEntities.add(workPositionMapper.asEntity(context, workPositionCursor));
            }

            if(!workPositionCursor.isClosed())
                workPositionCursor.close();
        }

        return workPositionEntities;
    }

    @Override
    public boolean update(@NonNull Context context, @NonNull WorkPositionEntity workPositionEntity) {
        final Uri workPositionUri = UriHelper.buildWorkPositionUri(context);
        final ContentValues workPositionValues = workPositionMapper.asContentValues(context, workPositionEntity);
        final String whereClause = context.getString(R.string.work_position_id) + " =? ";
        final String[] whereArgs = new String[] { String.valueOf(workPositionEntity.getWorkPositionId()) };

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
