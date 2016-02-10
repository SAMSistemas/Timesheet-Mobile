package com.samsistemas.timesheet.domain;

import com.orm.SugarRecord;
import com.orm.dsl.NotNull;
import com.orm.dsl.Unique;

import org.parceler.Parcel;

/**
 * Pojo class representing a Person
 *
 * @author jonatan.salas
 */
@Parcel
public class Person extends SugarRecord {

    /**
     * The id of the person stored in the server
     */
    @NotNull @Unique
    private Long serverId;

    /**
     * The name of the person
     */
    @NotNull
    private String name;

    /**
     * The last name of the person
     */
    @NotNull
    private String lastName;

    /**
     * The username for this person
     */
    @NotNull @Unique
    private String username;

    /**
     * The password for this person
     */
    @NotNull
    private String password;

    /**
     * The workPosition object associated to this person
     */
    @NotNull
    private WorkPosition workPosition;

    /**
     * The hours that work the person
     */
    private int workHours;

    /**
     * The profile picture of the person
     */
    private Byte[] picture;

    /**
     * The flag that indicates if this person is still active
     */
    @NotNull
    private Boolean enabled;

    /**
     * Public constructor
     */
    public Person() { }

    public Long getServerId() {
        return serverId;
    }

    public Person setServerId(Long serverId) {
        this.serverId = serverId;
        return this;
    }

    public String getName() {
        return name;
    }

    public Person setName(String name) {
        this.name = name;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Person setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public Person setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Person setPassword(String password) {
        this.password = password;
        return this;
    }

    public WorkPosition getWorkPosition() {
        return workPosition;
    }

    public Person setWorkPosition(WorkPosition workPosition) {
        this.workPosition = workPosition;
        return this;
    }

    public int getWorkHours() {
        return workHours;
    }

    public Person setWorkHours(int workHours) {
        this.workHours = workHours;
        return this;
    }

    public Byte[] getPicture() {
        return picture;
    }

    public Person setPicture(Byte[] picture) {
        this.picture = picture;
        return this;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public Person setEnabled(Boolean enabled) {
        this.enabled = enabled;
        return this;
    }
}
