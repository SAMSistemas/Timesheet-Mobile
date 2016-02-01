package com.samsistemas.timesheet.loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.samsistemas.timesheet.controllers.ControllerImpl;
import com.samsistemas.timesheet.controllers.base.Controller;
import com.samsistemas.timesheet.model.JobLog;

/**
 * @author jonatan.salas
 */
public class JobLogLoader extends AsyncTaskLoader<JobLog> {
    private static final String LOG_TAG = JobLogLoader.class.getSimpleName();
    private static final Class<JobLog> clazz = JobLog.class;
    private final Object lock = new Object();
    private Controller<JobLog> controller;
    private final long id;

    public JobLogLoader(Context context, long id) {
        super(context);
        this.id = id;
        this.controller = new ControllerImpl<>();
    }

    @Override
    public JobLog loadInBackground() {
        JobLog jobLog = null;

        if (controller.getCount(clazz) == 0) {
            try {
                synchronized (lock) {
                    lock.wait(3000);
                    jobLog = controller.findById(clazz, id);
                }
            } catch (InterruptedException ex) {
                Log.e(LOG_TAG, ex.getMessage(), ex.getCause());
            }
        } else {
            synchronized (lock) {
                jobLog = controller.findById(clazz, id);
                lock.notify();
            }
        }

        return jobLog;
    }
}
