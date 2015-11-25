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
import com.samsistemas.timesheet.entity.PersonEntity;
import com.samsistemas.timesheet.mapper.base.EntityMapper;
import com.samsistemas.timesheet.util.CursorUtil;

import java.util.List;

/**
 * Class that implements the PersonController interface.
 *
 * @author jonatan.salas
 */
public class PersonController implements BaseController<PersonEntity> {
    protected EntityMapper<PersonEntity, Cursor> personMapper;

    public PersonController() {
        this.personMapper = MapperFactory.getPersonMapper();
    }

    @Override
    public boolean insert(@NonNull Context context, @NonNull PersonEntity personEntity) {
        final Uri personUri = UriHelper.buildPersonUri(context);
        final ContentValues personValues = personMapper.asContentValues(personEntity);
        final Uri resultUri = context.getContentResolver().insert(personUri, personValues);
        return (null != resultUri);
    }

    @Override
    public boolean bulkInsert(@NonNull Context context, @NonNull List<PersonEntity> personEntities) {
        final Uri personUri = UriHelper.buildPersonUri(context);
        final ContentValues[] personValues = new ContentValues[personEntities.size()];

        for(int i = 0; i < personEntities.size(); i++) {
            personValues[i] = personMapper.asContentValues(personEntities.get(i));
        }

        int count = context.getContentResolver().bulkInsert(personUri, personValues);

        return (count != 0);
    }

    @Override
    public PersonEntity get(@NonNull Context context, long id) {
        final Uri personUri = UriHelper.buildPersonUriWithId(context, id);
        Cursor personCursor = context.getContentResolver().query(personUri, null, null, null, null);
        if(null != personCursor)
            personCursor.moveToFirst();

        final PersonEntity personEntity = personMapper.asEntity(personCursor);

        if(null != personCursor && !personCursor.isClosed())
            personCursor.close();

        return personEntity;
    }

    @Override
    public List<PersonEntity> listAll(@NonNull Context context) {
        final Uri personUri = UriHelper.buildPersonUri(context);
        Cursor personsCursor = context.getContentResolver().query(personUri, null, null, null, null);

        return CursorUtil.asEntityList(context, personsCursor, personMapper);
    }

    @Override
    public boolean update(@NonNull Context context, @NonNull PersonEntity personEntity) {
        final Uri personUri = UriHelper.buildPersonUri(context);
        final ContentValues personValues = personMapper.asContentValues(personEntity);
        final String whereClause = context.getString(R.string.person_id) + " =? ";
        final String[] whereArgs = new String[] { String.valueOf(personEntity.getPersonId()) };

        int updatedRows = context.getContentResolver().update(personUri, personValues, whereClause, whereArgs);

        return (0 != updatedRows);
    }

    @Override
    public boolean delete(@NonNull Context context, long id) {
        final Uri personUri = UriHelper.buildPersonUri(context);
        final String selection = context.getString(R.string.person_id) + " =? ";
        final String[] selectionArgs = new String[] { String.valueOf(id) };

        int deletedRows = context.getContentResolver().delete(personUri, selection, selectionArgs);

        return (0 != deletedRows);
    }
}
