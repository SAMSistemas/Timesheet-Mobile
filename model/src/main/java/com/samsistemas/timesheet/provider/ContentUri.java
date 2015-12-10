package com.samsistemas.timesheet.provider;

/**
 * public static final int erface that has all uris id to associate with SQLiteDatabase tables.
 *
 * @author jonatan.salas
 */
public final class ContentUri {
    public static final int  CLIENTS = 1;
    public static final int  CLIENT_ID = 2;
    public static final int  WORK_POSITION = 3;
    public static final int  WORK_POSITION_ID = 4;
    public static final int  PERSONS = 5;
    public static final int  PERSON_ID = 6;
    public static final int  TASK_TYPES = 7;
    public static final int  TASKTYPE_ID = 8;
    public static final int  PROJECTS = 9;
    public static final int  PROJECT_ID = 10;
    public static final int  JOB_LOGS = 11;
    public static final int  JOBLOG_ID = 12;

    private ContentUri() { }
}
