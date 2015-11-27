package com.samsistemas.timesheet.mapper;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.samsistemas.timesheet.mapper.base.EntityMapper;
import com.samsistemas.timesheet.entity.ClientEntity;
import com.samsistemas.timesheet.util.ConversionUtil;

/**
 * @author jonatan.salas
 */
public class ClientEntityMapper implements EntityMapper<ClientEntity, Cursor> {
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
        if (null != cursor && cursor.moveToFirst()) {

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
}
