package com.samsistemas.timesheet.domain;

import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;
import com.orm.dsl.NotNull;
import com.orm.dsl.Unique;

import org.parceler.Parcel;

import java.util.Date;

/**
 * Pojo class representing a Project
 *
 * @author jonatan.salas
 */
@Parcel
public class Project extends SugarRecord {

    /**
     * The id of the project stored in the server
     */
    @NotNull
    @Unique
    @SerializedName("id")
    private Long serverId;

    /**
     * The Client object associated to this Project
     */
    @NotNull
    @SerializedName("client")
    private Client client;

    /**
     * The name of the project
     */
    @NotNull
    @Unique
    @SerializedName("name")
    private String name;

    /**
     * The short name of the project
     */
    @NotNull
    @Unique
    @SerializedName("short_name")
    private String shortName;

    /**
     * The start date of the project
     */
    @NotNull
    @SerializedName("start_date")
    private Date startDate;

    /**
     * The flag that verifies if this is still active
     */
    @NotNull
    @SerializedName("enabled")
    private Boolean enabled;

    /**
     * Public Constructor
     */
    public Project() { }

    public Long getServerId() {
        return serverId;
    }

    public Project setServerId(Long serverId) {
        this.serverId = serverId;
        return this;
    }

    public Client getClient() {
        return client;
    }

    public Project setClient(Client client) {
        this.client = client;
        return this;
    }

    public String getName() {
        return name;
    }

    public Project setName(String name) {
        this.name = name;
        return this;
    }

    public String getShortName() {
        return shortName;
    }

    public Project setShortName(String shortName) {
        this.shortName = shortName;
        return this;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Project setStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public Project setEnabled(Boolean enabled) {
        this.enabled = enabled;
        return this;
    }
}
