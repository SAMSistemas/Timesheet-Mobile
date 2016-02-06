package com.samsistemas.timesheet.data.domain;

import com.orm.SugarRecord;
import com.orm.dsl.NotNull;
import com.orm.dsl.Unique;

import org.parceler.Parcel;

/**
 * Pojo class representing a TaskType
 *
 * @author jonatan.salas
 */
@Parcel
public class TaskType extends SugarRecord {

    /**
     * The id of the tasktype stored in the server
     */
    @NotNull @Unique
    private Long serverId;

    /**
     * The name of the task type
     */
    @NotNull @Unique
    private String name;

    /**
     * A boolean flag indicating if this is still active
     */
    @NotNull
    private Boolean enabled;

    /**
     * Public constructor
     */
    public TaskType() { }

    public Long getServerId() {
        return serverId;
    }

    public TaskType setServerId(Long serverId) {
        this.serverId = serverId;
        return this;
    }

    public String getName() {
        return name;
    }

    public TaskType setName(String name) {
        this.name = name;
        return this;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public TaskType setEnabled(Boolean enabled) {
        this.enabled = enabled;
        return this;
    }
}
