package com.samsistemas.timesheet.loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.samsistemas.timesheet.controller.ControllerImpl;
import com.samsistemas.timesheet.controller.base.Controller;
import com.samsistemas.timesheet.model.TaskType;

import java.util.List;

/**
 * @author jonatan.salas
 */
public class TaskTypeLoader extends AsyncTaskLoader<List<TaskType>> {
    private static final String LOG_TAG = TaskTypeLoader.class.getSimpleName();
    private static final Class<TaskType> clazz = TaskType.class;
    private final Object lock = new Object();
    private Controller<TaskType> controller;


    public TaskTypeLoader(Context context) {
        super(context);
        this.controller = new ControllerImpl<>();
    }

    @Override
    public List<TaskType> loadInBackground() {
        List<TaskType> taskTypeList = null;

        if (controller.getCount(clazz) == 0) {
            try {
                synchronized (lock) {
                    lock.wait(3000);
                    taskTypeList = controller.listAll(clazz);
                }
            } catch (InterruptedException ex) {
                Log.e(LOG_TAG, ex.getMessage(), ex.getCause());
            }
        } else {
            synchronized (lock) {
                taskTypeList = controller.listAll(clazz);
                lock.notify();
            }
        }

        return taskTypeList;
    }
}
