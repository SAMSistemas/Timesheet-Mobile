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
    protected ClientFacade mFacade;
    protected Context mContext;
    protected List<Client> mClientList;

    public ClientsLoader(Context context) {
        super(context);
        this.mFacade = ClientFacade.newInstance();
        this.mContext = context;
    }

    @Override
    public List<Client> loadInBackground() {
        mClientList = mFacade.findAll(mContext);
        return (null != mClientList && mClientList.size() > 0)? mClientList : null;
    }
}
