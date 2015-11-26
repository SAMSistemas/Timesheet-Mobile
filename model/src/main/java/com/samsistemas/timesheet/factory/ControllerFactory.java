package com.samsistemas.timesheet.factory;

import com.samsistemas.timesheet.controller.ClientController;
import com.samsistemas.timesheet.controller.JobLogController;
import com.samsistemas.timesheet.controller.PersonController;
import com.samsistemas.timesheet.controller.ProjectController;
import com.samsistemas.timesheet.controller.SessionController;
import com.samsistemas.timesheet.controller.TaskTypeController;
import com.samsistemas.timesheet.controller.WorkPositionController;
import com.samsistemas.timesheet.controller.base.BaseController;
import com.samsistemas.timesheet.controller.base.BaseSessionController;

import com.samsistemas.timesheet.entity.ClientEntity;
import com.samsistemas.timesheet.entity.JobLogEntity;
import com.samsistemas.timesheet.entity.PersonEntity;
import com.samsistemas.timesheet.entity.ProjectEntity;
import com.samsistemas.timesheet.entity.SessionEntity;
import com.samsistemas.timesheet.entity.TaskTypeEntity;
import com.samsistemas.timesheet.entity.WorkPositionEntity;

/**
 * Class used to get controller instances.
 *
 * @author jonatan.salas
 */
public class ControllerFactory {
    private static BaseController<ClientEntity> clientBaseController = null;
    private static BaseController<JobLogEntity> jobLogBaseController = null;
    private static BaseController<PersonEntity> personBaseController = null;
    private static BaseController<ProjectEntity> projectBaseController = null;
    private static BaseController<TaskTypeEntity> taskTypeBaseController = null;
    private static BaseController<WorkPositionEntity> workPositionBaseController = null;
    private static BaseSessionController<SessionEntity> sessionController = null;

    /**
     * Method that gets a singleton instance.
     *
     * @return a singleton object.
     */
    public static synchronized BaseController<ClientEntity> getClientController() {
        if(null == clientBaseController)
            clientBaseController = new ClientController();
        return clientBaseController;
    }

    /**
     * Method that gets a singleton instance.
     *
     * @return a singleton object.
     */
    public static synchronized BaseController<JobLogEntity> getJobLogController() {
        if(null == jobLogBaseController)
            jobLogBaseController = new JobLogController();
        return jobLogBaseController;
    }

    /**
     * Method that gets a singleton instance.
     *
     * @return a singleton object.
     */
    public static synchronized BaseController<PersonEntity> getPersonController() {
        if(null == personBaseController)
            personBaseController = new PersonController();
        return personBaseController;
    }

    /**
     * Method that gets a singleton instance.
     *
     * @return a singleton object.
     */
    public static synchronized BaseController<ProjectEntity> getProjectController() {
        if(null == projectBaseController)
            projectBaseController = new ProjectController();
        return projectBaseController;
    }

    /**
     * Method that gets a singleton instance.
     *
     * @return a singleton object.
     */
    public static synchronized BaseController<TaskTypeEntity> getTaskTypeController() {
        if(null == taskTypeBaseController)
            taskTypeBaseController = new TaskTypeController();
        return taskTypeBaseController;
    }

    /**
     *
     * @return
     */
    public static synchronized BaseController<WorkPositionEntity> getWorkPositionController() {
        if(null == workPositionBaseController)
            workPositionBaseController = new WorkPositionController();
        return workPositionBaseController;
    }

    /**
     * Method that gets a singleton instance.
     *
     * @return a singleton object.
     */
    public static synchronized BaseSessionController<SessionEntity> getSessionController() {
        if(null == sessionController)
            sessionController = new SessionController();
        return sessionController;
    }
}
