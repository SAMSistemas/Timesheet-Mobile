package com.samsistemas.timesheet.controller;

import android.test.AndroidTestCase;

import com.samsistemas.timesheet.controller.base.BaseController;
import com.samsistemas.timesheet.data.R;
import com.samsistemas.timesheet.factory.ControllerFactory;
import com.samsistemas.timesheet.model.Client;
import com.samsistemas.timesheet.util.TestUtilities;

import java.util.List;

/**
 * @author jonatan.salas
 */
public class TestController extends AndroidTestCase {

    public void testDeleteDb() {
        mContext.deleteDatabase(mContext.getString(R.string.database_name));
    }

    /**
     *
     */
    public void testClientControllerInsertReadDb() {
        BaseController<Client> clientBaseController = ControllerFactory.getClientController();

        boolean inserted = clientBaseController.insert(mContext, TestUtilities.getClient());
        assertEquals(true, inserted);

        Client client = clientBaseController.get(mContext, 1);
        Client expected = TestUtilities.getClient();

        assertEquals(expected.getClientId(), client.getClientId());
        assertEquals(expected.getName(), client.getName());
        assertEquals(expected.getShortName(), client.getShortName());
        assertEquals(expected.isEnabled(), client.isEnabled());

        List<Client> clientList = clientBaseController.listAll(mContext);
        assertEquals(false, clientList.isEmpty());

        boolean deleted = clientBaseController.delete(mContext, client.getClientId());
        assertEquals(true, deleted);
    }
}
