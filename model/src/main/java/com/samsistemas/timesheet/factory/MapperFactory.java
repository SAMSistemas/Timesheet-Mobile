package com.samsistemas.timesheet.factory;

import android.database.Cursor;

import com.samsistemas.timesheet.entity.ClientEntity;
import com.samsistemas.timesheet.entity.JobLogEntity;
import com.samsistemas.timesheet.entity.PersonEntity;
import com.samsistemas.timesheet.entity.ProjectEntity;
import com.samsistemas.timesheet.entity.TaskTypeEntity;
import com.samsistemas.timesheet.entity.WorkPositionEntity;
import com.samsistemas.timesheet.mapper.ClientEntityMapper;
import com.samsistemas.timesheet.mapper.JobLogEntityMapper;
import com.samsistemas.timesheet.mapper.PersonEntityMapper;
import com.samsistemas.timesheet.mapper.ProjectEntityMapper;
import com.samsistemas.timesheet.mapper.TaskTypeEntityMapper;
import com.samsistemas.timesheet.mapper.WorkPositionEntityMapper;
import com.samsistemas.timesheet.mapper.base.EntityMapper;

/**
 * @author jonatan.salas
 */
public class MapperFactory {
    private static EntityMapper<ClientEntity, Cursor> clientEntityMapper = null;
    private static EntityMapper<JobLogEntity, Cursor> joblogEntityMapper = null;
    private static EntityMapper<PersonEntity, Cursor> personEntityMapper = null;
    private static EntityMapper<ProjectEntity, Cursor> projectEntityMapper = null;
    private static EntityMapper<TaskTypeEntity, Cursor> taskTypeEntityMapper = null;
    private static EntityMapper<WorkPositionEntity, Cursor> workPositionEntityMapper = null;

    private MapperFactory() { }

    /**
     *
     * @return
     */
    public static EntityMapper<ClientEntity, Cursor> getClientMapper() {
        if (null == clientEntityMapper) {
            clientEntityMapper = new ClientEntityMapper();
        }
        return clientEntityMapper;
    }

    /**
     *
     * @return
     */
    public static EntityMapper<JobLogEntity, Cursor> getJoblogMapper() {
        if (null == joblogEntityMapper) {
            joblogEntityMapper = new JobLogEntityMapper();
        }
        return joblogEntityMapper;
    }

    /**
     *
     * @return
     */
    public static EntityMapper<PersonEntity, Cursor> getPersonMapper() {
        if (null == personEntityMapper) {
            personEntityMapper = new PersonEntityMapper();
        }
        return personEntityMapper;
    }

    /**
     *
     * @return
     */
    public static EntityMapper<ProjectEntity, Cursor> getProjectMapper() {
        if (null == projectEntityMapper) {
            projectEntityMapper = new ProjectEntityMapper();
        }
        return projectEntityMapper;
    }

    /**
     *
     * @return
     */
    public static EntityMapper<TaskTypeEntity, Cursor> getTaskTypeMapper() {
        if (null == taskTypeEntityMapper) {
            taskTypeEntityMapper = new TaskTypeEntityMapper();
        }
        return taskTypeEntityMapper;
    }

    /**
     *
     * @return
     */
    public static EntityMapper<WorkPositionEntity, Cursor> getWorkPositionMapper() {
        if (null == workPositionEntityMapper) {
            workPositionEntityMapper = new WorkPositionEntityMapper();
        }
        return workPositionEntityMapper;
    }
}
