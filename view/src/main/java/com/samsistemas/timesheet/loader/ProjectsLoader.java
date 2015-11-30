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
    private final ProjectFacade mFacade;
    private final Context mContext;
    private List<Project> mProjectList;

    public ProjectsLoader(Context context) {
        super(context);
        this.mFacade = ProjectFacade.newInstance();
        this.mContext = context;
    }

    @Override
    public List<Project> loadInBackground() {
        List<Project> projectList = mFacade.findAll(mContext);
        return (null != projectList && projectList.size() > 0)? projectList : null;
    }
}
