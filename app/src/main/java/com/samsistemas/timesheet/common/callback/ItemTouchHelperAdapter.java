package com.samsistemas.timesheet.common.callback;

/**
 * Interface to listen for a move or dismissal event from a Callback.
 *
 * @author jonatan.salas
 */
public interface ItemTouchHelperAdapter {

    boolean onItemMove(int fromPosition, int toPosition);

    void onItemDismiss(int position);
}
