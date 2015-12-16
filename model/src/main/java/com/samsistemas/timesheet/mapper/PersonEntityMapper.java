package com.samsistemas.timesheet.mapper;

import android.content.ContentValues;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.samsistemas.timesheet.entity.PersonEntity;
import com.samsistemas.timesheet.mapper.base.EntityMapper;
import com.samsistemas.timesheet.util.ConversionUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jonatan.salas
 */
public class PersonEntityMapper implements EntityMapper<PersonEntity, Cursor> {
    private static final String LOG_TAG = PersonEntityMapper.class.getSimpleName();

    private static final String ID_PERSON = "id";
    private static final String NAME = "name";
    private static final String LAST_NAME = "lastname";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String ID_WORK_POSITION = "id_workposition";
    private static final String WORK_HOURS = "work_hours";
    private static final String PICTURE = "picture";
    private static final String ENABLED = "enabled";

   @Override
    public ContentValues asContentValues(@NonNull PersonEntity personEntity) {
        ContentValues values = new ContentValues(7);
        int personEnabled = ConversionUtil.booleanToInt(personEntity.isEnabled());
        byte[] personPicture = ConversionUtil.drawableToByteArray(personEntity.getPicture());

        values.put(ID_PERSON, personEntity.getId());
        values.put(NAME, personEntity.getName());
        values.put(LAST_NAME, personEntity.getLastName());
        values.put(USERNAME, personEntity.getUsername());
        values.put(PASSWORD, personEntity.getPassword());
        values.put(ID_WORK_POSITION, personEntity.getWorkPositionId());
        values.put(WORK_HOURS, personEntity.getWorkHours());
        values.put(PICTURE, personPicture);
        values.put(ENABLED, personEnabled);

        return values;
    }

    @Override
    public PersonEntity asEntity(@Nullable Cursor cursor) {
        PersonEntity entity = new PersonEntity();

        try {
            if (null != cursor && cursor.getCount() == 0) {
                return entity;
            }

            if (null != cursor && cursor.moveToFirst()) {
                final byte[] profile = cursor.getBlob(cursor.getColumnIndexOrThrow(PICTURE));
                final int available = cursor.getInt(cursor.getColumnIndexOrThrow(ENABLED));
                final Drawable personPicture = ConversionUtil.byteArrayToDrawable(profile);
                final boolean personEnabled = ConversionUtil.intToBoolean(available);

                entity.setId(cursor.getInt(cursor.getColumnIndexOrThrow(ID_PERSON)));
                entity.setName(cursor.getString(cursor.getColumnIndexOrThrow(NAME)))
                        .setLastName(cursor.getString(cursor.getColumnIndexOrThrow(LAST_NAME)))
                        .setUsername(cursor.getString(cursor.getColumnIndexOrThrow(USERNAME)))
                        .setPassword(cursor.getString(cursor.getColumnIndexOrThrow(PASSWORD)))
                        .setWorkPositionId(cursor.getLong(cursor.getColumnIndexOrThrow(ID_WORK_POSITION)))
                        .setWorkHours(cursor.getInt(cursor.getColumnIndexOrThrow(WORK_HOURS)))
                        .setPicture(personPicture)
                        .setEnabled(personEnabled);
            }

        } catch (Exception ex) {
            Log.e(LOG_TAG, ex.getMessage(), ex.getCause());
        } finally {
            if (null != cursor && !cursor.isClosed()) {
                cursor.close();
            }
        }

        return entity;
    }

    @Override
    public List<PersonEntity> asEntityList(@Nullable Cursor cursor) {
        List<PersonEntity> entityList = new ArrayList<>();

        try {
            if (null != cursor && cursor.getCount() == 0) {
                return entityList;
            }

            if (null != cursor && cursor.moveToFirst()) {
                do {
                    final byte[] profile = cursor.getBlob(cursor.getColumnIndexOrThrow(PICTURE));
                    final int available = cursor.getInt(cursor.getColumnIndexOrThrow(ENABLED));
                    final Drawable personPicture = ConversionUtil.byteArrayToDrawable(profile);
                    final boolean personEnabled = ConversionUtil.intToBoolean(available);

                    PersonEntity entity = new PersonEntity();
                    entity.setId(cursor.getInt(cursor.getColumnIndexOrThrow(ID_PERSON)));
                    entity.setName(cursor.getString(cursor.getColumnIndexOrThrow(NAME)))
                          .setLastName(cursor.getString(cursor.getColumnIndexOrThrow(LAST_NAME)))
                          .setUsername(cursor.getString(cursor.getColumnIndexOrThrow(USERNAME)))
                          .setPassword(cursor.getString(cursor.getColumnIndexOrThrow(PASSWORD)))
                          .setWorkPositionId(cursor.getLong(cursor.getColumnIndexOrThrow(ID_WORK_POSITION)))
                          .setWorkHours(cursor.getInt(cursor.getColumnIndexOrThrow(WORK_HOURS)))
                          .setPicture(personPicture)
                          .setEnabled(personEnabled);

                    entityList.add(entity);

                } while (cursor.moveToNext());
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
