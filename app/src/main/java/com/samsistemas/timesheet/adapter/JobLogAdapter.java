package com.samsistemas.timesheet.adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.IntentCompat;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.samsistemas.timesheet.R;
import com.samsistemas.timesheet.activity.AddHoursActivity;
import com.samsistemas.timesheet.animation.ScaleUpAnimator;
import com.samsistemas.timesheet.model.JobLog;
import com.samsistemas.timesheet.util.ItemTouchHelperAdapter;
import com.samsistemas.timesheet.util.ItemTouchHelperViewHolder;

import static com.samsistemas.timesheet.util.AppConstants.EDIT_MODE_KEY;
import static com.samsistemas.timesheet.util.AppConstants.DATE_KEY;
import static com.samsistemas.timesheet.util.AppConstants.JOBLOG_ID_KEY;
import static com.samsistemas.timesheet.util.AppConstants.DATE_TEMPLATE;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/**
 * @author jonatan.salas
 */
public class JobLogAdapter extends RecyclerView.Adapter<JobLogAdapter.ViewHolder> implements ItemTouchHelperAdapter {
    private List<JobLog> mItems;
    private final Activity mContext;

    /**
     * @author jonatan.salas
     */
    public class ViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder, View.OnClickListener {
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
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(final View v) {
            PopupMenu popup = new PopupMenu(v.getContext(), v);
            popup.inflate(R.menu.edit_menu);
            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    switch(item.getItemId()) {
                        case R.id.action_mode_edit:
                            Intent addHoursIntent = new Intent(mContext.getApplicationContext(), AddHoursActivity.class);
                            final JobLog jobLog = mItems.get(getAdapterPosition());
                            String dateString = new SimpleDateFormat(DATE_TEMPLATE, Locale.getDefault()).format(jobLog.getWorkDate());

                            addHoursIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            addHoursIntent.putExtra(DATE_KEY, dateString);
                            addHoursIntent.putExtra(EDIT_MODE_KEY, true);
                            addHoursIntent.putExtra(JOBLOG_ID_KEY, jobLog.getId());

                            Bundle options = ScaleUpAnimator.newInstance().saveAnimation(v);
                            ActivityCompat.startActivity(mContext, addHoursIntent, options);
                    }

                    return true;
                }
            });
            popup.show();
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

    public JobLogAdapter(@NonNull Activity context, @NonNull List<JobLog> items) {
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
