package com.samsistemas.timesheet.entity.base;

/**
 * @author jonatan.salas
 */
public class BaseEntity {
    protected long id;

    public BaseEntity setId(long id) {
        this.id = id;
        return this;
    }

    public long getId() {
        return id;
    }
}
