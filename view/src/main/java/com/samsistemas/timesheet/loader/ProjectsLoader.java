package com.samsistemas.timesheet.loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.samsistemas.timesheet.facade.ProjectFacade;
import com.samsistemas.timesheet.model.Project;

import java.util.List;

/**
 * @author jonatan.salas
 */
public class ProjectsLoader extends AsyncTaskLoader<List<Project>> {
    protected ProjectFacade mFacade;
    protected Context mContext;
    protected List<Project> mProjectList;

    public ProjectsLoader(Context context) {
        super(context);
        this.mFacade = ProjectFacade.newInstance();
        this.mContext = context;
    }

    @Override
    public List<Project> loadInBackground() {
        mProjectList = mFacade.findAll(mContext);
        return (null != mProjectList && mProjectList.size() > 0)? mProjectList : null;
    }
}
