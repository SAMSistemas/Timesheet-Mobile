package com.samsistemas.timesheet.common;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.orm.SugarRecord;

/**
 * @author jonatan.salas
 */
public class SugarExclusionStrategy implements ExclusionStrategy {
    private static final Class<?> clazz = SugarRecord.class;

    private SugarExclusionStrategy() {}

    @Override
    public boolean shouldSkipClass(Class<?> clazz) {
        return false;
    }

    @Override
    public boolean shouldSkipField(FieldAttributes f) {
        return f.getDeclaringClass().equals(clazz) && f.getName().equals("id");
    }

    public static SugarExclusionStrategy getInstance() {
        return new SugarExclusionStrategy();
    }
}
