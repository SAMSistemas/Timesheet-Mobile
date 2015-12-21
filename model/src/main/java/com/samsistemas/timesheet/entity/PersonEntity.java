package com.samsistemas.timesheet.entity;

import android.graphics.drawable.Drawable;

import com.samsistemas.timesheet.entity.base.Entity;

/**
 * Entity that represents a Person table row content
 *
 * @author jonatan.salas
 */
public class PersonEntity extends Entity {

    /**
     * The name of the person
     */
    private String name;

    /**
     * The last name of the person
     */
    private String lastName;

    /**
     * The username for the person
     */
    private String username;

    /**
     * The password for the person
     */
    private String password;

    /**
     * The id of the work position to reference
     */
    private long workPositionId;

    /**
     * The hours that work the person
     */
    private int workHours;

    /**
     * Image of profile for the person
     */
    private Drawable picture;

    /**
     * A boolean flag that validates if the person is still active
     */
    private boolean enabled;

    /**
     * Public Constructor
     */
    public PersonEntity() { }

    /**
     * Setter as builder pattern
     *
     * @param name the name of the person
     * @return a PersonEntity object
     */
    public PersonEntity setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Setter as builder pattern
     *
     * @param lastName the last name of the person
     * @return a PersonEntity object
     */
    public PersonEntity setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    /**
     * Setter as builder pattern
     *
     * @param username the username of the person
     * @return a PersonEntity object
     */
    public PersonEntity setUsername(String username) {
        this.username = username;
        return this;
    }

    /**
     * Setter as builder pattern
     *
     * @param password the password of the person
     * @return a PersonEntity object
     */
    public PersonEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    /**
     * Setter as builder pattern
     *
     * @param workPositionId the work position id of the person
     * @return a PersonEntity object
     */
    public PersonEntity setWorkPositionId(long workPositionId) {
        this.workPositionId = workPositionId;
        return this;
    }

    /**
     * Setter as builder pattern
     *
     * @param workHours the hours that work the person
     * @return a PersonEntity object
     */
    public PersonEntity setWorkHours(int workHours) {
        this.workHours = workHours;
        return this;
    }

    /**
     * Setter as builder pattern
     *
     * @param picture the profile image of the person
     * @return a PersonEntity object
     */
    public PersonEntity setPicture(Drawable picture) {
        this.picture = picture;
        return this;
    }

    /**
     * Setter as builder pattern
     *
     * @param enabled the flag that indicates if the person is active
     * @return a PersonEntity object
     */
    public PersonEntity setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    /**
     * Getter for Name
     *
     * @return the value of the field name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for LastName
     *
     * @return the value of the field lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Getter for Username
     *
     * @return the value of the field username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Getter for Password
     *
     * @return the value of the field password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Getter for WorkPositionId
     *
     * @return the value of the field workPositionId
     */
    public long getWorkPositionId() {
        return workPositionId;
    }

    /**
     * Getter for WorkHours
     *
     * @return the value of the field workHours
     */
    public int getWorkHours() {
        return workHours;
    }

    /**
     * Getter for Picture
     *
     * @return the value of the field picture
     */
    public Drawable getPicture() {
        return picture;
    }

    /**
     * Getter for Enabled
     *
     * @return the value of the field enabled
     */
    public boolean isEnabled() {
        return enabled;
    }
}
