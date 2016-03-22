package com.samsistemas.timesheet.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.orm.dsl.NotNull;
import com.orm.dsl.Table;
import com.orm.dsl.Unique;

import org.parceler.Parcel;

/**
 * Pojo class representing a Person
 *
 * @author jonatan.salas
 */
@Parcel
@Table
public class Person {

    @NotNull
    @Unique
    private Long id;

    /**
     * The id of the person stored in the server
     */
    @NotNull
    @Unique
    @SerializedName("id")
    private Long serverId;

    /**
     * The name of the person
     */
    @NotNull
    @SerializedName("name")
    private String name;

    /**
     * The last name of the person
     */
    @NotNull
    @SerializedName("lastname")
    private String lastName;

    /**
     * The username for this person
     */
    @NotNull
    @Unique
    @SerializedName("username")
    private String username;

    /**
     * The workPosition object associated to this person
     */
    @NotNull
    @SerializedName("work_position")
    private String workPosition;

    /**
     * The hours that work the person
     */
    @SerializedName("work_hours")
    private int workHours;

    /**
     * The profile picture of the person
     */
    private transient Byte[] picture;

    /**
     * The flag that indicates if this person is still active
     */
    @NotNull
    @SerializedName("enabled")
    private Boolean enabled;

    /**
     * Public constructor
     */
    public Person() { }

    public Long getId() {
        return id;
    }

    public Person setId(Long id) {
        this.id = id;
        return this;
    }

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

    public String getWorkPosition() {
        return workPosition;
    }

    public Person setWorkPosition(String workPosition) {
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
