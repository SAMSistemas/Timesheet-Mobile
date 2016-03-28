package com.samsistemas.timesheet.application;

import android.content.Context;
import android.support.multidex.MultiDex;

import com.orm.SugarApp;
import com.samsistemas.timesheet.utility.PreferenceUtility;

/**
 * @author jonatan.salas
 */
public class TimesheetApp extends SugarApp {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
        PreferenceUtility.init(base);
    }
}
