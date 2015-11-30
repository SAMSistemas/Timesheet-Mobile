package com.samsistemas.timesheet.loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.samsistemas.timesheet.facade.ClientFacade;
import com.samsistemas.timesheet.model.Client;

import java.util.List;

/**
 * @author jonatan.salas
 */
public class ClientsLoader extends AsyncTaskLoader<List<Client>> {
    private final ClientFacade mFacade;
    private final Context mContext;

    public ClientsLoader(Context context) {
        super(context);
        this.mFacade = ClientFacade.newInstance();
        this.mContext = context;
    }

    @Override
    public List<Client> loadInBackground() {
        List<Client> clientList = mFacade.findAll(mContext);
        return (null != clientList && clientList.size() > 0)? clientList : null;
    }
}
