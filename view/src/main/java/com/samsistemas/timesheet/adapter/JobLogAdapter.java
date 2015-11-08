package com.samsistemas.timesheet.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Parcel;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.IntentCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.samsistemas.timesheet.R;
import com.samsistemas.timesheet.activity.AddHoursActivity;
import com.samsistemas.timesheet.model.JobLog;
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
        View mItemView;
        TextView mJobLogTask;
        TextView mJobLogDescription;
        TextView mJobLogHours;
        ImageView mIcon;

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
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final ViewHolder viewHolder = (ViewHolder) holder;
        final JobLogViewModel jobLogViewModel = mItems.get(position);

        viewHolder.mItemView.setEnabled(true);
        viewHolder.mItemView.setClickable(true);
        viewHolder.mItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModelAdapter adapter = new ModelAdapter(mContext);
                JobLog jobLog = adapter.getJoblog(jobLogViewModel.getJobLogId());
                Intent nextActivity = new Intent(mContext, AddHoursActivity.class);
                nextActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                nextActivity.putExtra("JOBLOG_TO_EDIT", jobLog);
                mContext.startActivity(nextActivity);
            }
        });

        viewHolder.mIcon.setColorFilter(ContextCompat.getColor(mContext, R.color.material_teal), PorterDuff.Mode.SRC_ATOP);
        viewHolder.mJobLogTask.setText(jobLogViewModel.getTaskTypeViewModel().getTaskTypeName());
        viewHolder.mJobLogDescription.setText(jobLogViewModel.getJobLogObservation());
        viewHolder.mJobLogHours.setText(jobLogViewModel.getJobLogHours() + " hrs");
    }

    @Override
    public int getItemCount() {
        return (null!= mItems && mItems.size() > 0)? mItems.size() : 0;
    }

    public void setItems(List<JobLogViewModel> items) {
        this.mItems = items;
    }
}
