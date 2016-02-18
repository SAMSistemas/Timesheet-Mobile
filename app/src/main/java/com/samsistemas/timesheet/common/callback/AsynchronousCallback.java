package com.samsistemas.timesheet.common.callback;

/**
 * @author jonatan.salas
 */
public interface AsynchronousCallback<T> {

    T execute();
}
