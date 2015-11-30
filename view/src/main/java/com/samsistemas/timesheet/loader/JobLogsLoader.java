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
    private final Context mContext;
    private final JobLogFacade mFacade;

    public JobLogsLoader(Context context) {
        super(context);
        this.mFacade = JobLogFacade.newInstance();
        this.mContext = context;
    }

    @Override
    public List<JobLog> loadInBackground() {
//        List<JobLog> jobLogList = mFacade.findAll(mContext);
//        return (null != jobLogList && jobLogList.size() > 0)? jobLogList : null;
        return null;
    }
}
