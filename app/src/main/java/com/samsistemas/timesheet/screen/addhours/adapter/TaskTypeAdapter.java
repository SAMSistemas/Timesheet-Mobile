package com.samsistemas.timesheet.screen.addhours.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.samsistemas.timesheet.domain.TaskType;
import com.samsistemas.timesheet.screen.addhours.adapter.base.BaseSpinnerAdapter;

import java.util.List;

/**
 * @author jonatan.salas
 */
public class TaskTypeAdapter extends BaseSpinnerAdapter<TaskType> {

    public TaskTypeAdapter(@NonNull Context context, @Nullable List<TaskType> items) {
        super(context, items);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        final TaskType taskType = (TaskType) getItem(position);

        if (null == convertView) {
            convertView = LayoutInflater.from(context).inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
        }

        final TextView text1 = (TextView) convertView.findViewById(android.R.id.text1);
        text1.setText(taskType.getName());

        return convertView;
    }

    @Override
    public int findPositionById(long id) {
        int position = 0;

        if(null != list) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getId() == id) {
                    position = i;
                }
            }
        }

        return position;
    }
}
