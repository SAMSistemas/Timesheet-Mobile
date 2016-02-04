package com.samsistemas.timesheet.commons.loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.samsistemas.timesheet.commons.controller.ControllerImpl;
import com.samsistemas.timesheet.commons.controller.base.Controller;
import com.samsistemas.timesheet.commons.model.Client;

import java.util.List;

/**
 * @author jonatan.salas
 */
public class ClientsLoader extends AsyncTaskLoader<List<Client>> {
    private static final String LOG_TAG = ClientsLoader.class.getSimpleName();
    private static final Class<Client> clazz = Client.class;
    private final Object lock = new Object();
    private Controller<Client> controller;


    public ClientsLoader(Context context) {
        super(context);
        this.controller = new ControllerImpl<>();
    }

    @Override
    public List<Client> loadInBackground() {
        List<Client> clientList = null;

        if (controller.getCount(clazz) == 0) {
            try {
                synchronized (lock) {
                    lock.wait(3000);
                    clientList = controller.listAll(clazz);
                }
            } catch (InterruptedException ex) {
                Log.e(LOG_TAG, ex.getMessage(), ex.getCause());
            }
        } else {
            synchronized (lock) {
                clientList = controller.listAll(clazz);
                lock.notify();
            }
        }

        return clientList;
    }
}
