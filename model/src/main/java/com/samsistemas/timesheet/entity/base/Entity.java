package com.samsistemas.timesheet.entity.base;

/**
 * Base entity class, all entities have to extend this class.
 *
 * @author jonatan.salas
 */
public class Entity {

    /**
     * The id to be saved.
     */
    private long id;

    /**
     * Id setter as builder pattern.
     *
     * @param id the id associated to this entity
     * @return the entity object, with id set
     */
    public Entity setId(long id) {
        this.id = id;
        return this;
    }

    /**
     * Id getter
     *
     * @return the id set for an entity object
     */
    public long getId() {
        return id;
    }
}
