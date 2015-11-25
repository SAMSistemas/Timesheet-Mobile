package com.samsistemas.timesheet.loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.samsistemas.timesheet.facade.JobLogFacade;
import com.samsistemas.timesheet.model.JobLog;

import java.util.List;

/**
 * @author jonatan.salas
 */
public class JobLogsLoader extends AsyncTaskLoader<List<JobLog>> {
    protected Context mContext;
    protected JobLogFacade mFacade;
    protected List<JobLog> mJobLogList;

    public JobLogsLoader(Context context) {
        super(context);
        this.mFacade = JobLogFacade.newInstance();
        this.mContext = context;
    }

    @Override
    public List<JobLog> loadInBackground() {
        mJobLogList = mFacade.findAll(mContext);
        return (null != mJobLogList && mJobLogList.size() > 0)? mJobLogList : null;
    }
}
