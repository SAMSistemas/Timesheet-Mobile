package com.samsistemas.timesheet.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.samsistemas.timesheet.controller.base.BaseController;
import com.samsistemas.timesheet.data.R;
import com.samsistemas.timesheet.factory.MapperFactory;
import com.samsistemas.timesheet.helper.UriHelper;
import com.samsistemas.timesheet.entity.ClientEntity;
import com.samsistemas.timesheet.mapper.base.EntityMapper;
import com.samsistemas.timesheet.util.CursorUtil;

import java.util.List;

/**
 * Class that implements the ClientController interface.
 *
 * @author jonatan.salas
 */
public class ClientController implements BaseController<ClientEntity> {
    protected EntityMapper<ClientEntity, Cursor> clientMapper;

    public ClientController() {
        this.clientMapper = MapperFactory.getClientMapper();
    }

    @Override
    public boolean insert(@NonNull Context context, @NonNull ClientEntity clientEntity) {
        final Uri clientUri = UriHelper.buildClientUri(context);
        final ContentValues clientValues = clientMapper.asContentValues(clientEntity);
        final Uri resultUri = context.getContentResolver().insert(clientUri, clientValues);
        return (null != resultUri);
    }

    @Override
    public boolean bulkInsert(@NonNull Context context, @NonNull List<ClientEntity> clientEntities) {
        final Uri clientUri = UriHelper.buildClientUri(context);
        final ContentValues[] clientValues = new ContentValues[clientEntities.size()];

        for(int i = 0; i < clientEntities.size(); i++) {
            clientValues[i] = clientMapper.asContentValues(clientEntities.get(i));
        }

        int count = context.getContentResolver().bulkInsert(clientUri, clientValues);

        return (count != 0);
    }

    @Override
    public ClientEntity get(@NonNull Context context, long id) {
        final Uri clientUri = UriHelper.buildClientUriWithId(context, id);
        Cursor clientCursor = context.getContentResolver().query(clientUri, null, null, null, null);
        if(null != clientCursor)
            clientCursor.moveToFirst();

        final ClientEntity clientEntity = clientMapper.asEntity(clientCursor);

        if(null != clientCursor && !clientCursor.isClosed())
            clientCursor.close();

        return clientEntity;
    }

    @Override
    public List<ClientEntity> listAll(@NonNull Context context) {
        final Uri clientUri = UriHelper.buildClientUri(context);
        Cursor clientsCursor = context.getContentResolver().query(clientUri, null, null, null, null);

        return CursorUtil.asEntityList(clientsCursor, clientMapper);
    }

    @Override
    public boolean update(@NonNull Context context, @NonNull ClientEntity clientEntity) {
        final Uri clientUri = UriHelper.buildClientUri(context);
        final ContentValues clientValues = clientMapper.asContentValues(clientEntity);
        final String whereClause = context.getString(R.string.client_id) + " =? ";
        final String[] whereArgs = new String[] { String.valueOf(clientEntity.getClientId()) };

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
