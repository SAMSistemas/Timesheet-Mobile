package com.samsistemas.timesheet.commons.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.samsistemas.timesheet.commons.model.Project;

import java.util.List;

/**
 * @author jonatan.salas
 */
public class ProjectAdapter extends BaseAdapter {

    @NonNull
    private Context mContext;

    @Nullable
    private List<Project> mItems;

    public ProjectAdapter(@NonNull Context context, @Nullable List<Project> items) {
        this.mContext = context;
        this.mItems = items;
    }

    @Override
    public int getCount() {
        return (null == mItems || mItems.isEmpty()) ? 0 : mItems.size();
    }

    @Override
    public Object getItem(int position) {
        return (null == mItems || mItems.isEmpty()) ? null : mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return (null == mItems || mItems.isEmpty()) ? 0 : mItems.get(position).getId();
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getDropDownView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        final Project project = (Project) getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
        }

        final TextView text1 = (TextView) convertView.findViewById(android.R.id.text1);
        text1.setText(project.getName());

        return convertView;
    }

    public int getPositionById(long id) {
        int position = 0;

        if(null != mItems) {
            for (int i = 0; i < mItems.size(); i++) {
                if (mItems.get(i).getId() == id) {
                    position = i;
                }
            }
        }

        return position;
    }

    public void setItems(@Nullable List<Project> items) {
        this.mItems = items;
    }
}
