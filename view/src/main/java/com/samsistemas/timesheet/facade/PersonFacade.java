package com.samsistemas.timesheet.facade;

import android.content.Context;
import android.support.annotation.NonNull;

import com.samsistemas.timesheet.controller.base.BaseController;
import com.samsistemas.timesheet.entity.PersonEntity;
import com.samsistemas.timesheet.facade.base.Facade;
import com.samsistemas.timesheet.factory.ControllerFactory;
import com.samsistemas.timesheet.model.Person;
import com.samsistemas.timesheet.model.WorkPosition;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jonatan.salas
 */
public class PersonFacade implements Facade<Person> {
    private static PersonFacade instance = null;
    protected BaseController<PersonEntity> personController;
    protected Facade<WorkPosition> workPositionFacade;

    protected PersonFacade() {
        this.personController = ControllerFactory.getPersonController();
        this.workPositionFacade = WorkPositionFacade.newInstance();
    }

    @Override
    public Person findById(@NonNull Context context, long id) {
        final PersonEntity entity = personController.get(context, id);
        final WorkPosition workPosition = workPositionFacade.findById(context, entity.getWorkPositionId());
        final Person person = new Person();

        person.setId(entity.getPersonId())
              .setName(entity.getName())
              .setLastName(entity.getLastName())
              .setUsername(entity.getUsername())
              .setPassword(entity.getPassword())
              .setWorkPosition(workPosition)
              .setPicture(entity.getPicture())
              .setWorkHours(entity.getWorkHours())
              .setEnabled(entity.isEnabled());

        return person;
    }

    @Override
    public List<Person> findAll(@NonNull Context context) {
        final List<PersonEntity> personEntities = personController.listAll(context);
        final List<Person> persons = new ArrayList<>(personEntities.size());
        final Person person = new Person();
        WorkPosition workPosition;
        PersonEntity entity;

        for(int i = 0; i < personEntities.size(); i++) {
            entity = personEntities.get(i);
            workPosition = workPositionFacade.findById(context, entity.getWorkPositionId());

            person.setId(entity.getPersonId())
                  .setName(entity.getName())
                  .setLastName(entity.getLastName())
                  .setUsername(entity.getUsername())
                  .setPassword(entity.getPassword())
                  .setWorkPosition(workPosition)
                  .setPicture(entity.getPicture())
                  .setWorkHours(entity.getWorkHours())
                  .setEnabled(entity.isEnabled());

            persons.add(person);
        }

        return persons;
    }

    @Override
    public boolean insert(@NonNull Context context, Person object) {
        final PersonEntity entity = new PersonEntity();

        entity.setPersonId(object.getId())
              .setName(object.getName())
              .setLastName(object.getLastName())
              .setUsername(object.getUsername())
              .setPassword(object.getPassword())
              .setPicture(object.getPicture())
              .setWorkHours(object.getWorkHours())
              .setWorkPositionId(object.getWorkPosition().getId())
              .setEnabled(object.isEnabled());

        return personController.insert(context, entity);
    }

    @Override
    public boolean update(@NonNull Context context, Person object) {
        final PersonEntity entity = new PersonEntity();

        entity.setPersonId(object.getId())
              .setName(object.getName())
              .setLastName(object.getLastName())
              .setUsername(object.getUsername())
              .setPassword(object.getPassword())
              .setPicture(object.getPicture())
              .setWorkHours(object.getWorkHours())
              .setWorkPositionId(object.getWorkPosition().getId())
              .setEnabled(object.isEnabled());

        return personController.update(context, entity);
    }

    @Override
    public boolean deleteById(@NonNull Context context, long id) {
        return personController.delete(context, id);
    }

    public static PersonFacade newInstance() {
        if(null == instance)
            instance = new PersonFacade();
        return instance;
    }
}
