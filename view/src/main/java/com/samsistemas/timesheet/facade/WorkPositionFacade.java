package com.samsistemas.timesheet.facade;

import android.content.Context;
import android.support.annotation.NonNull;

import com.samsistemas.timesheet.controller.base.BaseController;
import com.samsistemas.timesheet.entity.WorkPositionEntity;
import com.samsistemas.timesheet.facade.base.Facade;
import com.samsistemas.timesheet.factory.ControllerFactory;
import com.samsistemas.timesheet.model.WorkPosition;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jonatan.salas
 */
public class WorkPositionFacade implements Facade<WorkPosition> {
    private static WorkPositionFacade instance = null;
    protected BaseController<WorkPositionEntity> workPositionController;

    protected WorkPositionFacade() {
        this.workPositionController = ControllerFactory.getWorkPositionController();
    }

    @Override
    public WorkPosition findById(@NonNull Context context, long id) {
        final WorkPositionEntity entity = workPositionController.get(context, id);
        final WorkPosition workPosition = new WorkPosition();

        workPosition.setId(entity.getWorkPositionId())
                    .setDescription(entity.getDescription());

        return workPosition;
    }

    @Override
    public List<WorkPosition> findAll(@NonNull Context context) {
        final List<WorkPositionEntity> workPositionEntities = workPositionController.listAll(context);
        final List<WorkPosition> workPositions = new ArrayList<>(workPositionEntities.size());
        final WorkPosition workPosition = new WorkPosition();
        WorkPositionEntity entity;

        for(int i = 0; i < workPositionEntities.size(); i++) {
            entity = workPositionEntities.get(i);

            workPosition.setId(entity.getWorkPositionId())
                        .setDescription(entity.getDescription());

            workPositions.add(workPosition);
        }

        return workPositions;
    }

    @Override
    public boolean insert(@NonNull Context context, WorkPosition object) {
        final WorkPositionEntity entity = new WorkPositionEntity();

        entity.setWorkPositionId(object.getId())
              .setDescription(object.getDescription());

        return workPositionController.insert(context, entity);
    }

    @Override
    public boolean update(@NonNull Context context, WorkPosition object) {
        final WorkPositionEntity entity = new WorkPositionEntity();

        entity.setWorkPositionId(object.getId())
                .setDescription(object.getDescription());

        return workPositionController.update(context, entity);
    }

    @Override
    public boolean deleteById(@NonNull Context context, long id) {
        return workPositionController.delete(context, id);
    }

    public static WorkPositionFacade newInstance() {
        if(null == instance)
            instance = new WorkPositionFacade();
        return instance;
    }
}
