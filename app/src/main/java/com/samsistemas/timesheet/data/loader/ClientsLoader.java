package com.samsistemas.timesheet.data.loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.samsistemas.timesheet.data.domain.Client;

import java.util.List;

/**
 * @author jonatan.salas
 */
public class ClientsLoader extends AsyncTaskLoader<List<Client>> {
    private static final String LOG_TAG = ClientsLoader.class.getSimpleName();
    private static final Class<Client> clazz = Client.class;
    private final Object lock = new Object();

    public ClientsLoader(Context context) {
        super(context);
    }

    @Override
    public List<Client> loadInBackground() {
        List<Client> clientList = null;

        if (Client.count(clazz) == 0) {
            try {
                synchronized (lock) {
                    lock.wait(3000);
                    clientList = Client.listAll(clazz);
                }
            } catch (InterruptedException ex) {
                Log.e(LOG_TAG, ex.getMessage(), ex.getCause());
            }
        } else {
            synchronized (lock) {
                clientList = Client.listAll(clazz);
                lock.notify();
            }
        }

        return clientList;
    }
}
