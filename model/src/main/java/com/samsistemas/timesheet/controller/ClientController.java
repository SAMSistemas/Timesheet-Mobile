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

import java.util.ArrayList;
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
        final ContentValues clientValues = clientMapper.asContentValues(context, clientEntity);
        final ClientEntity entity = get(context, clientEntity.getClientId());

        if (null != entity) {
            return false;
        } else {
            final Uri resultUri = context.getContentResolver().insert(clientUri, clientValues);
            return (null != resultUri);
        }
    }

    @Override
    public boolean bulkInsert(@NonNull Context context, @NonNull List<ClientEntity> clientEntities) {
        int count = 0;

        for(ClientEntity entity: clientEntities) {
            boolean inserted = insert(context, entity);
            if(inserted)
                count++;
        }

        return (count == clientEntities.size());
    }

    @Override
    public ClientEntity get(@NonNull Context context, long id) {
        final Uri clientUri = UriHelper.buildClientUriWithId(context, id);
        final Cursor clientCursor = context.getContentResolver().query(clientUri, null, null, null, null);

        return clientMapper.asEntity(context, clientCursor);
    }

    @Override
    public List<ClientEntity> listAll(@NonNull Context context) {
        final Uri clientUri = UriHelper.buildClientUri(context);
        final Cursor clientsCursor = context.getContentResolver().query(clientUri, null, null, null, null);

        List<ClientEntity> clientEntities = new ArrayList<>();

        if(null != clientsCursor && clientsCursor.moveToFirst()) {
            for(int i = 0; i < clientsCursor.getCount(); i++) {
                clientEntities.add(clientMapper.asEntity(context, clientsCursor));
            }

            if(!clientsCursor.isClosed())
                clientsCursor.close();
        }

        return clientEntities;
    }

    @Override
    public boolean update(@NonNull Context context, @NonNull ClientEntity clientEntity) {
        final Uri clientUri = UriHelper.buildClientUri(context);
        final ContentValues clientValues = clientMapper.asContentValues(context, clientEntity);
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
