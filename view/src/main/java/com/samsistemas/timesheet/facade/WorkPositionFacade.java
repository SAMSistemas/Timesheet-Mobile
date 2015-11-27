package com.samsistemas.timesheet.facade;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.samsistemas.timesheet.controller.Controller;
import com.samsistemas.timesheet.entity.WorkPositionEntity;
import com.samsistemas.timesheet.facade.base.Facade;
import com.samsistemas.timesheet.factory.ControllerFactory;
import com.samsistemas.timesheet.helper.UriHelper;
import com.samsistemas.timesheet.model.WorkPosition;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jonatan.salas
 */
public class WorkPositionFacade implements Facade<WorkPosition> {
    private static WorkPositionFacade instance = null;
    protected Controller<WorkPositionEntity> workPositionController;

    protected WorkPositionFacade() {
        this.workPositionController = ControllerFactory.getWorkPositionController();
    }

    @Override
    public WorkPosition findById(@NonNull Context context, long id) {
        final Uri uri = UriHelper.buildWorkPositionUriWithId(context, id);
        final WorkPositionEntity entity = workPositionController.get(context, uri);
        final WorkPosition workPosition = new WorkPosition();

        workPosition.setId(entity.getId())
                    .setDescription(entity.getDescription());

        return workPosition;
    }

    @Override
    public List<WorkPosition> findAll(@NonNull Context context) {
        final Uri uri = UriHelper.buildWorkPositionUri(context);
        final List<WorkPositionEntity> workPositionEntities = workPositionController.listAll(context, uri);
        final List<WorkPosition> workPositions = new ArrayList<>(workPositionEntities.size());
        final WorkPosition workPosition = new WorkPosition();
        WorkPositionEntity entity;

        for(int i = 0; i < workPositionEntities.size(); i++) {
            entity = workPositionEntities.get(i);

            workPosition.setId(entity.getId())
                        .setDescription(entity.getDescription());

            workPositions.add(workPosition);
        }

        return workPositions;
    }

    @Override
    public boolean insert(@NonNull Context context, WorkPosition object) {
        final Uri uri = UriHelper.buildWorkPositionUri(context);
        final WorkPositionEntity entity = new WorkPositionEntity();

        entity.setId(object.getId());
        entity.setDescription(object.getDescription());

        return workPositionController.insert(context, entity, uri);
    }

    @Override
    public boolean update(@NonNull Context context, WorkPosition object) {
        final Uri uri = UriHelper.buildWorkPositionUri(context);
        final WorkPositionEntity entity = new WorkPositionEntity();

        entity.setId(object.getId());
        entity.setDescription(object.getDescription());

        return workPositionController.update(context, entity, uri);
    }

    @Override
    public boolean deleteById(@NonNull Context context, long id) {
        final Uri uri = UriHelper.buildWorkPositionUri(context);
        return workPositionController.delete(context, uri, id);
    }

    public static WorkPositionFacade newInstance() {
        if(null == instance)
            instance = new WorkPositionFacade();
        return instance;
    }
}
