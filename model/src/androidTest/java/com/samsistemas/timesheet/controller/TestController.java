package com.samsistemas.timesheet.controller;

import android.test.AndroidTestCase;

import com.samsistemas.timesheet.controller.base.BaseController;
import com.samsistemas.timesheet.data.R;
import com.samsistemas.timesheet.factory.ControllerFactory;
import com.samsistemas.timesheet.model.Client;
import com.samsistemas.timesheet.model.JobLog;
import com.samsistemas.timesheet.model.Person;
import com.samsistemas.timesheet.model.Project;
import com.samsistemas.timesheet.model.TaskForPosition;
import com.samsistemas.timesheet.model.TaskType;
import com.samsistemas.timesheet.model.WorkPosition;
import com.samsistemas.timesheet.util.AssertUtilities;
import com.samsistemas.timesheet.util.TestUtilities;

import java.util.List;

/**
 * @author jonatan.salas
 */
public class TestController extends AndroidTestCase {
    private static BaseController<Client> clientBaseController = ControllerFactory.getClientController();
    private static BaseController<WorkPosition> workPositionBaseController = ControllerFactory.getWorkPositionController();
    private static BaseController<Person> personBaseController = ControllerFactory.getPersonController();
    private static BaseController<TaskType> taskTypeBaseController = ControllerFactory.getTaskTypeController();
    private static BaseController<TaskForPosition> taskForPositionBaseController = ControllerFactory.getTaskForPositionController();
    private static BaseController<Project> projectBaseController = ControllerFactory.getProjectController();
    private static BaseController<JobLog> jobLogBaseController = ControllerFactory.getJobLogController();

    private static Client expectedClient = TestUtilities.getClient();
    private static WorkPosition expectedWorkPosition = TestUtilities.getWorkPosition();
    private static Person expectedPerson = TestUtilities.getPerson(expectedWorkPosition.getWorkPositionId());
    private static TaskType expectedTaskType = TestUtilities.getTaskType();
    private static TaskForPosition expectedTaskForPosition = TestUtilities.getTaskForPosition();
    private static Project expectedProject = TestUtilities.getProject(expectedClient.getClientId());
    private static JobLog expectedJobLog = TestUtilities.getJobLog(expectedProject.getProjectId(), expectedPerson.getPersonId(), expectedTaskType.getTaskTypeId());


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
        boolean inserted = clientBaseController.insert(mContext, expectedClient);
        assertEquals(true, inserted);

        inserted = workPositionBaseController.insert(mContext, expectedWorkPosition);
        assertEquals(true, inserted);

        inserted = personBaseController.insert(mContext, expectedPerson);
        assertEquals(true, inserted);

        inserted = taskTypeBaseController.insert(mContext, expectedTaskType);
        assertEquals(true, inserted);

        inserted = taskForPositionBaseController.insert(mContext, expectedTaskForPosition);
        assertEquals(true, inserted);

        inserted = projectBaseController.insert(mContext, expectedProject);
        assertEquals(true, inserted);

        inserted = jobLogBaseController.insert(mContext, expectedJobLog);
        assertEquals(true, inserted);

        //-------------------------------------------------------------------------//
        //                      CONTROLLER READ TEST PART                          //
        //-------------------------------------------------------------------------//
        Client client = clientBaseController.get(mContext, 1);
        AssertUtilities.compareClient(expectedClient, client);

        WorkPosition workPosition = workPositionBaseController.get(mContext, expectedWorkPosition.getWorkPositionId());
        AssertUtilities.compareWorkPosition(expectedWorkPosition, workPosition);

        Person person = personBaseController.get(mContext, expectedPerson.getPersonId());
        AssertUtilities.comparePerson(expectedPerson, person);

        TaskType taskType = taskTypeBaseController.get(mContext, expectedTaskType.getTaskTypeId());
        AssertUtilities.compareTaskType(expectedTaskType, taskType);

        TaskForPosition taskForPosition = taskForPositionBaseController.get(mContext, expectedTaskForPosition.getTaskTypeId());
        AssertUtilities.compareTaskForPosition(expectedTaskForPosition, taskForPosition);

        Project project = projectBaseController.get(mContext, expectedProject.getProjectId());
        AssertUtilities.compareProject(expectedProject, project);

        JobLog jobLog = jobLogBaseController.get(mContext, expectedJobLog.getJobLogId());
        AssertUtilities.compareJobLog(expectedJobLog, jobLog);

        //-------------------------------------------------------------------------//
        //                      CONTROLLER LIST TEST PART                          //
        //-------------------------------------------------------------------------//
        List<Client> clientList = clientBaseController.listAll(mContext);
        assertEquals(false, clientList.isEmpty());

        List<WorkPosition> workPositionList = workPositionBaseController.listAll(mContext);
        assertEquals(false, workPositionList.isEmpty());

        List<Person> personList = personBaseController.listAll(mContext);
        assertEquals(false, personList.isEmpty());

        List<TaskType> taskTypeList = taskTypeBaseController.listAll(mContext);
        assertEquals(false, taskTypeList.isEmpty());

        List<TaskForPosition> taskForPositionList = taskForPositionBaseController.listAll(mContext);
        assertEquals(false, taskForPositionList.isEmpty());

        List<Project> projectList = projectBaseController.listAll(mContext);
        assertEquals(false, projectList.isEmpty());

        List<JobLog> jobLogList = jobLogBaseController.listAll(mContext);
        assertEquals(false, jobLogList.isEmpty());

        //-------------------------------------------------------------------------//
        //                      CONTROLLER DELETE TEST PART                       //
        //-------------------------------------------------------------------------//
        boolean deleted = clientBaseController.delete(mContext, expectedClient.getClientId());
        assertEquals(true, deleted);

        deleted = workPositionBaseController.delete(mContext, expectedWorkPosition.getWorkPositionId());
        assertEquals(true, deleted);

        deleted = personBaseController.delete(mContext, expectedPerson.getPersonId());
        assertEquals(true, deleted);

        deleted = taskTypeBaseController.delete(mContext, expectedTaskType.getTaskTypeId());
        assertEquals(true, deleted);

        deleted = taskForPositionBaseController.delete(mContext, expectedTaskForPosition.getTaskTypeId());
        assertEquals(true, deleted);

        deleted = projectBaseController.delete(mContext, expectedProject.getProjectId());
        assertEquals(true, deleted);

        deleted = jobLogBaseController.delete(mContext, expectedJobLog.getJobLogId());
        assertEquals(true, deleted);
    }
}
