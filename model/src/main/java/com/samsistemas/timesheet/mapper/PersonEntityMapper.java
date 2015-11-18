package com.samsistemas.timesheet.mapper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.samsistemas.timesheet.data.R;
import com.samsistemas.timesheet.entity.PersonEntity;
import com.samsistemas.timesheet.mapper.base.EntityMapper;
import com.samsistemas.timesheet.util.ConversionUtil;

/**
 * @author jonatan.salas
 */
public class PersonEntityMapper implements EntityMapper<PersonEntity, Cursor> {

    @Override
    public ContentValues asContentValues(@NonNull Context context, @NonNull PersonEntity personEntity) {
        final ContentValues values = new ContentValues(7);
        final int personEnabled = ConversionUtil.booleanToInt(personEntity.isEnabled());
        final byte[] personPicture = ConversionUtil.drawableToByteArray(personEntity.getPicture());

        values.put(context.getString(R.string.person_id), personEntity.getPersonId());
        values.put(context.getString(R.string.person_name), personEntity.getName());
        values.put(context.getString(R.string.person_last_name), personEntity.getLastName());
        values.put(context.getString(R.string.person_user_name), personEntity.getUsername());
        values.put(context.getString(R.string.person_password), personEntity.getPassword());
        values.put(context.getString(R.string.person_work_position_id), personEntity.getWorkPositionId());
        values.put(context.getString(R.string.person_work_hours), personEntity.getWorkHours());
        values.put(context.getString(R.string.person_picture), personPicture);
        values.put(context.getString(R.string.person_enabled), personEnabled);

        return values;
    }

    @Override
    public PersonEntity asEntity(@NonNull Context context, @Nullable Cursor cursor) {
        if (null != cursor && cursor.moveToFirst()) {

            final byte[] profile = cursor.getBlob(cursor.getColumnIndexOrThrow(context.getString(R.string.person_picture)));
            final int available = cursor.getInt(cursor.getColumnIndexOrThrow(context.getString(R.string.person_enabled)));
            final Drawable personPicture = ConversionUtil.byteArrayToDrawable(profile);
            final boolean personEnabled = ConversionUtil.intToBoolean(available);

            final PersonEntity personEntity = new PersonEntity();

            personEntity.setPersonId(cursor.getInt(cursor.getColumnIndexOrThrow(context.getString(R.string.person_id))))
                    .setName(cursor.getString(cursor.getColumnIndexOrThrow(context.getString(R.string.person_name))))
                    .setLastName(cursor.getString(cursor.getColumnIndexOrThrow(context.getString(R.string.person_last_name))))
                    .setUsername(cursor.getString(cursor.getColumnIndexOrThrow(context.getString(R.string.person_user_name))))
                    .setPassword(cursor.getString(cursor.getColumnIndexOrThrow(context.getString(R.string.person_password))))
                    .setWorkPositionId(cursor.getLong(cursor.getColumnIndexOrThrow(context.getString(R.string.person_work_position_id))))
                    .setWorkHours(cursor.getInt(cursor.getColumnIndexOrThrow(context.getString(R.string.person_work_hours))))
                    .setPicture(personPicture)
                    .setEnabled(personEnabled);

            return personEntity;
        }

        return null;
    }
}
