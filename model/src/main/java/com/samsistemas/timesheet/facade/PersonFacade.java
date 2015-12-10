package com.samsistemas.timesheet.facade;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.samsistemas.timesheet.controller.Controller;
import com.samsistemas.timesheet.entity.PersonEntity;
import com.samsistemas.timesheet.facade.base.Facade;
import com.samsistemas.timesheet.factory.ControllerFactory;
import com.samsistemas.timesheet.helper.UriHelper;
import com.samsistemas.timesheet.model.Person;
import com.samsistemas.timesheet.model.WorkPosition;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jonatan.salas
 */
public class PersonFacade implements Facade<Person> {
    private static PersonFacade instance = null;
    private final Controller<PersonEntity> personController;
    private final Facade<WorkPosition> workPositionFacade;

    private PersonFacade() {
        this.personController = ControllerFactory.getPersonController();
        this.workPositionFacade = WorkPositionFacade.newInstance();
    }

    @Override
    public Person findById(@NonNull Context context, long id) {
        final Uri uri = UriHelper.buildPersonUriWithId(context, id);
        final PersonEntity entity = personController.get(context, uri);
        final Person person = new Person();

        if (null != entity) {
            final WorkPosition workPosition = workPositionFacade.findById(context, entity.getWorkPositionId());

            person.setId(entity.getId())
                  .setName(entity.getName())
                  .setLastName(entity.getLastName())
                  .setUsername(entity.getUsername())
                  .setPassword(entity.getPassword())
                  .setWorkPosition(workPosition)
                  .setPicture(entity.getPicture())
                  .setWorkHours(entity.getWorkHours())
                  .setEnabled(entity.isEnabled());
        }

        return person;
    }

    @Override
    public List<Person> findAll(@NonNull Context context) {
        final Uri uri = UriHelper.buildPersonUri(context);
        final List<PersonEntity> personEntities = personController.listAll(context, uri);
        final List<Person> persons = new ArrayList<>(personEntities.size());
        final Person person = new Person();
        WorkPosition workPosition;
        PersonEntity entity;

        for (int i = 0; i < personEntities.size(); i++) {
            entity = personEntities.get(i);
            workPosition = workPositionFacade.findById(context, entity.getWorkPositionId());

            person.setId(entity.getId())
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
    public boolean insert(@NonNull Context context, Person person) {
        final Uri uri = UriHelper.buildPersonUri(context);
        final PersonEntity entity = new PersonEntity();

        entity.setId(person.getId());
        entity.setName(person.getName())
              .setLastName(person.getLastName())
              .setUsername(person.getUsername())
              .setPassword(person.getPassword())
              .setPicture(person.getPicture())
              .setWorkHours(person.getWorkHours())
              .setWorkPositionId(person.getWorkPosition().getId())
              .setEnabled(person.isEnabled());

        return personController.insert(context, entity, uri);
    }

    @Override
    public boolean update(@NonNull Context context, Person person) {
        final Uri uri = UriHelper.buildPersonUri(context);
        final PersonEntity entity = new PersonEntity();

        entity.setId(person.getId());
        entity.setName(person.getName())
              .setLastName(person.getLastName())
              .setUsername(person.getUsername())
              .setPassword(person.getPassword())
              .setPicture(person.getPicture())
              .setWorkHours(person.getWorkHours())
              .setWorkPositionId(person.getWorkPosition().getId())
              .setEnabled(person.isEnabled());

        return personController.update(context, entity, uri);
    }

    @Override
    public boolean deleteById(@NonNull Context context, long id) {
        final Uri uri = UriHelper.buildPersonUri(context);
        return personController.delete(context, uri, id);
    }

    public static PersonFacade newInstance() {
        if (null == instance) {
            instance = new PersonFacade();
        }
        return instance;
    }
}
