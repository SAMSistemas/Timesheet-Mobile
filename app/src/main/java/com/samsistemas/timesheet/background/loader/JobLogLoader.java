package com.samsistemas.timesheet.background.loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.samsistemas.timesheet.domain.JobLog;

/**
 * @author jonatan.salas
 */
public class JobLogLoader extends AsyncTaskLoader<JobLog> {
    private static final String LOG_TAG = JobLogLoader.class.getSimpleName();
    private static final Class<JobLog> clazz = JobLog.class;
    private final Object lock = new Object();
    private final long id;

    public JobLogLoader(Context context, long id) {
        super(context);
        this.id = id;
    }

    @Override
    public JobLog loadInBackground() {
        JobLog jobLog = null;

        if (JobLog.count(clazz) == 0) {
            try {
                synchronized (lock) {
                    lock.wait(3000);
                    jobLog = JobLog.findById(clazz, id);
                }
            } catch (InterruptedException ex) {
                Log.e(LOG_TAG, ex.getMessage(), ex.getCause());
            }
        } else {
            synchronized (lock) {
                jobLog = JobLog.findById(clazz, id);
                lock.notify();
            }
        }

        return jobLog;
    }
}
