package com.samsistemas.timesheet.util;


/**
 * Interface to notify an item ViewHolder of relevant callbacks from {@link
 * android.support.v7.widget.helper.ItemTouchHelper.Callback}.
 *
 * @author jonatan.salas
 */
public interface ItemTouchHelperViewHolder {

    void onItemSelected();

    void onItemClear();
}
