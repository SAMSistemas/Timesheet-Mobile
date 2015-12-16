package com.samsistemas.timesheet.model;

/**
 * @author jonatan.salas
 */
public class WorkPosition {

    /**
     *
     */
    private long id;

    /**
     *
     */
    private String description;

    /**
     *
     */
    public WorkPosition() { }

    /**
     *
     * @param id
     * @return
     */
    public WorkPosition setId(long id) {
        this.id = id;
        return this;
    }

    /**
     *
     * @param description
     * @return
     */
    public WorkPosition setDescription(String description) {
        this.description = description;
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
    public String getDescription() {
        return description;
    }
}
