package com.samsistemas.timesheet.adapter;

import android.content.Context;
import android.graphics.PorterDuff;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.samsistemas.timesheet.R;
import com.samsistemas.timesheet.viewmodel.JobLogViewModel;

import java.util.List;

/**
 * @author jonatan.salas
 */
public class JobLogAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<JobLogViewModel> mItems;
    private Context mContext;

    /**
     * @author jonatan.salas
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView mJobLogTask;
        TextView mJobLogDescription;
        TextView mJobLogHours;
        ImageView mIcon;

        public ViewHolder(View v) {
            super(v);
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
    public JobLogAdapter(@NonNull Context context, @Nullable List<JobLogViewModel> items) {
        this.mContext = context;
        this.mItems = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.job_log_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final ViewHolder viewHolder = (ViewHolder) holder;
        final JobLogViewModel jobLogViewModel = mItems.get(position);

        viewHolder.mIcon.setColorFilter(ContextCompat.getColor(mContext, R.color.material_teal), PorterDuff.Mode.SRC_ATOP);
        viewHolder.mJobLogTask.setText(jobLogViewModel.getTaskTypeViewModel().getTaskTypeName());
        viewHolder.mJobLogDescription.setText(jobLogViewModel.getJobLogObservation());
        viewHolder.mJobLogHours.setText(jobLogViewModel.getJobLogHours() + " hrs");
    }

    @Override
    public int getItemCount() {
        return (null!= mItems && mItems.size() > 0)? mItems.size() : 0;
    }
}
