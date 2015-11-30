package com.samsistemas.timesheet.network.converter;

import android.support.annotation.NonNull;

import com.samsistemas.timesheet.constant.JSONConst;
import com.samsistemas.timesheet.entity.WorkPositionEntity;
import com.samsistemas.timesheet.network.converter.base.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author jonatan.salas
 */
public class WorkPositionEntityParser implements JsonParser<WorkPositionEntity, JSONObject>, JSONConst {
    private static WorkPositionEntityParser instance = null;

    private WorkPositionEntityParser() {}

    @Override
    public WorkPositionEntity convert(@NonNull JSONObject jsonObject) throws JSONException {
        final WorkPositionEntity workPositionEntity = new WorkPositionEntity();
        final JSONObject jsonWorkPosition = jsonObject.getJSONObject(WORK_POSITION);

        workPositionEntity.setId(jsonWorkPosition.getLong(ID));
        workPositionEntity.setDescription(jsonWorkPosition.getString(DESCRIPTION));

        return workPositionEntity;
    }

    public static WorkPositionEntityParser newInstance() {
        if(null == instance)
            instance = new WorkPositionEntityParser();
        return instance;
    }
}
