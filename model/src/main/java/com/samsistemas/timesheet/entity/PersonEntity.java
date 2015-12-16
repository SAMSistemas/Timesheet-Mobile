package com.samsistemas.timesheet.entity;

import android.graphics.drawable.Drawable;

import com.samsistemas.timesheet.entity.base.Entity;

/**
 * Domain class that represents the data contained in the table Person.
 *
 * @author jonatan.salas
 */
public class PersonEntity extends Entity {

    /**
     *
     */
    private String name;

    /**
     *
     */
    private String lastName;

    /**
     *
     */
    private String username;

    /**
     *
     */
    private String password;

    /**
     *
     */
    private long workPositionId;

    /**
     *
     */
    private int workHours;

    /**
     *
     */
    private Drawable picture;

    /**
     *
     */
    private boolean enabled;

    /**
     *
     */
    public PersonEntity() { }

    /**
     *
     * @param name
     * @return
     */
    public PersonEntity setName(String name) {
        this.name = name;
        return this;
    }

    /**
     *
     * @param lastName
     * @return
     */
    public PersonEntity setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    /**
     *
     * @param username
     * @return
     */
    public PersonEntity setUsername(String username) {
        this.username = username;
        return this;
    }

    /**
     *
     * @param password
     * @return
     */
    public PersonEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    /**
     *
     * @param workPositionId
     * @return
     */
    public PersonEntity setWorkPositionId(long workPositionId) {
        this.workPositionId = workPositionId;
        return this;
    }

    /**
     *
     * @param workHours
     * @return
     */
    public PersonEntity setWorkHours(int workHours) {
        this.workHours = workHours;
        return this;
    }

    /**
     *
     * @param picture
     * @return
     */
    public PersonEntity setPicture(Drawable picture) {
        this.picture = picture;
        return this;
    }

    /**
     *
     * @param enabled
     * @return
     */
    public PersonEntity setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return
     */
    public String getLastName() {
        return lastName;
    }

    /**
     *
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @return
     */
    public long getWorkPositionId() {
        return workPositionId;
    }

    /**
     *
     * @return
     */
    public int getWorkHours() {
        return workHours;
    }

    /**
     *
     * @return
     */
    public Drawable getPicture() {
        return picture;
    }

    /**
     *
     * @return
     */
    public boolean isEnabled() {
        return enabled;
    }
}
