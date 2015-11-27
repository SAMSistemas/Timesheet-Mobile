package com.samsistemas.timesheet.entity;

import android.graphics.drawable.Drawable;

import com.samsistemas.timesheet.entity.base.Entity;

/**
 * Domain class that represents the data contained in the table Person.
 *
 * @author jonatan.salas
 */
public class PersonEntity extends Entity {
    private String name;
    private String lastName;
    private String username;
    private String password;
    private long workPositionId;
    private int workHours;
    private Drawable picture;
    private boolean enabled;

    public PersonEntity() {}

    /** Attributes setters and getters **/
    public PersonEntity setName(String name) {
        this.name = name;
        return this;
    }

    public PersonEntity setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public PersonEntity setUsername(String username) {
        this.username = username;
        return this;
    }

    public PersonEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    public PersonEntity setWorkPositionId(long workPositionId) {
        this.workPositionId = workPositionId;
        return this;
    }

    public PersonEntity setWorkHours(int workHours) {
        this.workHours = workHours;
        return this;
    }

    public PersonEntity setPicture(Drawable picture) {
        this.picture = picture;
        return this;
    }

    public PersonEntity setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public long getWorkPositionId() {
        return workPositionId;
    }

    public int getWorkHours() {
        return workHours;
    }

    public Drawable getPicture() {
        return picture;
    }

    public boolean isEnabled() {
        return enabled;
    }
}
