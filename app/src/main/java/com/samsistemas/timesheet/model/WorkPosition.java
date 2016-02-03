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
     * The id of the workPosition stored in the server
     */
    @NotNull @Unique
    private Long serverId;

    /**
     * The description of the work position
     */
    @NotNull
    private String description;

    /**
     * Public Constructor
     */
    public WorkPosition() { }

    public Long getId() {
        return id;
    }

    public WorkPosition setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getServerId() {
        return serverId;
    }

    public WorkPosition setServerId(Long serverId) {
        this.serverId = serverId;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public WorkPosition setDescription(String description) {
        this.description = description;
        return this;
    }
}
