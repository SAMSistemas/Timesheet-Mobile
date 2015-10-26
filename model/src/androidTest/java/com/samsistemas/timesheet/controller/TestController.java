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
    public void testClientControllerInsertReadDb() {
        boolean inserted = clientBaseController.insert(mContext, TestUtilities.getClient());
        assertEquals(true, inserted);

        Client client = clientBaseController.get(mContext, 1);

        assertEquals(expectedClient.getClientId(), client.getClientId());
        assertEquals(expectedClient.getName(), client.getName());
        assertEquals(expectedClient.getShortName(), client.getShortName());
        assertEquals(expectedClient.isEnabled(), client.isEnabled());

        List<Client> clientList = clientBaseController.listAll(mContext);
        assertEquals(false, clientList.isEmpty());

        boolean deleted = clientBaseController.delete(mContext, client.getClientId());
        assertEquals(true, deleted);
    }

    /**
     *
     */
    public void testWorkPositionControllerInsertReadDb() {
        boolean inserted = workPositionBaseController.insert(mContext, expectedWorkPosition);
        assertEquals(true, inserted);

        WorkPosition workPosition = workPositionBaseController.get(mContext, 1);

        assertEquals(expectedWorkPosition.getWorkPositionId(), workPosition.getWorkPositionId());
        assertEquals(expectedWorkPosition.getDescription(), workPosition.getDescription());

        List<WorkPosition> workPositionList = workPositionBaseController.listAll(mContext);
        assertEquals(false, workPositionList.isEmpty());

        boolean deleted = workPositionBaseController.delete(mContext, workPosition.getWorkPositionId());
        assertEquals(true, deleted);
    }
}
