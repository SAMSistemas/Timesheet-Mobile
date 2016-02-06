package com.samsistemas.timesheet.data.domain;

import com.orm.SugarRecord;
import com.orm.dsl.NotNull;
import com.orm.dsl.Unique;

import org.parceler.Parcel;

/**
 * Pojo class representing a WorkPosition
 *
 * @author jonatan.salas
 */
@Parcel
public class WorkPosition extends SugarRecord {

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
