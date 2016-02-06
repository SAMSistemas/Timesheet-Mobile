package com.samsistemas.timesheet.data.loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.samsistemas.timesheet.data.domain.Project;

import java.util.List;

/**
 * @author jonatan.salas
 */
public class ProjectsLoader extends AsyncTaskLoader<List<Project>> {
    private static final String LOG_TAG = ProjectsLoader.class.getSimpleName();
    private static final Class<Project> clazz = Project.class;
    private final Object lock = new Object();

    public ProjectsLoader(Context context) {
        super(context);
    }

    @Override
    public List<Project> loadInBackground() {
        List<Project> projectList = null;

        if (Project.count(clazz) == 0) {
            try {
                synchronized (lock) {
                    lock.wait(3000);
                    projectList = Project.listAll(clazz);
                }
            } catch (InterruptedException ex) {
                Log.e(LOG_TAG, ex.getMessage(), ex.getCause());
            }
        } else {
            synchronized (lock) {
                projectList = Project.listAll(clazz);
                lock.notify();
            }
        }

        return projectList;
    }
}
