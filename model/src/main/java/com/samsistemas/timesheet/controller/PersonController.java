package com.samsistemas.timesheet.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.samsistemas.timesheet.controller.base.BaseController;
import com.samsistemas.timesheet.data.R;
import com.samsistemas.timesheet.helper.UriHelper;
import com.samsistemas.timesheet.model.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that implements the PersonController interface.
 *
 * @author jonatan.salas
 */
public class PersonController implements BaseController<Person> {

    @Override
    public boolean insert(@NonNull Context context, @NonNull Person person) {
        final Uri personUri = UriHelper.buildPersonUri(context);
        final ContentValues personValues = person.asContentValues(context);

        final Uri resultUri = context.getContentResolver().insert(personUri, personValues);

        return (null != resultUri);
    }

    @Override
    public boolean bulkInsert(@NonNull Context context, @NonNull List<Person> persons) {
        final Uri personsUri = UriHelper.buildPersonUri(context);
        final ContentValues[] personsValues = new ContentValues[persons.size()];

        for(int i = 0; i < persons.size(); i++) {
            personsValues[i] = persons.get(i).asContentValues(context);
        }

        final int count = context.getContentResolver().bulkInsert(personsUri, personsValues);

        return (0 != count);
    }

    @Override
    public Person get(@NonNull Context context, long id) {
        final Uri personUri = UriHelper.buildPersonUriWithId(context, id);
        final Cursor personCursor = context.getContentResolver().query(personUri, null, null, null, null);

        return new Person().save(context, personCursor);
    }

    @Override
    public List<Person> listAll(@NonNull Context context) {
        final Uri personUri = UriHelper.buildPersonUri(context);
        final Cursor personsCursor = context.getContentResolver().query(personUri, null, null, null, null);

        List<Person> persons = new ArrayList<>();

        if(null != personsCursor && personsCursor.moveToFirst()) {
            for(int i = 0; i < personsCursor.getCount(); i++) {
                persons.add(new Person().save(context, personsCursor));
            }
        }

        return persons;
    }

    @Override
    public boolean update(@NonNull Context context, @NonNull Person person) {
        final Uri personUri = UriHelper.buildPersonUri(context);
        final ContentValues personValues = person.asContentValues(context);
        final String whereClause = context.getString(R.string.person_id) + " =? ";
        final String[] whereArgs = new String[] { String.valueOf(person.getPersonId()) };

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
