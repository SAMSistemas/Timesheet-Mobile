package com.samsistemas.timesheet.factory;

import com.samsistemas.timesheet.controller.Controller;
import com.samsistemas.timesheet.controller.SessionController;
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
    private static Controller<ClientEntity> clientController = null;
    private static Controller<JobLogEntity> jobLogController = null;
    private static Controller<PersonEntity> personController = null;
    private static Controller<ProjectEntity> projectController = null;
    private static Controller<TaskTypeEntity> taskTypeController = null;
    private static Controller<WorkPositionEntity> workPositionController = null;
    private static BaseSessionController<SessionEntity> sessionController = null;

    private ControllerFactory() { }

    /**
     * Method that gets a singleton instance.
     *
     * @return a singleton object.
     */
    public static synchronized Controller<ClientEntity> getClientController() {
        if (null == clientController) {
            clientController = new Controller<>(MapperFactory.getClientMapper());
        }
        return clientController;
    }

    /**
     * Method that gets a singleton instance.
     *
     * @return a singleton object.
     */
    public static synchronized Controller<JobLogEntity> getJobLogController() {
        if (null == jobLogController) {
            jobLogController = new Controller<>(MapperFactory.getJoblogMapper());
        }
        return jobLogController;
    }

    /**
     * Method that gets a singleton instance.
     *
     * @return a singleton object.
     */
    public static synchronized Controller<PersonEntity> getPersonController() {
        if (null == personController) {
            personController = new Controller<>(MapperFactory.getPersonMapper());
        }
        return personController;
    }

    /**
     * Method that gets a singleton instance.
     *
     * @return a singleton object.
     */
    public static synchronized Controller<ProjectEntity> getProjectController() {
        if (null == projectController) {
            projectController = new Controller<>(MapperFactory.getProjectMapper());
        }
        return projectController;
    }

    /**
     * Method that gets a singleton instance.
     *
     * @return a singleton object.
     */
    public static synchronized Controller<TaskTypeEntity> getTaskTypeController() {
        if (null == taskTypeController) {
            taskTypeController = new Controller<>(MapperFactory.getTaskTypeMapper());
        }
        return taskTypeController;
    }

    /**
     *
     * @return
     */
    public static synchronized Controller<WorkPositionEntity> getWorkPositionController() {
        if (null == workPositionController) {
            workPositionController = new Controller<>(MapperFactory.getWorkPositionMapper());
        }
        return workPositionController;
    }

    /**
     * Method that gets a singleton instance.
     *
     * @return a singleton object.
     */
    public static synchronized BaseSessionController<SessionEntity> getSessionController() {
        if (null == sessionController) {
            sessionController = new SessionController();
        }
        return sessionController;
    }
}
