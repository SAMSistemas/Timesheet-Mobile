package com.samsistemas.timesheet.helper;

import android.content.ContentUris;
import android.content.Context;
import android.content.UriMatcher;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.samsistemas.timesheet.data.R;
import com.samsistemas.timesheet.provider.ContentUri;

/**
 * @author jonatan.salas
 */
public class UriHelper implements ContentUri {

    /**
     *
     * @param context
     * @return
     */
    public static UriMatcher buildUriMatcher(@NonNull Context context) {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = context.getString(R.string.content_authority);

        //Uris for Clients Table..
        matcher.addURI(authority, context.getString(R.string.client_table), CLIENTS);
        matcher.addURI(authority, context.getString(R.string.client_table) + "/#", CLIENT_ID);

        //Uris for WorkPosition Table..
        matcher.addURI(authority, context.getString(R.string.work_position_table), WORK_POSITION);
        matcher.addURI(authority, context.getString(R.string.work_position_table) + "/#", WORK_POSITION_ID);

        //Uris for Person Table..
        matcher.addURI(authority, context.getString(R.string.person_table), PERSONS);
        matcher.addURI(authority, context.getString(R.string.person_table) + "/#", PERSON_ID);

        //Uris for TaskType Table..
        matcher.addURI(authority, context.getString(R.string.task_type_table), TASK_TYPES);
        matcher.addURI(authority, context.getString(R.string.task_type_table) + "/#", TASKTYPE_ID);

        //Uris for Project Table..
        matcher.addURI(authority, context.getString(R.string.project_table), PROJECTS);
        matcher.addURI(authority, context.getString(R.string.project_table) + "/#", PROJECT_ID);

        //Uris for JobLog Table..
        matcher.addURI(authority, context.getString(R.string.job_log_table), JOB_LOGS);
        matcher.addURI(authority, context.getString(R.string.job_log_table) + "/#", JOBLOG_ID);

        return matcher;
    }

    /**
     *
     * @param context
     * @return
     */
    public static Uri buildClientUri(@NonNull Context context) {
        return Uri.parse(context.getString(R.string.client_content_uri));
    }

    /**
     *
     * @param context
     * @param id
     * @return
     */
    public static Uri buildClientUriWithId(@NonNull Context context, long id) {
        Uri contentUri = Uri.parse(context.getString(R.string.client_content_uri));
        return ContentUris.withAppendedId(contentUri, id);
    }

    /**
     *
     * @param context
     * @return
     */
    public static Uri buildWorkPositionUri(@NonNull Context context) {
        return Uri.parse(context.getString(R.string.work_position_content_uri));
    }

    /**
     *
     * @param context
     * @param id
     * @return
     */
    public static Uri buildWorkPositionUriWithId(@NonNull Context context, long id) {
        Uri contentUri = Uri.parse(context.getString(R.string.work_position_content_uri));
        return ContentUris.withAppendedId(contentUri, id);
    }

    /**
     *
     * @param context
     * @return
     */
    public static Uri buildPersonUri(@NonNull Context context) {
        return Uri.parse(context.getString(R.string.person_content_uri));
    }

    /**
     *
     * @param context
     * @param id
     * @return
     */
    public static Uri buildPersonUriWithId(@NonNull Context context, long id) {
        Uri contentUri = Uri.parse(context.getString(R.string.person_content_uri));
        return ContentUris.withAppendedId(contentUri, id);
    }

    /**
     *
     * @param context
     * @return
     */
    public static Uri buildTaskTypeUri(@NonNull Context context) {
        return Uri.parse(context.getString(R.string.task_type_content_uri));
    }

    /**
     *
     * @param context
     * @param id
     * @return
     */
    public static Uri buildTaskTypeUriWithId(@NonNull Context context, long id) {
        Uri contentUri = Uri.parse(context.getString(R.string.task_type_content_uri));
        return ContentUris.withAppendedId(contentUri, id);
    }

    /**
     *
     * @param context
     * @return
     */
    public static Uri buildProjectUri(@NonNull Context context) {
        return Uri.parse(context.getString(R.string.project_content_uri));
    }

    /**
     *
     * @param context
     * @param id
     * @return
     */
    public static Uri buildProjectUriWithId(@NonNull Context context, long id) {
        Uri contentUri = Uri.parse(context.getString(R.string.project_content_uri));
        return ContentUris.withAppendedId(contentUri, id);
    }

    /**
     *
     * @param context
     * @return
     */
    public static Uri buildJobLogUri(@NonNull Context context) {
        return Uri.parse(context.getString(R.string.job_log_content_uri));
    }

    /**
     *
     * @param context
     * @param id
     * @return
     */
    public static Uri buildJobLogUriWithId(@NonNull Context context, long id) {
        Uri contentUri = Uri.parse(context.getString(R.string.job_log_content_uri));
        return ContentUris.withAppendedId(contentUri, id);
    }
}
