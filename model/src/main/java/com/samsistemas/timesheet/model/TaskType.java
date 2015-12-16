package com.samsistemas.timesheet.model;

/**
 * @author jonatan.salas
 */
public class TaskType {

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
    private boolean enabled;

    /**
     *
     */
    public TaskType() { }

    /**
     *
     * @param id
     * @return
     */
    public TaskType setId(long id) {
        this.id = id;
        return this;
    }

    /**
     *
     * @param name
     * @return
     */
    public TaskType setName(String name) {
        this.name = name;
        return this;
    }

    /**
     *
     * @param enabled
     * @return
     */
    public TaskType setEnabled(boolean enabled) {
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
    public boolean isEnabled() {
        return enabled;
    }
}
