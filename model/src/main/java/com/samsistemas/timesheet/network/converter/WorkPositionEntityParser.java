package com.samsistemas.timesheet.network.converter;

import android.content.Context;
import android.support.annotation.NonNull;

import com.samsistemas.timesheet.data.R;
import com.samsistemas.timesheet.entity.WorkPositionEntity;
import com.samsistemas.timesheet.network.converter.base.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author jonatan.salas
 */
public class WorkPositionEntityParser implements JsonParser<WorkPositionEntity, JSONObject> {
    protected static WorkPositionEntityParser instance = null;

    protected WorkPositionEntityParser() {}

    @Override
    public WorkPositionEntity convert(@NonNull Context context, @NonNull JSONObject jsonObject) throws JSONException {
        final WorkPositionEntity workPositionEntity = new WorkPositionEntity();
        final JSONObject jsonWorkPosition = jsonObject.getJSONObject(context.getString(R.string.work_position));

        workPositionEntity.setWorkPositionId(jsonWorkPosition.getLong(context.getString(R.string.id)))
                          .setDescription(jsonWorkPosition.getString(context.getString(R.string.description)));

        return workPositionEntity;
    }

    public static WorkPositionEntityParser newInstance() {
        if(null == instance)
            instance = new WorkPositionEntityParser();
        return instance;
    }
}
