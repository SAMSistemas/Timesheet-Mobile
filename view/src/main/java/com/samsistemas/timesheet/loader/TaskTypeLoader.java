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
    protected Context mContext;
    protected TaskTypeFacade mFacade;
    protected List<TaskType> mTaskTypeList;

    public TaskTypeLoader(Context context) {
        super(context);
        this.mFacade = TaskTypeFacade.newInstance();
        this.mContext = context;
    }

    @Override
    public List<TaskType> loadInBackground() {
        mTaskTypeList = mFacade.findAll(mContext);
        return (null != mTaskTypeList && mTaskTypeList.size() > 0)? mTaskTypeList : null;
    }
}
