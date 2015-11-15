package com.samsistemas.timesheet.adapter;

import android.content.Context;

import com.samsistemas.timesheet.controller.base.BaseController;
import com.samsistemas.timesheet.factory.ControllerFactory;
import com.samsistemas.timesheet.entity.ClientEntity;
import com.samsistemas.timesheet.entity.JobLogEntity;
import com.samsistemas.timesheet.entity.PersonEntity;
import com.samsistemas.timesheet.entity.ProjectEntity;
import com.samsistemas.timesheet.entity.TaskTypeEntity;
import com.samsistemas.timesheet.entity.WorkPositionEntity;

import java.lang.ref.WeakReference;

/**
 * @author jonatan.salas
 */
public class ModelAdapter {
    private static BaseController<ClientEntity> clientBaseController = ControllerFactory.getClientController();
    private static BaseController<WorkPositionEntity> workPositionBaseController = ControllerFactory.getWorkPositionController();
    private static BaseController<PersonEntity> personBaseController = ControllerFactory.getPersonController();
    private static BaseController<TaskTypeEntity> taskTypeBaseController = ControllerFactory.getTaskTypeController();
    private static BaseController<ProjectEntity> projectBaseController = ControllerFactory.getProjectController();
    private static BaseController<JobLogEntity> jobLogBaseController = ControllerFactory.getJobLogController();

    private WeakReference<Context> mContextReference;

    //TODO JS: Modify.
}
