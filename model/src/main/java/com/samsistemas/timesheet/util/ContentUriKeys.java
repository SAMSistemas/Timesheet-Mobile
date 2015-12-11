package com.samsistemas.timesheet.util;

/**
 * Utility class containing URI references for ContentProvider
 *
 * @author jonatan.salas
 */
public final class ContentUriKeys {

    /**
     * Uri reference to table Client
     */
    public static final int  CLIENTS = 1;

    /**
     * Uri referencing a Client entity
     */
    public static final int  CLIENT_ID = 2;

    /**
     * Uri reference to table Work Position
     */
    public static final int  WORK_POSITION = 3;

    /**
     * Uri referencing a Work position entity
     */
    public static final int  WORK_POSITION_ID = 4;

    /**
     * Uri reference to table Person
     */
    public static final int  PERSONS = 5;

    /**
     * Uri referencing a person entity
     */
    public static final int  PERSON_ID = 6;

    /**
     * Uri reference to table Task Type
     */
    public static final int  TASK_TYPES = 7;

    /**
     * Uri referencing a TaskType entity
     */
    public static final int  TASKTYPE_ID = 8;

    /**
     * Uri reference to table Project
     */
    public static final int  PROJECTS = 9;

    /**
     * Uri referencing a Project entity
     */
    public static final int  PROJECT_ID = 10;

    /**
     * Uri reference to table JobLogs
     */
    public static final int  JOB_LOGS = 11;

    /**
     * Uri referencing a JobLog entity
     */
    public static final int  JOBLOG_ID = 12;

    private ContentUriKeys() { }
}
