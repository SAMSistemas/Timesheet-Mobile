package com.samsistemas.timesheet.model;

import android.graphics.drawable.Drawable;

/**
 * @author jonatan.salas
 */
public class Person {

    /**
     *
     */
    private long id;

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
    private WorkPosition workPosition;

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
    public Person() { }

    /**
     *
     * @param id
     * @return
     */
    public Person setId(long id) {
        this.id = id;
        return this;
    }

    /**
     *
     * @param name
     * @return
     */
    public Person setName(String name) {
        this.name = name;
        return this;
    }

    /**
     *
     * @param lastName
     * @return
     */
    public Person setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    /**
     *
     * @param username
     * @return
     */
    public Person setUsername(String username) {
        this.username = username;
        return this;
    }

    /**
     *
     * @param password
     * @return
     */
    public Person setPassword(String password) {
        this.password = password;
        return this;
    }

    /**
     *
     * @param workPosition
     * @return
     */
    public Person setWorkPosition(WorkPosition workPosition) {
        this.workPosition = workPosition;
        return this;
    }

    /**
     *
     * @param workHours
     * @return
     */
    public Person setWorkHours(int workHours) {
        this.workHours = workHours;
        return this;
    }

    /**
     *
     * @param picture
     * @return
     */
    public Person setPicture(Drawable picture) {
        this.picture = picture;
        return this;
    }

    /**
     *
     * @param enabled
     * @return
     */
    public Person setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    /**
     *
     * @return
     */
    public long getId() {
        return id;
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
    public WorkPosition getWorkPosition() {
        return workPosition;
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
