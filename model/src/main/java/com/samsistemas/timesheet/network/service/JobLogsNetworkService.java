package com.samsistemas.timesheet.network.service;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.samsistemas.timesheet.controller.Controller;
import com.samsistemas.timesheet.entity.JobLogEntity;
import com.samsistemas.timesheet.factory.ControllerFactory;
import com.samsistemas.timesheet.helper.UriHelper;
import com.samsistemas.timesheet.network.converter.JobLogEntityListParser;
import com.samsistemas.timesheet.network.service.base.NetworkService;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;

/**
 * @author jonatan.salas
 */
public class JobLogsNetworkService implements NetworkService<JSONArray, Object> {

    @Override
    public Object parseNetworkResponse(@NonNull Context context, JSONArray response, Object customArgs) throws JSONException {
        final Controller<JobLogEntity> jobLogController = ControllerFactory.getJobLogController();

        final JobLogEntityListParser jobLogEntityListParser = JobLogEntityListParser.newInstance();
        final List<JobLogEntity> jobLogEntities = jobLogEntityListParser.convert(response);
        final Uri uri = UriHelper.buildJobLogUri(context);

        return jobLogController.bulkInsert(context.getApplicationContext(), jobLogEntities, uri);
    }
}
