package com.samsistemas.timesheet.domain;

import com.orm.SugarRecord;
import com.orm.dsl.NotNull;
import com.orm.dsl.Unique;

import org.parceler.Parcel;

/**
 * @author jonatan.salas
 */
@Parcel
public class Session extends SugarRecord {

    @NotNull @Unique
    private Person person;

    @NotNull
    private Boolean active;

    /**
     * Empty constructor
     */
    public Session() { }

    public Person getPerson() {
        return person;
    }

    public Session setPerson(Person person) {
        this.person = person;
        return this;
    }

    public Boolean getActive() {
        return active;
    }

    public Session setActive(Boolean active) {
        this.active = active;
        return this;
    }
}
