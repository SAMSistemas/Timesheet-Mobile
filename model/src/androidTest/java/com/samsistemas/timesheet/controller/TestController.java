package com.samsistemas.timesheet.controller;

import android.test.AndroidTestCase;

import com.samsistemas.timesheet.controller.base.BaseController;
import com.samsistemas.timesheet.data.R;
import com.samsistemas.timesheet.factory.ControllerFactory;
import com.samsistemas.timesheet.entity.ClientEntity;
import com.samsistemas.timesheet.entity.JobLogEntity;
import com.samsistemas.timesheet.entity.PersonEntity;
import com.samsistemas.timesheet.entity.ProjectEntity;
import com.samsistemas.timesheet.entity.TaskTypeEntity;
import com.samsistemas.timesheet.entity.WorkPositionEntity;
import com.samsistemas.timesheet.util.AssertUtilities;
import com.samsistemas.timesheet.util.TestUtilities;

import java.util.List;

/**
 * @author jonatan.salas
 */
public class TestController extends AndroidTestCase {
    private static BaseController<ClientEntity> clientBaseController = ControllerFactory.getClientController();
    private static BaseController<WorkPositionEntity> workPositionBaseController = ControllerFactory.getWorkPositionController();
    private static BaseController<PersonEntity> personBaseController = ControllerFactory.getPersonController();
    private static BaseController<TaskTypeEntity> taskTypeBaseController = ControllerFactory.getTaskTypeController();
    private static BaseController<ProjectEntity> projectBaseController = ControllerFactory.getProjectController();
    private static BaseController<JobLogEntity> jobLogBaseController = ControllerFactory.getJobLogController();

    private static ClientEntity expectedClientEntity = TestUtilities.getClient();
    private static WorkPositionEntity expectedWorkPositionEntity = TestUtilities.getWorkPosition();
    private static PersonEntity expectedPersonEntity = TestUtilities.getPerson(expectedWorkPositionEntity.getWorkPositionId());
    private static TaskTypeEntity expectedTaskTypeEntity = TestUtilities.getTaskType();
    private static ProjectEntity expectedProjectEntity = TestUtilities.getProject(expectedClientEntity.getClientId());
    private static JobLogEntity expectedJobLogEntity = TestUtilities.getJobLog(expectedProjectEntity.getProjectId(), expectedPersonEntity.getPersonId(), expectedTaskTypeEntity.getTaskTypeId());


    public void testDeleteDb() {
        mContext.deleteDatabase(mContext.getString(R.string.database_name));
    }

    /**
     *
     */
    public void testControllersCRUD() {
        //-------------------------------------------------------------------------//
        //                      CONTROLLER INSERTS TEST PART                       //
        //-------------------------------------------------------------------------//
        boolean inserted = clientBaseController.insert(mContext, expectedClientEntity);
        assertEquals(true, inserted);

        inserted = workPositionBaseController.insert(mContext, expectedWorkPositionEntity);
        assertEquals(true, inserted);

        inserted = personBaseController.insert(mContext, expectedPersonEntity);
        assertEquals(true, inserted);

        inserted = taskTypeBaseController.insert(mContext, expectedTaskTypeEntity);
        assertEquals(true, inserted);

        inserted = projectBaseController.insert(mContext, expectedProjectEntity);
        assertEquals(true, inserted);

        inserted = jobLogBaseController.insert(mContext, expectedJobLogEntity);
        assertEquals(true, inserted);

        //-------------------------------------------------------------------------//
        //                      CONTROLLER READ TEST PART                          //
        //-------------------------------------------------------------------------//
        ClientEntity clientEntity = clientBaseController.get(mContext, 1);
        AssertUtilities.compareClient(expectedClientEntity, clientEntity);

        WorkPositionEntity workPositionEntity = workPositionBaseController.get(mContext, expectedWorkPositionEntity.getWorkPositionId());
        AssertUtilities.compareWorkPosition(expectedWorkPositionEntity, workPositionEntity);

        PersonEntity personEntity = personBaseController.get(mContext, expectedPersonEntity.getPersonId());
        AssertUtilities.comparePerson(expectedPersonEntity, personEntity);

        TaskTypeEntity taskTypeEntity = taskTypeBaseController.get(mContext, expectedTaskTypeEntity.getTaskTypeId());
        AssertUtilities.compareTaskType(expectedTaskTypeEntity, taskTypeEntity);

        ProjectEntity projectEntity = projectBaseController.get(mContext, expectedProjectEntity.getProjectId());
        AssertUtilities.compareProject(expectedProjectEntity, projectEntity);

        JobLogEntity jobLogEntity = jobLogBaseController.get(mContext, expectedJobLogEntity.getJobLogId());
        AssertUtilities.compareJobLog(expectedJobLogEntity, jobLogEntity);

        //-------------------------------------------------------------------------//
        //                      CONTROLLER LIST TEST PART                          //
        //-------------------------------------------------------------------------//
        List<ClientEntity> clientEntityList = clientBaseController.listAll(mContext);
        assertEquals(false, clientEntityList.isEmpty());

        List<WorkPositionEntity> workPositionEntityList = workPositionBaseController.listAll(mContext);
        assertEquals(false, workPositionEntityList.isEmpty());

        List<PersonEntity> personEntityList = personBaseController.listAll(mContext);
        assertEquals(false, personEntityList.isEmpty());

        List<TaskTypeEntity> taskTypeEntityList = taskTypeBaseController.listAll(mContext);
        assertEquals(false, taskTypeEntityList.isEmpty());

        List<ProjectEntity> projectEntityList = projectBaseController.listAll(mContext);
        assertEquals(false, projectEntityList.isEmpty());

        List<JobLogEntity> jobLogEntityList = jobLogBaseController.listAll(mContext);
        assertEquals(false, jobLogEntityList.isEmpty());

        //-------------------------------------------------------------------------//
        //                      CONTROLLER DELETE TEST PART                       //
        //-------------------------------------------------------------------------//
        boolean deleted = clientBaseController.delete(mContext, expectedClientEntity.getClientId());
        assertEquals(true, deleted);

        deleted = workPositionBaseController.delete(mContext, expectedWorkPositionEntity.getWorkPositionId());
        assertEquals(true, deleted);

        deleted = personBaseController.delete(mContext, expectedPersonEntity.getPersonId());
        assertEquals(true, deleted);

        deleted = taskTypeBaseController.delete(mContext, expectedTaskTypeEntity.getTaskTypeId());
        assertEquals(true, deleted);

        deleted = projectBaseController.delete(mContext, expectedProjectEntity.getProjectId());
        assertEquals(true, deleted);

        deleted = jobLogBaseController.delete(mContext, expectedJobLogEntity.getJobLogId());
        assertEquals(true, deleted);
    }
}
