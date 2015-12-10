package com.samsistemas.timesheet.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.samsistemas.timesheet.controller.base.BaseController;
import com.samsistemas.timesheet.entity.base.Entity;
import com.samsistemas.timesheet.mapper.base.EntityMapper;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jonatan.salas
 * @param <T>
 */
public class Controller<T extends Entity> implements BaseController<T> {
    private static final String LOG_TAG = Controller.class.getSimpleName();
    private static final String CLAUSE = " id =? ";
    private final EntityMapper<T, Cursor> entityMapper;

    public Controller(EntityMapper<T, Cursor> mapper) {
        this.entityMapper = mapper;
    }

    @Override
    public boolean insert(@NonNull Context context, @NonNull T toInsert, @NonNull Uri uri) {
        final ContentValues values = entityMapper.asContentValues(toInsert);
        final Uri resultUri = context.getContentResolver().insert(uri, values);
        return (null != resultUri);
    }

    @Override
    public boolean bulkInsert(@NonNull Context context, @NonNull List<T> toInserts, @NonNull Uri uri) {
        final ContentValues[] values = new ContentValues[toInserts.size()];

        for (int i = 0; i < toInserts.size(); i++) {
            values[i] = entityMapper.asContentValues(toInserts.get(i));
        }

        int count = context.getContentResolver().bulkInsert(uri, values);

        return (count != 0);
    }

    @Override
    public T get(@NonNull Context context, @NonNull Uri uri) {
        Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);

        if (null != cursor) {
            cursor.moveToFirst();
        }

        final T entity = entityMapper.asEntity(cursor);

        if (null != cursor && !cursor.isClosed()) {
            cursor.close();
        }

        return entity;
    }

    @Override
    public List<T> listAll(@NonNull Context context, @NonNull Uri uri) {
        Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);
        List<T> entityList = new ArrayList<>();

        try {
            if (null != cursor && cursor.getCount() == 0) {
                return entityList;
            }

            if (null != cursor && cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {
                    entityList.add(entityMapper.asEntity(cursor));
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

    @Override
    public boolean update(@NonNull Context context, @NonNull T toUpdate, @NonNull Uri uri) {
        final ContentValues values = entityMapper.asContentValues(toUpdate);
        final String[] whereArgs = new String[] { String.valueOf(toUpdate.getId()) };

        int updatedRows = context.getContentResolver().update(uri, values, CLAUSE, whereArgs);

        return (0 != updatedRows);
    }

    @Override
    public boolean delete(@NonNull Context context, @NonNull Uri uri, long id) {
        int deletedRows = context.getContentResolver().delete(uri, CLAUSE, new String[] { String.valueOf(id) } );

        return (0 != deletedRows);
    }
}
