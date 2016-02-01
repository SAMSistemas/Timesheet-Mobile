package com.samsistemas.timesheet.model;

import com.orm.dsl.NotNull;
import com.orm.dsl.Table;
import com.orm.dsl.Unique;

import org.parceler.Parcel;

/**
 * Pojo class representing a WorkPosition
 *
 * @author jonatan.salas
 */
@Table
@Parcel
public class WorkPosition {

    /**
     * The identifier of the work position
     */
    @Unique @NotNull
    private Long id;

    /**
     * The description of the work position
     */
    @NotNull
    private String description;

    /**
     * Public Constructor
     */
    public WorkPosition() { }

    /**
     * Setter as builder pattern
     *
     * @param id identifier of the work position
     * @return a WorkPosition object
     */
    public WorkPosition setId(long id) {
        this.id = id;
        return this;
    }

    /**
     * Setter as builder pattern
     *
     * @param description the description of the work position
     * @return a WorkPosition object
     */
    public WorkPosition setDescription(String description) {
        this.description = description;
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
     * Getter for description
     *
     * @return the value of the description field
     */
    public String getDescription() {
        return description;
    }
}
