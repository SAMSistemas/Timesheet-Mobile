package com.samsistemas.timesheet.network.service;

import android.content.Context;
import android.support.annotation.NonNull;

import com.samsistemas.timesheet.controller.base.BaseController;
import com.samsistemas.timesheet.entity.JobLogEntity;
import com.samsistemas.timesheet.factory.ControllerFactory;
import com.samsistemas.timesheet.network.converter.JobLogEntityListParser;
import com.samsistemas.timesheet.network.service.base.NetworkService;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;

/**
 * @author jonatan.salas
 */
public class JobLogNetworkService implements NetworkService<JSONArray, Object> {

    @Override
    public void parseNetworkResponse(@NonNull Context context, JSONArray response, Object customArgs) throws JSONException {
        final BaseController<JobLogEntity> jobLogController = ControllerFactory.getJobLogController();

        final JobLogEntityListParser jobLogEntityListParser = JobLogEntityListParser.newInstance();
        final List<JobLogEntity> jobLogEntities = jobLogEntityListParser.convert(context.getApplicationContext(), response);

        jobLogController.bulkInsert(context.getApplicationContext(), jobLogEntities);
    }
}
