package com.samsistemas.timesheet.network.service;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.samsistemas.timesheet.controller.Controller;
import com.samsistemas.timesheet.entity.JobLogEntity;
import com.samsistemas.timesheet.factory.ControllerFactory;
import com.samsistemas.timesheet.helper.UriHelper;
import com.samsistemas.timesheet.network.converter.JobLogEntityParser;
import com.samsistemas.timesheet.network.service.base.NetworkService;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author jonatan.salas
 */
public class JobLogNetworkService implements NetworkService<JSONObject, Object> {

    @Override
    public Object parseNetworkResponse(@NonNull Context context, JSONObject response, Object customArgs) throws JSONException {
        final Controller<JobLogEntity> jobLogController = ControllerFactory.getJobLogController();

        final JobLogEntityParser jobLogEntityParser = JobLogEntityParser.newInstance();
        final JobLogEntity jobLogEntity= jobLogEntityParser.convert(response);
        final Uri uri = UriHelper.buildJobLogUri(context);

        return jobLogController.insert(context.getApplicationContext(), jobLogEntity, uri);
    }
}
