package com.samsistemas.timesheet.helper;

import android.content.ContentUris;
import android.content.Context;
import android.content.UriMatcher;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.samsistemas.timesheet.data.R;
import com.samsistemas.timesheet.util.ContentUriKeys;

/**
 * Utility class used to build the Uri to point the tables of the ContentProvider
 *
 * @author jonatan.salas
 */
public final class UriHelper {

    /**
     * Private constructor.
     */
    private UriHelper() { }

    /**
     * Method that builds a UriMatcher to be used in the ContentProvider class when checking
     * the URIs
     *
     * @param context the application context used to retrieve the correct URIs located in string.xml file
     * @return a UriMatcher object ready to use
     */
    public static UriMatcher buildUriMatcher(@NonNull Context context) {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = context.getString(R.string.content_authority);

        //Uris for Clients Table..
        matcher.addURI(authority, context.getString(R.string.client_table), ContentUriKeys.CLIENTS);
        matcher.addURI(authority, context.getString(R.string.client_table) + "/#", ContentUriKeys.CLIENT_ID);

        //Uris for WorkPosition Table..
        matcher.addURI(authority, context.getString(R.string.work_position_table), ContentUriKeys.WORK_POSITION);
        matcher.addURI(
                authority,
                context.getString(R.string.work_position_table) + "/#",
                ContentUriKeys.WORK_POSITION_ID
        );

        //Uris for Person Table..
        matcher.addURI(authority, context.getString(R.string.person_table), ContentUriKeys.PERSONS);
        matcher.addURI(authority, context.getString(R.string.person_table) + "/#", ContentUriKeys.PERSON_ID);

        //Uris for TaskType Table..
        matcher.addURI(authority, context.getString(R.string.task_type_table), ContentUriKeys.TASK_TYPES);
        matcher.addURI(authority, context.getString(R.string.task_type_table) + "/#", ContentUriKeys.TASKTYPE_ID);

        //Uris for Project Table..
        matcher.addURI(authority, context.getString(R.string.project_table), ContentUriKeys.PROJECTS);
        matcher.addURI(authority, context.getString(R.string.project_table) + "/#", ContentUriKeys.PROJECT_ID);

        //Uris for JobLog Table..
        matcher.addURI(authority, context.getString(R.string.job_log_table), ContentUriKeys.JOB_LOGS);
        matcher.addURI(authority, context.getString(R.string.job_log_table) + "/#", ContentUriKeys.JOBLOG_ID);

        return matcher;
    }

    /**
     * Helper method to build the correct Client Uri
     *
     * @param context the application context used to retrieve the client content uri
     * @return a Uri object pointing to the Client table.
     */
    public static Uri buildClientUri(@NonNull Context context) {
        return Uri.parse(context.getString(R.string.client_content_uri));
    }

    /**
     * Helper method to build the correct Client uri with appended ID
     *
     * @param context the application context used to retrieve the client content uri
     * @param id the id to append
     * @return a Uri object pointing to the Client table, with the appended ID
     */
    public static Uri buildClientUriWithId(@NonNull Context context, long id) {
        Uri contentUri = Uri.parse(context.getString(R.string.client_content_uri));
        return ContentUris.withAppendedId(contentUri, id);
    }

    /**
     * Helper method to build the correct WorkPosition Uri
     * 
     * @param context the application context used to retrieve the work position content uri
     * @return a Uri object pointing to the WorkPosition table
     */
    public static Uri buildWorkPositionUri(@NonNull Context context) {
        return Uri.parse(context.getString(R.string.work_position_content_uri));
    }

    /**
     * Helper method to build the correct WorkPosition Uri with the appended ID
     *
     * @param context the application context used to retrieve the work position content uri
     * @param id the id to append
     * @return a Uri object pointing to the WorkPosition table with the appended ID
     */
    public static Uri buildWorkPositionUriWithId(@NonNull Context context, long id) {
        Uri contentUri = Uri.parse(context.getString(R.string.work_position_content_uri));
        return ContentUris.withAppendedId(contentUri, id);
    }

    /**
     * Helper method to build the correct Person Uri
     *
     * @param context the application context used to retrieve the person content uri
     * @return a Uri object pointing to the Person table
     */
    public static Uri buildPersonUri(@NonNull Context context) {
        return Uri.parse(context.getString(R.string.person_content_uri));
    }

    /**
     * Helper method to build the correct Person Uri with the appended ID
     *
     * @param context the application context used to retrieve the person content uri
     * @param id the id to append
     * @return a Uri object pointing to the Person table with the appended ID
     */
    public static Uri buildPersonUriWithId(@NonNull Context context, long id) {
        Uri contentUri = Uri.parse(context.getString(R.string.person_content_uri));
        return ContentUris.withAppendedId(contentUri, id);
    }

    /**
     * Helper method to build the correct TaskType Uri
     *
     * @param context the application context used to retrieve the tasktype content uri
     * @return a Uri object pointing to the TaskType table
     */
    public static Uri buildTaskTypeUri(@NonNull Context context) {
        return Uri.parse(context.getString(R.string.task_type_content_uri));
    }

    /**
     * Helper method to build the correct TaskType Uri with appended ID
     *
     * @param context the application context used to retrieve the tasktype content uri
     * @param id the id to append
     * @return a Uri object pointing to the TaskType table with the appended ID
     */
    public static Uri buildTaskTypeUriWithId(@NonNull Context context, long id) {
        Uri contentUri = Uri.parse(context.getString(R.string.task_type_content_uri));
        return ContentUris.withAppendedId(contentUri, id);
    }

    /**
     * Helper method to build the correct Project Uri
     *
     * @param context the application context used to retrieve the project content uri
     * @return a Uri object pointing to the Project table
     */
    public static Uri buildProjectUri(@NonNull Context context) {
        return Uri.parse(context.getString(R.string.project_content_uri));
    }

    /**
     * Helper method to build the correct Project Uri with appended ID
     *
     * @param context the application context used to retrieve the project content uri
     * @param id the id to append
     * @return a Uri object pointing to the Project table with the appended ID
     */
    public static Uri buildProjectUriWithId(@NonNull Context context, long id) {
        Uri contentUri = Uri.parse(context.getString(R.string.project_content_uri));
        return ContentUris.withAppendedId(contentUri, id);
    }

    /**
     * Helper method to build the correct JobLog Uri
     *
     * @param context the application context used to retrieve the joblog content uri
     * @return a Uri object pointing to the JobLog table
     */
    public static Uri buildJobLogUri(@NonNull Context context) {
        return Uri.parse(context.getString(R.string.job_log_content_uri));
    }

    /**
     * Helper method to build the correct JobLog Uri with appended ID
     *
     * @param context the application context used to retrieve the joblog content uri
     * @param id the id to append
     * @return a Uri object pointing to the JobLog table with the appended ID
     */
    public static Uri buildJobLogUriWithId(@NonNull Context context, long id) {
        Uri contentUri = Uri.parse(context.getString(R.string.job_log_content_uri));
        return ContentUris.withAppendedId(contentUri, id);
    }
}
