package com.samsistemas.timesheet.mapper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.samsistemas.timesheet.data.R;
import com.samsistemas.timesheet.mapper.base.EntityMapper;
import com.samsistemas.timesheet.entity.ClientEntity;
import com.samsistemas.timesheet.util.ConversionUtil;

/**
 * @author jonatan.salas
 */
public class ClientEntityMapper implements EntityMapper<ClientEntity, Cursor> {

    @Override
    public ContentValues asContentValues(@NonNull Context context, @NonNull ClientEntity clientEntity) {
        final ContentValues values = new ContentValues(4);
        final int clientEnabled = ConversionUtil.booleanToInt(clientEntity.isEnabled());

        values.put(context.getString(R.string.client_id), clientEntity.getClientId());
        values.put(context.getString(R.string.client_name), clientEntity.getName());
        values.put(context.getString(R.string.client_short_name), clientEntity.getShortName());
        values.put(context.getString(R.string.client_enabled), clientEnabled);

        return values;
    }

    @Override
    public ClientEntity asEntity(@NonNull Context context, @Nullable Cursor cursor) {
        if (null != cursor && cursor.moveToFirst()) {

            final int available = cursor.getInt(cursor.getColumnIndexOrThrow(context.getString(R.string.client_enabled)));
            final boolean clientEnabled = ConversionUtil.intToBoolean(available);

            final ClientEntity clientEntity = new ClientEntity();

            clientEntity.setClientId(cursor.getLong(cursor.getColumnIndexOrThrow(context.getString(R.string.client_id))))
                    .setName(cursor.getString(cursor.getColumnIndexOrThrow(context.getString(R.string.client_name))))
                    .setShortName(cursor.getString(cursor.getColumnIndexOrThrow(context.getString(R.string.client_short_name))))
                    .setEnabled(clientEnabled);

            return clientEntity;
        }

        return null;
    }
}
