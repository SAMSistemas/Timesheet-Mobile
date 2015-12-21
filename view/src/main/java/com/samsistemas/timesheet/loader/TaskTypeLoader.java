package com.samsistemas.timesheet.loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.samsistemas.timesheet.facade.TaskTypeFacade;
import com.samsistemas.timesheet.model.TaskType;

import java.util.List;

/**
 * @author jonatan.salas
 */
public class TaskTypeLoader extends AsyncTaskLoader<List<TaskType>> {
    private final Context mContext;
    private final TaskTypeFacade mFacade;

    public TaskTypeLoader(Context context) {
        super(context);
        this.mFacade = TaskTypeFacade.newInstance();
        this.mContext = context;
    }

    @Override
    public List<TaskType> loadInBackground() {
        List<TaskType> taskTypeList = mFacade.findAll(mContext);
        return (null != taskTypeList && taskTypeList.size() > 0)? taskTypeList : null;
    }
}
