package com.samsistemas.timesheet.data.loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.samsistemas.timesheet.data.domain.TaskType;

import java.util.List;

/**
 * @author jonatan.salas
 */
public class TaskTypeLoader extends AsyncTaskLoader<List<TaskType>> {
    private static final String LOG_TAG = TaskTypeLoader.class.getSimpleName();
    private static final Class<TaskType> clazz = TaskType.class;
    private final Object lock = new Object();

    public TaskTypeLoader(Context context) {
        super(context);
    }

    @Override
    public List<TaskType> loadInBackground() {
        List<TaskType> taskTypeList = null;

        if (TaskType.count(clazz) == 0) {
            try {
                synchronized (lock) {
                    lock.wait(3000);
                    taskTypeList = TaskType.listAll(clazz);
                }
            } catch (InterruptedException ex) {
                Log.e(LOG_TAG, ex.getMessage(), ex.getCause());
            }
        } else {
            synchronized (lock) {
                taskTypeList = TaskType.listAll(clazz);
                lock.notify();
            }
        }

        return taskTypeList;
    }
}
