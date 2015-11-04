package com.samsistemas.timesheet.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.samsistemas.timesheet.viewmodel.JobLogViewModel;

import java.util.List;

/**
 * @author jonatan.salas
 */
public class JobLogAdapter extends RecyclerView.Adapter<JobLogAdapter.ViewHolder> {
    private static final int EMPTY_VIEW_TYPE = 10;
    private List<JobLogViewModel> mItems;
    private Context mContext;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case

        public ViewHolder(View v) {
            super(v);
        }
    }

    /***
     *
     * @param context
     * @param items
     */
    public JobLogAdapter(@NonNull Context context, @NonNull List<JobLogViewModel> items) {

    }

    @Override
    public JobLogAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(JobLogAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
}
