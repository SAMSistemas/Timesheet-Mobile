package com.samsistemas.timesheet.util;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.samsistemas.timesheet.mapper.base.EntityMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jonatan.salas
 */
public class CursorUtil {

    public static <T> List<T> asEntityList(@NonNull Context context, @Nullable Cursor cursor, @NonNull EntityMapper<T, Cursor> mapper) {
        List<T> entityList = new ArrayList<>();

        if(null != cursor && cursor.getCount() == 0)
            return entityList;

        if(null != cursor && cursor.moveToFirst()) {
            while(!cursor.isAfterLast()) {
                entityList.add(mapper.asEntity(context, cursor));
                cursor.moveToNext();
            }

            if(!cursor.isClosed())
                cursor.close();
        }

        return entityList;
    }
}
