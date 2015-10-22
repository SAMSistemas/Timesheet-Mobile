package com.samsistemas.timesheet.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;

import com.samsistemas.timesheet.data.R;
import com.samsistemas.timesheet.database.DatabaseHelper;
import com.samsistemas.timesheet.helper.UriHelper;

/**
 * The content provider used to effectuate CRUD operations in the app.
 *
 * @author jonatan.salas
 */
public class DataProvider extends ContentProvider implements ContentUri {
    private DatabaseHelper mDatabaseHelper;
    private UriMatcher mUriMatcher;
    private Context mContext;

    @Override
    public boolean onCreate() {
        mContext = getContext();

        if(null != mContext) {
            mDatabaseHelper = new DatabaseHelper(mContext);
            mUriMatcher = UriHelper.buildUriMatcher(mContext);
        }

        return (null != mDatabaseHelper);
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        final SQLiteDatabase database = mDatabaseHelper.getReadableDatabase();
        Cursor retCursor;

        switch(mUriMatcher.match(uri)) {
            case CLIENTS:
                retCursor = database.query(
                        mContext.getString(R.string.client_table),
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            case CLIENT_ID:
                retCursor = database.query(
                        mContext.getString(R.string.client_table),
                        projection,
                        mContext.getString(R.string.client_id) + " = '" + ContentUris.parseId(uri) + "'",
                        null,
                        null,
                        null,
                        sortOrder
                );
                break;
            case PERSONS:
                retCursor = database.query(
                        mContext.getString(R.string.person_table),
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            case PERSON_ID:
                retCursor = database.query(
                        mContext.getString(R.string.person_table),
                        projection,
                        mContext.getString(R.string.person_id) + " = '" + ContentUris.parseId(uri) + "'",
                        null,
                        null,
                        null,
                        sortOrder
                );
                break;
            case TASK_TYPES:
                retCursor = database.query(
                        mContext.getString(R.string.task_type_table),
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            case TASKTYPE_ID:
                retCursor = database.query(
                        mContext.getString(R.string.task_type_table),
                        projection,
                        mContext.getString(R.string.task_type_id) + " = '" + ContentUris.parseId(uri) + "'",
                        null,
                        null,
                        null,
                        sortOrder
                );
                break;
            case PROJECTS:
                retCursor = database.query(
                        mContext.getString(R.string.project_table),
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            case PROJECT_ID:
                retCursor = database.query(
                        mContext.getString(R.string.project_table),
                        projection,
                        mContext.getString(R.string.project_id) + " = '" + ContentUris.parseId(uri) + "'",
                        null,
                        null,
                        null,
                        sortOrder
                );
                break;
            case JOB_LOGS:
                retCursor = database.query(
                        mContext.getString(R.string.job_log_table),
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            case JOBLOG_ID:
                retCursor = database.query(
                        mContext.getString(R.string.job_log_table),
                        projection,
                        mContext.getString(R.string.job_log_id) + " = '" + ContentUris.parseId(uri) + "'",
                        null,
                        null,
                        null,
                        sortOrder
                );
                break;
            default:
                throw new UnsupportedOperationException("unknown uri: " + uri);
        }

        retCursor.setNotificationUri(mContext.getContentResolver(), uri);

        return retCursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (mUriMatcher.match(uri)) {
            case CLIENTS:
                return mContext.getString(R.string.client_content_type);
            case CLIENT_ID:
                return mContext.getString(R.string.client_content_item_type);
            case PERSONS:
                return mContext.getString(R.string.person_content_type);
            case PERSON_ID:
                return mContext.getString(R.string.person_content_item_type);
            case TASK_TYPES:
                return mContext.getString(R.string.task_type_content_type);
            case TASKTYPE_ID:
                return mContext.getString(R.string.task_type_content_item_type);
            case PROJECTS:
                return mContext.getString(R.string.project_content_type);
            case PROJECT_ID:
                return mContext.getString(R.string.project_content_item_type);
            case JOB_LOGS:
                return mContext.getString(R.string.job_log_content_type);
            case JOBLOG_ID:
                return mContext.getString(R.string.job_log_content_item_type);
            default:
                throw new UnsupportedOperationException("unknown uri: " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, ContentValues values) {
        final SQLiteDatabase database = mDatabaseHelper.getWritableDatabase();
        Uri returnUri;

        switch(mUriMatcher.match(uri)) {
            case CLIENTS:
                long id = database.insert(
                        mContext.getString(R.string.client_table),
                        null,
                        values
                );

                if(id > 0) {
                    Uri contentUri = Uri.parse(mContext.getString(R.string.client_content_uri));
                    returnUri = ContentUris.withAppendedId(contentUri, id);
                } else {
                    throw new SQLException("failed to insert row in Uri: " + uri);
                }

                break;
            case PERSONS:
                id = database.insert(
                        mContext.getString(R.string.person_table),
                        null,
                        values
                );

                if(id > 0) {
                    Uri contentUri = Uri.parse(mContext.getString(R.string.person_content_uri));
                    returnUri = ContentUris.withAppendedId(contentUri, id);
                } else {
                    throw new SQLException("failed to insert row in Uri: " + uri);
                }

                break;
            case TASK_TYPES:
                id = database.insert(
                        mContext.getString(R.string.task_type_table),
                        null,
                        values
                );

                if(id > 0) {
                    Uri contentUri = Uri.parse(mContext.getString(R.string.task_type_content_uri));
                    returnUri = ContentUris.withAppendedId(contentUri, id);
                } else {
                    throw new SQLException("failed to insert row in Uri: " + uri);
                }

                break;
            case PROJECTS:
                id = database.insert(
                        mContext.getString(R.string.project_table),
                        null,
                        values
                );

                if(id > 0) {
                    Uri contentUri = Uri.parse(mContext.getString(R.string.project_content_uri));
                    returnUri = ContentUris.withAppendedId(contentUri, id);
                } else {
                    throw new SQLException("failed to insert row in Uri: " + uri);
                }

                break;
            case JOB_LOGS:
                id = database.insert(
                        mContext.getString(R.string.job_log_table),
                        null,
                        values
                );

                if(id > 0) {
                    Uri contentUri = Uri.parse(mContext.getString(R.string.job_log_content_uri));
                    returnUri = ContentUris.withAppendedId(contentUri, id);
                } else {
                    throw new SQLException("failed to insert row in Uri: " + uri);
                }

                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        mContext.getContentResolver().notifyChange(uri, null);

        return returnUri;
    }

    @Override
    public int bulkInsert(@NonNull Uri uri, @NonNull ContentValues[] values) {

        switch(mUriMatcher.match(uri)) {
            case CLIENTS:
                return bulkInsert(uri, values, R.string.client_table);
            case PERSONS:
                return bulkInsert(uri, values, R.string.person_table);
            case TASK_TYPES:
                return bulkInsert(uri, values, R.string.task_type_table);
            case PROJECTS:
                return bulkInsert(uri, values, R.string.project_table);
            case JOB_LOGS:
                return bulkInsert(uri, values, R.string.job_log_table);

            default: return super.bulkInsert(uri, values);
        }
    }

    /***
     * Method that inserts a big quantity of ContentValues inside a SQLite database.
     *
     * @param uri - the ure used with the content resolver.
     * @param values - the ContentValues array to insert.
     * @param tableName - the table name as a Resource id.
     * @return an int representing the count of inserted rows.
     */
    protected int bulkInsert(@NonNull Uri uri, @NonNull ContentValues[] values, @StringRes int tableName) {
        SQLiteDatabase database = mDatabaseHelper.getWritableDatabase();

        database.beginTransaction();
        int returnCount = 0;

        try {
            for(ContentValues value: values) {
                long id = database.insert(
                        mContext.getString(tableName),
                        null,
                        value
                );

                if(-1 != id) {
                    returnCount++;
                }
            }
            database.setTransactionSuccessful();
        } finally {
            database.endTransaction();
        }

        mContext.getContentResolver().notifyChange(uri, null);

        return returnCount;
    }

    @Override
    public int update(@NonNull Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        final SQLiteDatabase database = mDatabaseHelper.getWritableDatabase();
        int updatedRows;

        switch(mUriMatcher.match(uri)) {
            case CLIENTS:
                updatedRows = database.update(
                        mContext.getString(R.string.client_table),
                        values,
                        selection,
                        selectionArgs
                );
                break;
            case PERSONS:
                updatedRows = database.update(
                        mContext.getString(R.string.person_table),
                        values,
                        selection,
                        selectionArgs
                );
                break;
            case TASK_TYPES:
                updatedRows = database.update(
                        mContext.getString(R.string.task_type_table),
                        values,
                        selection,
                        selectionArgs
                );
                break;
            case PROJECTS:
                updatedRows = database.update(
                        mContext.getString(R.string.project_table),
                        values,
                        selection,
                        selectionArgs
                );
                break;
            case JOB_LOGS:
                updatedRows = database.update(
                        mContext.getString(R.string.job_log_table),
                        values,
                        selection,
                        selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        if(0 != updatedRows) mContext.getContentResolver().notifyChange(uri, null);

        return updatedRows;
    }

    @Override
    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {
        final SQLiteDatabase database = mDatabaseHelper.getWritableDatabase();
        int deletedRows;

        switch(mUriMatcher.match(uri)) {
            case CLIENTS:
                deletedRows = database.delete(
                        mContext.getString(R.string.client_table),
                        selection,
                        selectionArgs
                );
                break;
            case PERSONS:
                deletedRows = database.delete(
                        mContext.getString(R.string.person_table),
                        selection,
                        selectionArgs
                );
                break;
            case TASK_TYPES:
                deletedRows = database.delete(
                        mContext.getString(R.string.task_type_table),
                        selection,
                        selectionArgs
                );
                break;
            case PROJECTS:
                deletedRows = database.delete(
                        mContext.getString(R.string.project_table),
                        selection,
                        selectionArgs
                );
                break;
            case JOB_LOGS:
                deletedRows = database.delete(
                        mContext.getString(R.string.job_log_table),
                        selection,
                        selectionArgs
                );
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        //if selection is null, database.delete() method deletes all rows.
        if(null == selection || 0 != deletedRows) mContext.getContentResolver().notifyChange(uri, null);

        return deletedRows;
    }
}
