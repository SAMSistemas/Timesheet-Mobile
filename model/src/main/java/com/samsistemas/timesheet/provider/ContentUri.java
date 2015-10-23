package com.samsistemas.timesheet.provider;

/**
 * Interface that has all uris id to associate with SQLiteDatabase tables.
 *
 * @author jonatan.salas
 */
public interface ContentUri {
    int CLIENTS = 1;
    int CLIENT_ID = 2;
    int WORK_POSITION = 3;
    int WORK_POSITION_ID = 4;
    int PERSONS = 5;
    int PERSON_ID = 6;
    int TASK_TYPES = 7;
    int TASKTYPE_ID = 8;
    int TASK_TYPE_WORK_POSITION = 9;
    int TASK_TYPE_WORK_POSITION_ID = 10;
    int PROJECTS = 11;
    int PROJECT_ID = 12;
    int JOB_LOGS = 13;
    int JOBLOG_ID = 14;
}
