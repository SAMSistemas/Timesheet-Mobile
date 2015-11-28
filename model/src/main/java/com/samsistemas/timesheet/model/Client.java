package com.samsistemas.timesheet.model;

/**
 * @author jonatan.salas
 */
public class Client {
    private long id;
    private String name;
    private String shortName;
    private boolean enabled;

    public Client() {}

    public Client setId(long id) {
        this.id = id;
        return this;
    }

    public Client setName(String name) {
        this.name = name;
        return this;
    }

    public Client setShortName(String shortName) {
        this.shortName = shortName;
        return this;
    }

    public Client setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getShortName() {
        return shortName;
    }

    public boolean isEnabled() {
        return enabled;
    }
}
