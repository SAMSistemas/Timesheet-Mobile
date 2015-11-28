package com.samsistemas.timesheet.model;

import android.graphics.drawable.Drawable;

/**
 * @author jonatan.salas
 */
public class Person {
    private long id;
    private String name;
    private String lastName;
    private String username;
    private String password;
    private WorkPosition workPosition;
    private int workHours;
    private Drawable picture;
    private boolean enabled;

    public Person() {}

    public Person setId(long id) {
        this.id = id;
        return this;
    }

    public Person setName(String name) {
        this.name = name;
        return this;
    }

    public Person setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Person setUsername(String username) {
        this.username = username;
        return this;
    }

    public Person setPassword(String password) {
        this.password = password;
        return this;
    }

    public Person setWorkPosition(WorkPosition workPosition) {
        this.workPosition = workPosition;
        return this;
    }

    public Person setWorkHours(int workHours) {
        this.workHours = workHours;
        return this;
    }

    public Person setPicture(Drawable picture) {
        this.picture = picture;
        return this;
    }

    public Person setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public long getId() {
        return id;
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

    public WorkPosition getWorkPosition() {
        return workPosition;
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
