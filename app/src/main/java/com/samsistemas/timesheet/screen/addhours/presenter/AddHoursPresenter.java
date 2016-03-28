package com.samsistemas.timesheet.screen.addhours.presenter;

import android.support.annotation.NonNull;

import com.jonisaa.commons.presenter.BasePresenter;
import com.samsistemas.timesheet.R;
import com.samsistemas.timesheet.domain.Client;
import com.samsistemas.timesheet.domain.JobLog;
import com.samsistemas.timesheet.domain.Project;
import com.samsistemas.timesheet.domain.TaskType;
import com.samsistemas.timesheet.screen.addhours.view.AddHoursView;

import java.util.List;

import static com.orm.SugarRecord.find;
import static com.samsistemas.timesheet.utility.ThreadUtility.runInBackground;
import static com.samsistemas.timesheet.utility.ThreadUtility.CallBack;
import static com.orm.SugarRecord.listAll;

/**
 * @author jonatan.salas
 */
public class AddHoursPresenter extends BasePresenter<AddHoursView> {
    private final CallBack<List<TaskType>> taskTypeCallBack = new CallBack<List<TaskType>>() {
        @Override
        public List<TaskType> execute() {
            return find(TaskType.class, null, null);
        }
    };

    private final CallBack<List<Client>> clientCallback = new CallBack<List<Client>>() {
        @Override
        public List<Client> execute() {
            return listAll(Client.class);
        }
    };

    private final CallBack<List<Project>> projectCallback = new CallBack<List<Project>>() {
        @Override
        public List<Project> execute() {
            return listAll(Project.class);
        }
    };

    private AddHoursPresenter(@NonNull AddHoursView view) {
        super(view);
    }

    public void showStyledActionBar() {
        if (null != getView()) {
            getView().styleBar();
        }
    }

    public void showHoursInSpinner() {
        if (null != getView()) {
            getView().loadHours(R.array.hours, android.R.layout.simple_spinner_item);
        }
    }

    public void showTaskTypesInSpinner() {
        if (null != getView()) {
            getView().loadTaskTypes(runInBackground(taskTypeCallBack));
        }
    }

    public void showClientsInSpinner() {
        if (null != getView()) {
            getView().loadClients(runInBackground(clientCallback));
        }
    }

    public void showProjectsInSpinner() {
        if (null != getView()) {
            getView().loadProjects(runInBackground(projectCallback));
        }
    }

    public boolean saveOrUpdateAsync(boolean isInEditMode, @NonNull final JobLog jobLog) {
        if (isInEditMode) {
            final Long id = runInBackground(new CallBack<Long>() {
                @Override
                public Long execute() {
                    return JobLog.update(jobLog);
                }
            });

            return id.equals(jobLog.getId());
        } else {
            runInBackground(new CallBack<Void>() {
                @Override
                public Void execute() {
                    JobLog.save(jobLog);
                    return null;
                }
            });

            return true;
        }
    }

    public static AddHoursPresenter getInstance(@NonNull AddHoursView view) {
        return new AddHoursPresenter(view);
    }
}
