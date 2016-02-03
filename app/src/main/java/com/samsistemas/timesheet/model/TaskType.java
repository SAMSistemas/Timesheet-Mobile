package com.samsistemas.timesheet.model;

import com.orm.dsl.NotNull;
import com.orm.dsl.Table;
import com.orm.dsl.Unique;

import org.parceler.Parcel;

/**
 * Pojo class representing a TaskType
 *
 * @author jonatan.salas
 */
@Table
@Parcel
public class TaskType {

    /**
     * The id of the task type
     */
    @NotNull @Unique
    private Long id;

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

    public Long getId() {
        return id;
    }

    public TaskType setId(Long id) {
        this.id = id;
        return this;
    }

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
