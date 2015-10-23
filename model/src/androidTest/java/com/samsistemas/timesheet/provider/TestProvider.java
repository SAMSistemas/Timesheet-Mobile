package com.samsistemas.timesheet.provider;

import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.test.AndroidTestCase;
import android.util.Log;

import com.samsistemas.timesheet.util.TestUtilities;
import com.samsistemas.timesheet.data.R;
import com.samsistemas.timesheet.helper.UriHelper;

import java.util.Map;
import java.util.Set;

/**
 * @author jonatan.salas
 */
public class TestProvider extends AndroidTestCase {
    private static final String LOG_TAG = TestProvider.class.getSimpleName();

    /**
     *
     * @throws Throwable
     */
    public void testDeleteDb() throws Throwable {
        mContext.deleteDatabase(mContext.getString(R.string.database_name));
    }

    /**
     *
     */
    public void testGetType() {
        //Type for Table Client..
        String type = mContext.getContentResolver().getType(UriHelper.buildClientUri(mContext));
        assertEquals(mContext.getString(R.string.client_content_type), type);

        type = mContext.getContentResolver().getType(UriHelper.buildClientUriWithId(mContext, 1L));
        assertEquals(mContext.getString(R.string.client_content_item_type), type);

        //Type for Table WorkPosition..
        type = mContext.getContentResolver().getType(UriHelper.buildWorkPositionUri(mContext));
        assertEquals(mContext.getString(R.string.work_position_content_type), type);

        type = mContext.getContentResolver().getType(UriHelper.buildWorkPositionUriWithId(mContext, 1L));
        assertEquals(mContext.getString(R.string.work_position_content_item_type), type);

        //Type for Table Person..
        type = mContext.getContentResolver().getType(UriHelper.buildPersonUri(mContext));
        assertEquals(mContext.getString(R.string.person_content_type), type);

        type = mContext.getContentResolver().getType(UriHelper.buildPersonUriWithId(mContext, 1L));
        assertEquals(mContext.getString(R.string.person_content_item_type), type);

        //Type for Table TaskType..
        type = mContext.getContentResolver().getType(UriHelper.buildTaskTypeUri(mContext));
        assertEquals(mContext.getString(R.string.task_type_content_type), type);

        type = mContext.getContentResolver().getType(UriHelper.buildTaskTypeUriWithId(mContext, 1L));
        assertEquals(mContext.getString(R.string.task_type_content_item_type), type);

        //Type for Table TaskForPosition..
        type = mContext.getContentResolver().getType(UriHelper.buildTaskTypeWorkPositionUri(mContext));
        assertEquals(mContext.getString(R.string.task_type_x_work_position_content_type), type);

        type = mContext.getContentResolver().getType(UriHelper.buildTaskTypeWorkPositionUriWithId(mContext, 1L));
        assertEquals(mContext.getString(R.string.task_type_x_work_position_content_item_type), type);

        //Type for Table Project..
        type = mContext.getContentResolver().getType(UriHelper.buildProjectUri(mContext));
        assertEquals(mContext.getString(R.string.project_content_type), type);

        type = mContext.getContentResolver().getType(UriHelper.buildProjectUriWithId(mContext, 1L));
        assertEquals(mContext.getString(R.string.project_content_item_type), type);

        //Type for Table JobLog..
        type = mContext.getContentResolver().getType(UriHelper.buildJobLogUri(mContext));
        assertEquals(mContext.getString(R.string.job_log_content_type), type);

        type = mContext.getContentResolver().getType(UriHelper.buildJobLogUriWithId(mContext, 1L));
        assertEquals(mContext.getString(R.string.job_log_content_item_type), type);
    }

    /**
     *
     */
    public void testInsertReadDb() {
        //-------------------------------------------------------------------------//
        //                          INSERTS TEST PART                              //
        //-------------------------------------------------------------------------//
        //Test insert on Client Table..
        final ContentValues clientValues = TestUtilities.getClient(mContext);
        Uri clientUriId = mContext.getContentResolver().insert(UriHelper.buildClientUri(mContext), clientValues);

        long clientRowId = ContentUris.parseId(clientUriId);

        assertTrue(clientRowId != -1);
        Log.d(LOG_TAG, "New row id for client table: " + clientRowId);

        //-------------------------------------------------------------------------//
        //Test insert on WorkPosition Table..
        final ContentValues workPositionValues = TestUtilities.getWorkPosition(mContext);
        Uri workPositionUriId = mContext.getContentResolver().insert(UriHelper.buildWorkPositionUri(mContext), workPositionValues);

        long workPositionRowId = ContentUris.parseId(workPositionUriId);

        assertTrue(workPositionRowId != -1);
        Log.d(LOG_TAG, "New row id for WorkPosition table: " + workPositionRowId);

        //-------------------------------------------------------------------------//
        //Test insert on Person Table..
        final ContentValues personValues = TestUtilities.getPerson(mContext, workPositionRowId);
        Uri personUriId = mContext.getContentResolver().insert(UriHelper.buildPersonUri(mContext), personValues);

        long personRowId = ContentUris.parseId(personUriId);

        assertTrue(personRowId != -1);
        Log.d(LOG_TAG, "New row id for person table: " + personRowId);

        //-------------------------------------------------------------------------//
        //Test insert on TaskType Table..
        final ContentValues taskTypeValues = TestUtilities.getTaskType(mContext);
        Uri taskTypeUriId = mContext.getContentResolver().insert(UriHelper.buildTaskTypeUri(mContext), taskTypeValues);

        long taskTypeRowId = ContentUris.parseId(taskTypeUriId);

        assertTrue(taskTypeRowId != -1);
        Log.d(LOG_TAG, "New row id for task type table: " + taskTypeRowId);

        //--------------------------------------------------------------------------//
        //Test insert on TaskForPosition Table..
        final ContentValues taskForPositionValues = TestUtilities.getTaskForPosition(mContext);
        Uri taskForPositionUriId = mContext.getContentResolver().insert(UriHelper.buildTaskTypeWorkPositionUri(mContext), taskForPositionValues);

        long taskForPositionRowId = ContentUris.parseId(taskForPositionUriId);

        assertTrue(taskForPositionRowId != -1);
        Log.d(LOG_TAG, "New row id for tasktype for work position table: " + taskForPositionRowId);

        //--------------------------------------------------------------------------//
        //Test insert on Project Table..
        final ContentValues projectValues = TestUtilities.getProject(mContext, clientRowId);
        Uri projectUriId = mContext.getContentResolver().insert(UriHelper.buildProjectUri(mContext), projectValues);

        long projectRowId = ContentUris.parseId(projectUriId);

        assertTrue(projectRowId != -1);
        Log.d(LOG_TAG, "New row id for project table: " + projectRowId);

        //--------------------------------------------------------------------------//
        //Test insert on JobLog Table..
        final ContentValues jobLogValues = TestUtilities.getJobLog(mContext, projectRowId, personRowId, taskTypeRowId);
        Uri jobLogUriId = mContext.getContentResolver().insert(UriHelper.buildJobLogUri(mContext), jobLogValues);

        long jobLogRowId = ContentUris.parseId(jobLogUriId);

        assertTrue(jobLogRowId != -1);
        Log.d(LOG_TAG, "New row id for joblog table: " + jobLogRowId);

        //--------------------------------------------------------------------------//
        //                           QUERY TEST PART                                //
        //--------------------------------------------------------------------------//
        //Test query on Client Table..
        final Cursor clientCursor = mContext.getContentResolver().query(UriHelper.buildClientUri(mContext), null, null, null, null);

        if(null != clientCursor && clientCursor.moveToFirst()) {
            validateCursor(clientValues, clientCursor);

            //Test query on Work Position Table..
            final Cursor workPositionCursor = mContext.getContentResolver().query(UriHelper.buildWorkPositionUri(mContext), null, null, null, null);

            if(null != workPositionCursor && workPositionCursor.moveToFirst()) {
                validateCursor(workPositionValues, workPositionCursor);

                //Test query on Person Table..
                final Cursor personCursor = mContext.getContentResolver().query(UriHelper.buildPersonUri(mContext), null, null, null, null);

                if (null != personCursor && personCursor.moveToFirst()) {
                    validateCursor(personValues, personCursor);

                    //Test query on TaskType Table..
                    final Cursor taskTypeCursor = mContext.getContentResolver().query(UriHelper.buildTaskTypeUri(mContext), null, null, null, null);

                    if (null != taskTypeCursor && taskTypeCursor.moveToFirst()) {
                        validateCursor(taskTypeValues, taskTypeCursor);

                        //Test query on TaskType x WorkPosition Table..
                        final Cursor taskForPositionCursor = mContext.getContentResolver().query(UriHelper.buildTaskTypeWorkPositionUri(mContext), null, null, null, null);

                        if(null != taskForPositionCursor && taskForPositionCursor.moveToFirst()) {
                            validateCursor(taskForPositionValues, taskForPositionCursor);

                            //Test query on Project Table..
                            final Cursor projectCursor = mContext.getContentResolver().query(UriHelper.buildProjectUri(mContext), null, null, null, null);

                            if (null != projectCursor && projectCursor.moveToFirst()) {
                                validateCursor(projectValues, projectCursor);

                                //Test query on jobLog Table..
                                final Cursor jobLogCursor = mContext.getContentResolver().query(UriHelper.buildJobLogUri(mContext), null, null, null, null);

                                if (null != jobLogCursor && jobLogCursor.moveToFirst()) {
                                    validateCursor(jobLogValues, jobLogCursor);
                                } else {
                                    fail("No values returned :(");
                                }
                            } else {
                                fail("No values returned :(");
                            }
                        } else {
                            fail("No values returned :(");
                        }
                    } else {
                        fail("No values returned :(");
                    }
                } else {
                    fail("No values returned :(");
                }
            } else {
                fail("No values returned :(");
            }
        } else {
            fail("No values returned :(");
        }
    }

    /**
     *
     * @param expectedValues
     * @param valueCursor
     */
    public static void validateCursor(ContentValues expectedValues, Cursor valueCursor) {
        Set<Map.Entry<String, Object>> valueSet = expectedValues.valueSet();

        for(Map.Entry<String, Object> entry: valueSet) {

            String columnName = entry.getKey();
            int idx = valueCursor.getColumnIndex(columnName);

            assertFalse(-1 == idx);
            Object value = entry.getValue();

            //Use this check to avoid null pointer troubleshooting when trying to get
            //the picture from Person Table that could be null.
            if(null != value) {
                String expectedValue = entry.getValue().toString();
                assertEquals(expectedValue, valueCursor.getString(idx));
            } else {
                byte[] expected = (byte[]) entry.getValue();
                assertEquals(expected, valueCursor.getBlob(idx));
            }
        }
    }
}
