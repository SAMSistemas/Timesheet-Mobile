package com.samsistemas.timesheet.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.samsistemas.timesheet.controller.base.BaseController;
import com.samsistemas.timesheet.data.R;
import com.samsistemas.timesheet.helper.UriHelper;
import com.samsistemas.timesheet.model.Client;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that implements the ClientController interface.
 *
 * @author jonatan.salas
 */
public class ClientController implements BaseController<Client> {

    @Override
    public boolean insert(@NonNull Context context, @NonNull Client client) {
        final Uri clientUri = UriHelper.buildClientUri(context);
        final ContentValues clientValues = client.asContentValues(context);

        final Uri resultUri = context.getContentResolver().insert(clientUri, clientValues);

        return (null != resultUri);
    }

    @Override
    public boolean bulkInsert(@NonNull Context context, @NonNull List<Client> clients) {
        final Uri clientUri = UriHelper.buildClientUri(context);
        final ContentValues[] clientsValues = new ContentValues[clients.size()];

        for(int i = 0; i < clients.size(); i++) {
            clientsValues[i] = clients.get(i).asContentValues(context);
        }

        final int count = context.getContentResolver().bulkInsert(clientUri, clientsValues);

        return (0 != count);
    }

    @Override
    public Client get(@NonNull Context context, long id) {
        final Uri clientUri = UriHelper.buildClientUriWithId(context, id);
        final Cursor clientCursor = context.getContentResolver().query(clientUri, null, null, null, null);

        return new Client().save(context, clientCursor);
    }

    @Override
    public List<Client> listAll(@NonNull Context context) {
        final Uri clientUri = UriHelper.buildClientUri(context);
        final Cursor clientsCursor = context.getContentResolver().query(clientUri, null, null, null, null);

        List<Client> clients = new ArrayList<>();

        if(null != clientsCursor && clientsCursor.moveToFirst()) {
            for(int i = 0; i < clientsCursor.getCount(); i++) {
                clients.add(new Client().save(context, clientsCursor));
            }

            if(!clientsCursor.isClosed())
                clientsCursor.close();
        }

        return clients;
    }

    @Override
    public boolean update(@NonNull Context context, @NonNull Client client) {
        final Uri clientUri = UriHelper.buildClientUri(context);
        final ContentValues clientValues = client.asContentValues(context);
        final String whereClause = context.getString(R.string.client_id) + " =? ";
        final String[] whereArgs = new String[] { String.valueOf(client.getClientId()) };

        int updatedRows = context.getContentResolver().update(clientUri, clientValues, whereClause, whereArgs);

        return (0 != updatedRows);
    }

    @Override
    public boolean delete(@NonNull Context context, long id) {
        final Uri clientUri = UriHelper.buildClientUri(context);
        final String selection = context.getString(R.string.client_id) + " =? ";
        final String[] selectionArgs = new String[] { String.valueOf(id) };

        int deletedRows = context.getContentResolver().delete(clientUri, selection, selectionArgs);

        return (0 != deletedRows);
    }
}
