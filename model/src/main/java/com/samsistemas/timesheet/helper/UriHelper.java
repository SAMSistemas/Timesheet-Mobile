package com.samsistemas.timesheet.helper;

import android.content.ContentUris;
import android.content.Context;
import android.content.UriMatcher;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.samsistemas.timesheet.data.R;
import com.samsistemas.timesheet.provider.ContentUri;

public class UriHelper {

    private UriHelper() { }

    public static UriMatcher buildUriMatcher(@NonNull Context context) {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = context.getString(R.string.content_authority);

        //Uris for Clients Table..
        matcher.addURI(authority, context.getString(R.string.client_table), ContentUri.CLIENTS);
        matcher.addURI(authority, context.getString(R.string.client_table) + "/#", ContentUri.CLIENT_ID);

        //Uris for WorkPosition Table..
        matcher.addURI(authority, context.getString(R.string.work_position_table), ContentUri.WORK_POSITION);
        matcher.addURI(authority, context.getString(R.string.work_position_table) + "/#", ContentUri.WORK_POSITION_ID);

        //Uris for Person Table..
        matcher.addURI(authority, context.getString(R.string.person_table), ContentUri.PERSONS);
        matcher.addURI(authority, context.getString(R.string.person_table) + "/#", ContentUri.PERSON_ID);

        //Uris for TaskType Table..
        matcher.addURI(authority, context.getString(R.string.task_type_table), ContentUri.TASK_TYPES);
        matcher.addURI(authority, context.getString(R.string.task_type_table) + "/#", ContentUri.TASKTYPE_ID);

        //Uris for Project Table..
        matcher.addURI(authority, context.getString(R.string.project_table), ContentUri.PROJECTS);
        matcher.addURI(authority, context.getString(R.string.project_table) + "/#", ContentUri.PROJECT_ID);

        //Uris for JobLog Table..
        matcher.addURI(authority, context.getString(R.string.job_log_table), ContentUri.JOB_LOGS);
        matcher.addURI(authority, context.getString(R.string.job_log_table) + "/#", ContentUri.JOBLOG_ID);

        return matcher;
    }

    public static Uri buildClientUri(@NonNull Context context) {
        return Uri.parse(context.getString(R.string.client_content_uri));
    }

    public static Uri buildClientUriWithId(@NonNull Context context, long id) {
        Uri contentUri = Uri.parse(context.getString(R.string.client_content_uri));
        return ContentUris.withAppendedId(contentUri, id);
    }

    public static Uri buildWorkPositionUri(@NonNull Context context) {
        return Uri.parse(context.getString(R.string.work_position_content_uri));
    }

    public static Uri buildWorkPositionUriWithId(@NonNull Context context, long id) {
        Uri contentUri = Uri.parse(context.getString(R.string.work_position_content_uri));
        return ContentUris.withAppendedId(contentUri, id);
    }

    public static Uri buildPersonUri(@NonNull Context context) {
        return Uri.parse(context.getString(R.string.person_content_uri));
    }

    public static Uri buildPersonUriWithId(@NonNull Context context, long id) {
        Uri contentUri = Uri.parse(context.getString(R.string.person_content_uri));
        return ContentUris.withAppendedId(contentUri, id);
    }

    public static Uri buildTaskTypeUri(@NonNull Context context) {
        return Uri.parse(context.getString(R.string.task_type_content_uri));
    }

    public static Uri buildTaskTypeUriWithId(@NonNull Context context, long id) {
        Uri contentUri = Uri.parse(context.getString(R.string.task_type_content_uri));
        return ContentUris.withAppendedId(contentUri, id);
    }

    public static Uri buildProjectUri(@NonNull Context context) {
        return Uri.parse(context.getString(R.string.project_content_uri));
    }

    public static Uri buildProjectUriWithId(@NonNull Context context, long id) {
        Uri contentUri = Uri.parse(context.getString(R.string.project_content_uri));
        return ContentUris.withAppendedId(contentUri, id);
    }

    public static Uri buildJobLogUri(@NonNull Context context) {
        return Uri.parse(context.getString(R.string.job_log_content_uri));
    }

    public static Uri buildJobLogUriWithId(@NonNull Context context, long id) {
        Uri contentUri = Uri.parse(context.getString(R.string.job_log_content_uri));
        return ContentUris.withAppendedId(contentUri, id);
    }
}
