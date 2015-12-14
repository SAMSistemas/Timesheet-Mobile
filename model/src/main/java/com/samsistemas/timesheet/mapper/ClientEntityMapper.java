package com.samsistemas.timesheet.mapper;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.samsistemas.timesheet.mapper.base.EntityMapper;
import com.samsistemas.timesheet.entity.ClientEntity;
import com.samsistemas.timesheet.util.ConversionUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jonatan.salas
 */
public class ClientEntityMapper implements EntityMapper<ClientEntity, Cursor> {
    private static final String LOG_TAG = ClientEntityMapper.class.getSimpleName();
    private static final String ID_CLIENT = "id";
    private static final String NAME = "name";
    private static final String SHORT_NAME = "short_name";
    private static final String ENABLED = "enabled";

    @Override
    public ContentValues asContentValues(@NonNull ClientEntity clientEntity) {
        ContentValues values = new ContentValues(4);
        int clientEnabled = ConversionUtil.booleanToInt(clientEntity.isEnabled());

        values.put(ID_CLIENT, clientEntity.getId());
        values.put(NAME, clientEntity.getName());
        values.put(SHORT_NAME, clientEntity.getShortName());
        values.put(ENABLED, clientEnabled);

        return values;
    }

    @Override
    public ClientEntity asEntity(@Nullable Cursor cursor) {
        if (null != cursor) {

            final int available = cursor.getInt(cursor.getColumnIndexOrThrow(ENABLED));
            final boolean clientEnabled = ConversionUtil.intToBoolean(available);

            ClientEntity entity = new ClientEntity();

            entity.setId(cursor.getLong(cursor.getColumnIndexOrThrow(ID_CLIENT)));
            entity.setName(cursor.getString(cursor.getColumnIndexOrThrow(NAME)))
                  .setShortName(cursor.getString(cursor.getColumnIndexOrThrow(SHORT_NAME)))
                  .setEnabled(clientEnabled);

            return entity;
        }

        return null;
    }

    @Override
    public List<ClientEntity> asEntityList(@Nullable Cursor cursor) {
        List<ClientEntity> entityList = new ArrayList<>();

        try {
            if (null != cursor && cursor.getCount() == 0) {
                return entityList;
            }

            if (null != cursor && cursor.moveToFirst()) {
                while (!cursor.isAfterLast()){
                    final int available = cursor.getInt(cursor.getColumnIndexOrThrow(ENABLED));
                    final boolean clientEnabled = ConversionUtil.intToBoolean(available);

                    ClientEntity entity = new ClientEntity();

                    entity.setId(cursor.getLong(cursor.getColumnIndexOrThrow(ID_CLIENT)));
                    entity.setName(cursor.getString(cursor.getColumnIndexOrThrow(NAME)))
                          .setShortName(cursor.getString(cursor.getColumnIndexOrThrow(SHORT_NAME)))
                          .setEnabled(clientEnabled);

                    entityList.add(entity);
                    cursor.moveToNext();
                }
            }
        } catch (Exception ex) {
            Log.e(LOG_TAG, ex.getMessage(), ex.getCause());
        } finally {
            if (null != cursor && !cursor.isClosed()) {
                cursor.close();
            }
        }

        return entityList;
    }
}
