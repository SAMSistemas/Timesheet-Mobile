package com.samsistemas.timesheet.domain;

import com.orm.SugarRecord;
import com.orm.dsl.NotNull;
import com.orm.dsl.Unique;

import org.parceler.Parcel;

/**
 * Pojo class representing a Client
 *
 * @author jonatan.salas
 */
@Parcel
public class Client extends SugarRecord {

    /**
     * The id of the client stored in the server
     */
    @NotNull @Unique
    private Long serverId;

    /**
     * The name of the client
     */
    @NotNull @Unique
    private String name;

    /**
     * The short name of the client
     */
    @NotNull @Unique
    private String shortName;

    /**
     * Flag that indicates if the client is still active
     */
    @NotNull
    private Boolean enabled;

    /**
     * Public Constructor
     */
    public Client() { }

    public Long getServerId() {
        return serverId;
    }

    public Client setServerId(Long serverId) {
        this.serverId = serverId;
        return this;
    }

    public String getName() {
        return name;
    }

    public Client setName(String name) {
        this.name = name;
        return this;
    }

    public String getShortName() {
        return shortName;
    }

    public Client setShortName(String shortName) {
        this.shortName = shortName;
        return this;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public Client setEnabled(Boolean enabled) {
        this.enabled = enabled;
        return this;
    }
}
