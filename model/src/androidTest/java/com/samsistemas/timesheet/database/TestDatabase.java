package com.samsistemas.timesheet.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import android.util.Log;

import com.samsistemas.timesheet.util.TestUtilities;
import com.samsistemas.timesheet.data.R;

/**
 * @author jonatan.salas
 */
public class TestDatabase extends AndroidTestCase {
    private static final String LOG_TAG = TestDatabase.class.getSimpleName();

    /**
     *
     * @throws Throwable
     */
    public void testCreateDb() throws Throwable {
        mContext.deleteDatabase(mContext.getString(R.string.database_name));
        SQLiteDatabase db = new Database(mContext).getWritableDatabase();
        assertEquals(true, db.isOpen());
        db.close();
    }

    /**
     *
     */
    public void testInsertReadDb() {
        final Database databaseHelper = new Database(mContext);
        final SQLiteDatabase database = databaseHelper.getWritableDatabase();

        //-------------------------------------------------------------------------//
        //                          INSERTS TEST PART                              //
        //-------------------------------------------------------------------------//
        //Test insert on Client Table..
        final ContentValues clientValues = TestUtilities.getClientValues();
        long clientRowId = database.insert(mContext.getString(R.string.client_table), null, clientValues);

        assertTrue(clientRowId != -1);
        Log.d(LOG_TAG, "New row id for client table: " + clientRowId);


        //-------------------------------------------------------------------------//
        //Test insert on WorkPosition Table..
        final ContentValues workPositionValues = TestUtilities.getWorkPositionValues();
        long workPositionRowId = database.insert(mContext.getString(R.string.work_position_table), null, workPositionValues);

        assertTrue(workPositionRowId != -1);
        Log.d(LOG_TAG, "New row id for WorkPosition table: " + workPositionRowId);

        //-------------------------------------------------------------------------//
        //Test insert on Person Table..
        final ContentValues personValues = TestUtilities.getPersonValues(workPositionRowId);
        long personRowId = database.insert(mContext.getString(R.string.person_table), null, personValues);

        assertTrue(personRowId != -1);
        Log.d(LOG_TAG, "New row id for person table: " + personRowId);

        //-------------------------------------------------------------------------//
        //Test insert on TaskType Table..
        final ContentValues taskTypeValues = TestUtilities.getTaskTypeValues();
        long taskTypeRowId = database.insert(mContext.getString(R.string.task_type_table), null, taskTypeValues);

        assertTrue(taskTypeRowId != -1);
        Log.d(LOG_TAG, "New row id for task type table: " + taskTypeRowId);
        //--------------------------------------------------------------------------//
        //Test insert on Project Table..
        final ContentValues projectValues = TestUtilities.getProjectValues(clientRowId);
        long projectRowId = database.insert(mContext.getString(R.string.project_table), null, projectValues);

        assertTrue(projectRowId != -1);
        Log.d(LOG_TAG, "New row id for project table: " + projectRowId);

        //--------------------------------------------------------------------------//
        //Test insert on JobLog Table..
        final ContentValues jobLogValues = TestUtilities.getJobLogValues(projectRowId, personRowId, taskTypeRowId);
        long jobLogRowId = database.insert(mContext.getString(R.string.job_log_table), null, jobLogValues);

        assertTrue(jobLogRowId != -1);
        Log.d(LOG_TAG, "New row id for joblog table: " + jobLogRowId);

        //--------------------------------------------------------------------------//
        //                           QUERY TEST PART                                //
        //--------------------------------------------------------------------------//
        //Test query on Client Table..
        final Cursor clientCursor = database.query(mContext.getString(R.string.client_table), null, null, null, null, null, null);

        if (null != clientCursor && clientCursor.moveToFirst()) {
            TestUtilities.validateCursor(clientValues, clientCursor);

            //Test query on Work Position Table..
            final Cursor workPositionCursor = database.query(mContext.getString(R.string.work_position_table), null, null, null, null, null, null);

            if (null != workPositionCursor && workPositionCursor.moveToFirst()) {
                TestUtilities.validateCursor(workPositionValues, workPositionCursor);

                //Test query on Person Table..
                final Cursor personCursor = database.query(mContext.getString(R.string.person_table), null, null, null, null, null, null);

                if (null != personCursor && personCursor.moveToFirst()) {
                    TestUtilities.validateCursor(personValues, personCursor);

                    //Test query on TaskType Table..
                    final Cursor taskTypeCursor = database.query(mContext.getString(R.string.task_type_table), null, null, null, null, null, null);

                    if (null != taskTypeCursor && taskTypeCursor.moveToFirst()) {
                        TestUtilities.validateCursor(taskTypeValues, taskTypeCursor);

                        //Test query on Project Table..
                        final Cursor projectCursor = database.query(mContext.getString(R.string.project_table), null, null, null, null, null, null);

                        if (null != projectCursor && projectCursor.moveToFirst()) {
                            TestUtilities.validateCursor(projectValues, projectCursor);

                            //Test query on jobLog Table..
                            final Cursor jobLogCursor = database.query(mContext.getString(R.string.job_log_table), null, null, null, null, null, null);

                            if (null != jobLogCursor && jobLogCursor.moveToFirst()) {
                                TestUtilities.validateCursor(jobLogValues, jobLogCursor);
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
}
