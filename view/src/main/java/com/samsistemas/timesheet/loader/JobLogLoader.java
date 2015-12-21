package com.samsistemas.timesheet.loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.samsistemas.timesheet.facade.JobLogFacade;
import com.samsistemas.timesheet.model.JobLog;

/**
 * @author jonatan.salas
 */
public class JobLogLoader extends AsyncTaskLoader<JobLog> {
    private final int mJobLogId;
    private final Context mContext;
    private final JobLogFacade mFacade;

    public JobLogLoader(Context context, int id) {
        super(context);
        this.mJobLogId = id;
        this.mFacade = JobLogFacade.newInstance();
        this.mContext = context;
    }

    @Override
    public JobLog loadInBackground() {
        final JobLog jobLog = mFacade.findById(mContext, mJobLogId);
        return (null != jobLog) ? jobLog : null;
    }
}
