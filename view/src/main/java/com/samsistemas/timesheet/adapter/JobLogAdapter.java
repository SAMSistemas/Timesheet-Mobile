package com.samsistemas.timesheet.adapter;

import android.content.Context;
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

import java.util.List;

/**
 * @author jonatan.salas
 */
public class JobLogAdapter extends RecyclerView.Adapter<JobLogAdapter.ViewHolder> {
    private List<JobLog> mItems;
    private final Context mContext;

    /**
     * @author jonatan.salas
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        final View mItemView;
        final TextView mJobLogTask;
        final TextView mJobLogDescription;
        final TextView mJobLogHours;
        final ImageView mIcon;

        public ViewHolder(View v) {
            super(v);
            mItemView = v;
            mIcon = (ImageView) v.findViewById(R.id.joblog_image);
            mJobLogTask = (TextView) v.findViewById(R.id.joblog_task);
            mJobLogDescription = (TextView) v.findViewById(R.id.joblog_description);
            mJobLogHours = (TextView) v.findViewById(R.id.joblog_hours);
        }
    }

    /***
     *
     * @param context
     * @param items
     */
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

        holder.mItemView.setEnabled(true);
        holder.mItemView.setClickable(true);
        holder.mItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO JS: add adapter.
            }
        });

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

    public void setItems(List<JobLog> items) {
        this.mItems = items;
    }
}
