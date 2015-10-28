package com.samsistemas.timesheet.adapter;

import android.test.AndroidTestCase;
import android.util.Log;

import com.samsistemas.timesheet.controller.base.BaseController;
import com.samsistemas.timesheet.factory.ControllerFactory;
import com.samsistemas.timesheet.model.Client;
import com.samsistemas.timesheet.model.JobLog;
import com.samsistemas.timesheet.model.Person;
import com.samsistemas.timesheet.model.Project;
import com.samsistemas.timesheet.model.TaskForPosition;
import com.samsistemas.timesheet.model.TaskType;
import com.samsistemas.timesheet.model.WorkPosition;
import com.samsistemas.timesheet.util.TestUtilities;
import com.samsistemas.timesheet.viewmodel.TaskTypeViewModel;

import java.util.List;

/**
 * @author jonatan.salas
 */
public class TestAdapter extends AndroidTestCase {
    private static BaseController<Client> clientBaseController = ControllerFactory.getClientController();
    private static BaseController<WorkPosition> workPositionBaseController = ControllerFactory.getWorkPositionController();
    private static BaseController<Person> personBaseController = ControllerFactory.getPersonController();
    private static BaseController<TaskType> taskTypeBaseController = ControllerFactory.getTaskTypeController();
    private static BaseController<TaskForPosition> taskForPositionBaseController = ControllerFactory.getTaskForPositionController();
    private static BaseController<Project> projectBaseController = ControllerFactory.getProjectController();
    private static BaseController<JobLog> jobLogBaseController = ControllerFactory.getJobLogController();

    public void TestAdapterRead() {
        boolean inserted = personBaseController.insert(mContext, TestUtilities.getPerson());
        assertEquals(true, inserted);

        inserted = workPositionBaseController.insert(mContext, TestUtilities.getWorkPosition());
        assertEquals(true, inserted);

        inserted = taskTypeBaseController.bulkInsert(mContext, TestUtilities.getTaskTypeList());
        assertEquals(true, inserted);

        inserted = taskForPositionBaseController.bulkInsert(mContext, TestUtilities.getTaskForPositions());
        assertEquals(true, inserted);

        ModelAdapter modelAdapter = new ModelAdapter(mContext);

        List<TaskTypeViewModel> taskTypeViewModels = modelAdapter.getTaskForWorKPosition();
        assertEquals(TestUtilities.getTaskTypeList().size(), taskTypeViewModels.size());
        compareTasksList(TestUtilities.getTaskTypeList(), taskTypeViewModels);
    }

    private void compareTasksList(List<TaskType> expectedList, List<TaskTypeViewModel> list) {
        for(int i = 0; i < expectedList.size(); i++) {
            assertEquals(expectedList.get(i).getTaskTypeId(), list.get(i).getTaskTypeId());
            Log.d("test", "ID Esperado: " + expectedList.get(i).getTaskTypeId() + ", ID Resultante: " + list.get(i).getTaskTypeId());
            assertEquals(expectedList.get(i).getName(), list.get(i).getTaskTypeName());
            Log.d("test", "NOMBRE Esperado: " + expectedList.get(i).getName() + ", NOMBRE Resultante: " + list.get(i).getTaskTypeName());
        }
    }
}
