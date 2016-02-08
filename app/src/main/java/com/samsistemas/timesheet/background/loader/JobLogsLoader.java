package com.samsistemas.timesheet.background.loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.samsistemas.timesheet.domain.JobLog;

import java.util.List;

/**
 * @author jonatan.salas
 */
public class JobLogsLoader extends AsyncTaskLoader<List<JobLog>> {
    private static final String LOG_TAG = JobLogsLoader.class.getSimpleName();
    private static final Class<JobLog> clazz = JobLog.class;
    private final Object lock = new Object();

    public JobLogsLoader(Context context) {
        super(context);
    }

    @Override
    public List<JobLog> loadInBackground() {
        List<JobLog> jobLogList = null;

        if (JobLog.count(clazz) == 0) {
            try {
                synchronized (lock) {
                    lock.wait(3000);
                    jobLogList = JobLog.listAll(clazz);
                }
            } catch (InterruptedException ex) {
                Log.e(LOG_TAG, ex.getMessage(), ex.getCause());
            }
        } else {
            synchronized (lock) {
                jobLogList = JobLog.listAll(clazz);
                lock.notify();
            }
        }

        return jobLogList;
    }
}
