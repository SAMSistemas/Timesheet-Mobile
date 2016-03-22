package com.samsistemas.timesheet.common.adapter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import com.samsistemas.timesheet.domain.Person;

import java.lang.reflect.Type;

/**
 * @author jonatan.salas
 */
public final class PersonTypeAdapter implements JsonDeserializer<Person> {
    private static PersonTypeAdapter adapter = null;

    private PersonTypeAdapter() { }

    @Override
    public Person deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return null;
    }

    public static PersonTypeAdapter getInstance() {
        return (null == adapter) ? adapter = new PersonTypeAdapter() : adapter;
    }
}
