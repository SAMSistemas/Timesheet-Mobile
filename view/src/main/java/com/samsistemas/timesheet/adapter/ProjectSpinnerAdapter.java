package com.samsistemas.timesheet.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.samsistemas.timesheet.R;
import com.samsistemas.timesheet.viewmodel.ProjectViewModel;
import com.samsistemas.timesheet.viewmodel.TaskTypeViewModel;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * @author jonatan.salas
 */
public class ProjectSpinnerAdapter extends BaseAdapter {
    private WeakReference<Context> mContextReference;
    private List<ProjectViewModel> mItems;

    /**
     * Default Constructor with params.
     *
     * @param context - the context used to get the LayoutInflater object.
     * @param items - list with items to populate.
     */
    public ProjectSpinnerAdapter(@NonNull Context context, @NonNull List<ProjectViewModel> items) {
        this.mContextReference = new WeakReference<>(context);
        this.mItems = items;
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mItems.get(position).getProjectId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ProjectViewModel viewModel = (ProjectViewModel) getItem(position);
        View view;

        if(null == convertView) {
            view = LayoutInflater.from(mContextReference.get()).inflate(R.layout.project_item, parent, false);
        } else {
            view = convertView;
        }

        TextView textView = (TextView) view.findViewById(R.id.task_text);
        textView.setText(viewModel.getProjectName());

        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent);
    }
}
