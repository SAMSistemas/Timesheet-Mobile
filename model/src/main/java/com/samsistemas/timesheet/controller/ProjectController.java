package com.samsistemas.timesheet.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.samsistemas.timesheet.controller.base.BaseController;
import com.samsistemas.timesheet.data.R;
import com.samsistemas.timesheet.helper.UriHelper;
import com.samsistemas.timesheet.model.Project;

import java.util.ArrayList;
import java.util.List;

/**
 * Class the implements ProjectController interface.
 *
 * @author jonatan.salas
 */
public class ProjectController implements BaseController<Project> {

    @Override
    public boolean insert(@NonNull Context context, @NonNull Project project) {
        final Uri projectUri = UriHelper.buildProjectUri(context);
        final ContentValues projectValues = project.asContentValues(context);

        final Uri resultUri = context.getContentResolver().insert(projectUri, projectValues);

        return (null != resultUri);
    }

    @Override
    public boolean bulkInsert(@NonNull Context context, @NonNull List<Project> projects) {
        final Uri projectsUri = UriHelper.buildProjectUri(context);
        final ContentValues[] projectsValues = new ContentValues[projects.size()];

        for(int i = 0; i < projects.size(); i++) {
            projectsValues[i] = projects.get(i).asContentValues(context);
        }

        final int count = context.getContentResolver().bulkInsert(projectsUri, projectsValues);

        return (0 != count);
    }

    @Override
    public Project get(@NonNull Context context, long id) {
        final Uri projectUri = UriHelper.buildProjectUriWithId(context, id);
        final Cursor projectCursor = context.getContentResolver().query(projectUri, null, null, null, null);

        return new Project().save(context, projectCursor);
    }

    @Override
    public List<Project> listAll(@NonNull Context context) {
        final Uri projectsUri = UriHelper.buildProjectUri(context);
        final Cursor projectsCursor = context.getContentResolver().query(projectsUri, null, null, null, null);

        List<Project> projects = new ArrayList<>();

        if(null != projectsCursor && projectsCursor.moveToFirst()) {
            for(int i = 0; i < projectsCursor.getCount(); i++) {
                projects.add(new Project().save(context, projectsCursor));
            }
        }

        return projects;
    }

    @Override
    public boolean update(@NonNull Context context, @NonNull Project project) {
        final Uri projectUri = UriHelper.buildProjectUri(context);
        final ContentValues projectValues = project.asContentValues(context);
        final String whereClause = context.getString(R.string.project_id) + " =? ";
        final String[] whereArgs = new String[] { String.valueOf(project.getProjectId()) };

        int updatedRows = context.getContentResolver().update(projectUri, projectValues, whereClause, whereArgs);

        return (0 != updatedRows);
    }

    @Override
    public boolean delete(@NonNull Context context, long id) {
        final Uri projectUri = UriHelper.buildProjectUri(context);
        final String selection = context.getString(R.string.project_id) + " =? ";
        final String[] selectionArgs = new String[] { String.valueOf(id) };

        int deletedRows = context.getContentResolver().delete(projectUri, selection, selectionArgs);

        return (0 != deletedRows);
    }
}