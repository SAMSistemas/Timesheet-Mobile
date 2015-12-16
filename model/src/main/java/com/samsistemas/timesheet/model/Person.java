package com.samsistemas.timesheet.model;

import android.graphics.drawable.Drawable;

/**
 * Pojo class representing a Person
 *
 * @author jonatan.salas
 */
public class Person {

    /**
     * The id of the person
     */
    private long id;

    /**
     * The name of the person
     */
    private String name;

    /**
     * The last name of the person
     */
    private String lastName;

    /**
     * The username for this person
     */
    private String username;

    /**
     * The password for this person
     */
    private String password;

    /**
     * The workPosition object associated to this person
     */
    private WorkPosition workPosition;

    /**
     * The hours that work the person
     */
    private int workHours;

    /**
     * The profile picture of the person
     */
    private Drawable picture;

    /**
     * The flag that indicates if this person is still active
     */
    private boolean enabled;

    /**
     * Public constructor
     */
    public Person() { }

    /**
     * Setter as builder pattern
     *
     * @param id the id of the person
     * @return a Person object
     */
    public Person setId(long id) {
        this.id = id;
        return this;
    }

    /**
     * Setter as builder pattern
     *
     * @param name the name of the person
     * @return a Person object
     */
    public Person setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Setter as builder pattern
     *
     * @param lastName the last name of the person
     * @return a Person object
     */
    public Person setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    /**
     * Setter as builder pattern
     *
     * @param username the username for this person
     * @return a Person object
     */
    public Person setUsername(String username) {
        this.username = username;
        return this;
    }

    /**
     * Setter as builder pattern
     *
     * @param password the password for this person
     * @return a Person object
     */
    public Person setPassword(String password) {
        this.password = password;
        return this;
    }

    /**
     * Setter as builder pattern
     *
     * @param workPosition the work position associated to this person
     * @return a Person object
     */
    public Person setWorkPosition(WorkPosition workPosition) {
        this.workPosition = workPosition;
        return this;
    }

    /**
     * Setter as builder pattern
     *
     * @param workHours the hours that the person work
     * @return a Person object
     */
    public Person setWorkHours(int workHours) {
        this.workHours = workHours;
        return this;
    }

    /**
     * Setter as builder pattern
     *
     * @param picture the profile picture of the person
     * @return a Person object
     */
    public Person setPicture(Drawable picture) {
        this.picture = picture;
        return this;
    }

    /**
     * Setter as builder pattern
     *
     * @param enabled the flag that indicates if the person is still active
     * @return a Person object
     */
    public Person setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    /**
     * Getter for id
     *
     * @return the value of the id field
     */
    public long getId() {
        return id;
    }

    /**
     * Getter for name
     *
     * @return the value of the name field
     */
    public String getName() {
        return name;
    }

    /**
     * Getter the last name
     *
     * @return the value of the lastName field
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Getter for username
     *
     * @return the value of the username field
     */
    public String getUsername() {
        return username;
    }

    /**
     * Getter for password
     *
     * @return the value of the password field
     */
    public String getPassword() {
        return password;
    }

    /**
     * Getter for workPosition
     *
     * @return the value of the workPosition field
     */
    public WorkPosition getWorkPosition() {
        return workPosition;
    }

    /**
     * Getter for work hours
     *
     * @return the value of the workHours field
     */
    public int getWorkHours() {
        return workHours;
    }

    /**
     * Getter for picture
     *
     * @return the value of the picture field
     */
    public Drawable getPicture() {
        return picture;
    }

    /**
     * Getter for enabled
     *
     * @return the value of the enabled field
     */
    public boolean isEnabled() {
        return enabled;
    }
}
