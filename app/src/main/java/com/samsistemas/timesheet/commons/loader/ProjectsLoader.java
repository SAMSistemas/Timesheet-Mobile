package com.samsistemas.timesheet.commons.loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.samsistemas.timesheet.commons.controller.ControllerImpl;
import com.samsistemas.timesheet.commons.controller.base.Controller;
import com.samsistemas.timesheet.commons.model.Project;

import java.util.List;

/**
 * @author jonatan.salas
 */
public class ProjectsLoader extends AsyncTaskLoader<List<Project>> {
    private static final String LOG_TAG = ProjectsLoader.class.getSimpleName();
    private static final Class<Project> clazz = Project.class;
    private final Object lock = new Object();
    private Controller<Project> controller;

    public ProjectsLoader(Context context) {
        super(context);
        this.controller = new ControllerImpl<>();
    }

    @Override
    public List<Project> loadInBackground() {
        List<Project> projectList = null;

        if (controller.getCount(clazz) == 0) {
            try {
                synchronized (lock) {
                    lock.wait(3000);
                    projectList = controller.listAll(clazz);
                }
            } catch (InterruptedException ex) {
                Log.e(LOG_TAG, ex.getMessage(), ex.getCause());
            }
        } else {
            synchronized (lock) {
                projectList = controller.listAll(clazz);
                lock.notify();
            }
        }

        return projectList;
    }
}
