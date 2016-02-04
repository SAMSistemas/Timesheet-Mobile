package com.samsistemas.timesheet.common.model;

import com.orm.dsl.NotNull;
import com.orm.dsl.Table;
import com.orm.dsl.Unique;

import org.parceler.Parcel;

/**
 * @author jonatan.salas
 */
@Table
@Parcel
public class Session {

    @NotNull @Unique
    private Long id;

    @NotNull @Unique
    private Person person;

    @NotNull
    private Boolean active;

    /**
     * Empty constructor
     */
    public Session() { }

    public Long getId() {
        return id;
    }

    public Session setId(Long id) {
        this.id = id;
        return this;
    }

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
