package com.samsistemas.timesheet.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.samsistemas.timesheet.R;
import com.samsistemas.timesheet.model.JobLog;
import com.samsistemas.timesheet.util.ItemTouchHelperAdapter;
import com.samsistemas.timesheet.util.ItemTouchHelperViewHolder;

import java.util.Collections;
import java.util.List;

/**
 * @author jonatan.salas
 */
public class JobLogAdapter extends RecyclerView.Adapter<JobLogAdapter.ViewHolder> implements ItemTouchHelperAdapter {
    private List<JobLog> mItems;
    private final Context mContext;

    /**
     * @author jonatan.salas
     */
    public class ViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {
        final TextView mJobLogTask;
        final TextView mJobLogDescription;
        final TextView mJobLogHours;
        final ImageView mIcon;

        public ViewHolder(View v) {
            super(v);
            mIcon = (ImageView) v.findViewById(R.id.joblog_image);
            mJobLogTask = (TextView) v.findViewById(R.id.joblog_task);
            mJobLogDescription = (TextView) v.findViewById(R.id.joblog_description);
            mJobLogHours = (TextView) v.findViewById(R.id.joblog_hours);
        }

        @Override
        public void onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY);
        }

        @Override
        public void onItemClear() {
            itemView.setBackgroundColor(0);
        }
    }

    public JobLogAdapter(@NonNull Context context, @NonNull List<JobLog> items) {
        this.mContext = context;
        this.mItems = items;
    }

    @Override
    public JobLogAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.job_log_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(JobLogAdapter.ViewHolder holder, final int position) {
        final JobLog jobLog = mItems.get(position);

        holder.mIcon.setColorFilter(ContextCompat.getColor(mContext, R.color.material_teal), PorterDuff.Mode.SRC_ATOP);
        holder.mJobLogTask.setText(jobLog.getTaskType().getName());

        if (jobLog.getObservations().length() <= 17) {
            holder.mJobLogDescription.setText(jobLog.getObservations());
        } else {
            holder.mJobLogDescription.setText(jobLog.getObservations().substring(0, 17));
        }

        final String workedHours = jobLog.getHours() + " hrs";
        holder.mJobLogHours.setText(workedHours);
    }

    @Override
    public int getItemCount() {
        return (null!= mItems && mItems.size() > 0)? mItems.size() : 0;
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mItems, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public void onItemDismiss(int position) {
        mItems.remove(position);
        notifyItemRemoved(position);
    }

    public void setItems(List<JobLog> items) {
        this.mItems = items;
    }
}
