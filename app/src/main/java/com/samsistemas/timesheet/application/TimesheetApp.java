package com.samsistemas.timesheet.application;

import android.content.Context;
import android.support.multidex.MultiDex;

import com.orm.SugarApp;

/**
 * @author jonatan.salas
 */
public class TimesheetApp extends SugarApp {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
