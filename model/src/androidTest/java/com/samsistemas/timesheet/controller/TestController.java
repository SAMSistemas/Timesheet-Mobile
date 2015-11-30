package com.samsistemas.timesheet.controller;

import android.test.AndroidTestCase;

import com.samsistemas.timesheet.data.R;
import com.samsistemas.timesheet.factory.ControllerFactory;
import com.samsistemas.timesheet.entity.ClientEntity;
import com.samsistemas.timesheet.entity.JobLogEntity;
import com.samsistemas.timesheet.entity.PersonEntity;
import com.samsistemas.timesheet.entity.ProjectEntity;
import com.samsistemas.timesheet.entity.TaskTypeEntity;
import com.samsistemas.timesheet.entity.WorkPositionEntity;
import com.samsistemas.timesheet.helper.UriHelper;
import com.samsistemas.timesheet.util.AssertUtilities;
import com.samsistemas.timesheet.util.TestUtilities;

import java.util.List;

/**
 * @author jonatan.salas
 */
public class TestController extends AndroidTestCase {
    private static final Controller<ClientEntity> clientBaseController = ControllerFactory.getClientController();
    private static final Controller<WorkPositionEntity> workPositionBaseController = ControllerFactory.getWorkPositionController();
    private static final Controller<PersonEntity> personBaseController = ControllerFactory.getPersonController();
    private static final Controller<TaskTypeEntity> taskTypeBaseController = ControllerFactory.getTaskTypeController();
    private static final Controller<ProjectEntity> projectBaseController = ControllerFactory.getProjectController();
    private static final Controller<JobLogEntity> jobLogBaseController = ControllerFactory.getJobLogController();

    private static final ClientEntity expectedClientEntity = TestUtilities.getClient();
    private static final WorkPositionEntity expectedWorkPositionEntity = TestUtilities.getWorkPosition();
    private static final PersonEntity expectedPersonEntity = TestUtilities.getPerson(expectedWorkPositionEntity.getId());
    private static final TaskTypeEntity expectedTaskTypeEntity = TestUtilities.getTaskType();
    private static final ProjectEntity expectedProjectEntity = TestUtilities.getProject(expectedClientEntity.getId());
    private static final JobLogEntity expectedJobLogEntity = TestUtilities.getJobLog(expectedProjectEntity.getId(), expectedPersonEntity.getId(), expectedTaskTypeEntity.getId());

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
        boolean inserted = clientBaseController.insert(mContext, expectedClientEntity, UriHelper.buildClientUri(mContext));
        assertEquals(true, inserted);

        inserted = workPositionBaseController.insert(mContext, expectedWorkPositionEntity, UriHelper.buildWorkPositionUri(mContext));
        assertEquals(true, inserted);

        inserted = personBaseController.insert(mContext, expectedPersonEntity, UriHelper.buildPersonUri(mContext));
        assertEquals(true, inserted);

        inserted = taskTypeBaseController.insert(mContext, expectedTaskTypeEntity, UriHelper.buildTaskTypeUri(mContext));
        assertEquals(true, inserted);

        inserted = projectBaseController.insert(mContext, expectedProjectEntity, UriHelper.buildProjectUri(mContext));
        assertEquals(true, inserted);

        inserted = jobLogBaseController.insert(mContext, expectedJobLogEntity, UriHelper.buildJobLogUri(mContext));
        assertEquals(true, inserted);

        //-------------------------------------------------------------------------//
        //                      CONTROLLER READ TEST PART                          //
        //-------------------------------------------------------------------------//
        ClientEntity clientEntity = clientBaseController.get(mContext, UriHelper.buildClientUriWithId(mContext, expectedClientEntity.getId()));
        AssertUtilities.compareClient(expectedClientEntity, clientEntity);

        WorkPositionEntity workPositionEntity = workPositionBaseController.get(mContext, UriHelper.buildWorkPositionUriWithId(mContext, expectedWorkPositionEntity.getId()));
        AssertUtilities.compareWorkPosition(expectedWorkPositionEntity, workPositionEntity);

        PersonEntity personEntity = personBaseController.get(mContext, UriHelper.buildPersonUriWithId(mContext, expectedPersonEntity.getId()));
        AssertUtilities.comparePerson(expectedPersonEntity, personEntity);

        TaskTypeEntity taskTypeEntity = taskTypeBaseController.get(mContext, UriHelper.buildTaskTypeUriWithId(mContext, expectedTaskTypeEntity.getId()));
        AssertUtilities.compareTaskType(expectedTaskTypeEntity, taskTypeEntity);

        ProjectEntity projectEntity = projectBaseController.get(mContext, UriHelper.buildProjectUriWithId(mContext, expectedProjectEntity.getId()));
        AssertUtilities.compareProject(expectedProjectEntity, projectEntity);

        JobLogEntity jobLogEntity = jobLogBaseController.get(mContext, UriHelper.buildJobLogUriWithId(mContext, expectedJobLogEntity.getId()));
        AssertUtilities.compareJobLog(expectedJobLogEntity, jobLogEntity);

        //-------------------------------------------------------------------------//
        //                      CONTROLLER LIST TEST PART                          //
        //-------------------------------------------------------------------------//
        List<ClientEntity> clientEntityList = clientBaseController.listAll(mContext, UriHelper.buildClientUri(mContext));
        assertEquals(false, clientEntityList.isEmpty());

        List<WorkPositionEntity> workPositionEntityList = workPositionBaseController.listAll(mContext, UriHelper.buildWorkPositionUri(mContext));
        assertEquals(false, workPositionEntityList.isEmpty());

        List<PersonEntity> personEntityList = personBaseController.listAll(mContext, UriHelper.buildPersonUri(mContext));
        assertEquals(false, personEntityList.isEmpty());

        List<TaskTypeEntity> taskTypeEntityList = taskTypeBaseController.listAll(mContext, UriHelper.buildTaskTypeUri(mContext));
        assertEquals(false, taskTypeEntityList.isEmpty());

        List<ProjectEntity> projectEntityList = projectBaseController.listAll(mContext, UriHelper.buildProjectUri(mContext));
        assertEquals(false, projectEntityList.isEmpty());

        List<JobLogEntity> jobLogEntityList = jobLogBaseController.listAll(mContext, UriHelper.buildJobLogUri(mContext));
        assertEquals(false, jobLogEntityList.isEmpty());

        //-------------------------------------------------------------------------//
        //                      CONTROLLER DELETE TEST PART                       //
        //-------------------------------------------------------------------------//
        boolean deleted = clientBaseController.delete(mContext, UriHelper.buildClientUri(mContext), expectedClientEntity.getId());
        assertEquals(true, deleted);

        deleted = workPositionBaseController.delete(mContext, UriHelper.buildWorkPositionUri(mContext), expectedWorkPositionEntity.getId());
        assertEquals(true, deleted);

        deleted = personBaseController.delete(mContext, UriHelper.buildPersonUri(mContext), expectedPersonEntity.getId());
        assertEquals(true, deleted);

        deleted = taskTypeBaseController.delete(mContext, UriHelper.buildTaskTypeUri(mContext), expectedTaskTypeEntity.getId());
        assertEquals(true, deleted);

        deleted = projectBaseController.delete(mContext, UriHelper.buildProjectUri(mContext), expectedProjectEntity.getId());
        assertEquals(true, deleted);

        deleted = jobLogBaseController.delete(mContext, UriHelper.buildJobLogUri(mContext), expectedJobLogEntity.getId());
        assertEquals(true, deleted);
    }
}
