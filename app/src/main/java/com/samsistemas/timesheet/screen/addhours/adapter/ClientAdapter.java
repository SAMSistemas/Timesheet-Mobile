package com.samsistemas.timesheet.screen.addhours.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.samsistemas.timesheet.domain.Client;
import com.samsistemas.timesheet.screen.addhours.adapter.base.BaseSpinnerAdapter;

import java.util.List;

/**
 * @author jonatan.salas
 */
public class ClientAdapter extends BaseSpinnerAdapter<Client> {

    public ClientAdapter(@NonNull Context context, @Nullable List<Client> items) {
        super(context, items);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        final Client client = (Client) getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
        }

        final TextView text1 = (TextView) convertView.findViewById(android.R.id.text1);
        text1.setText(client.getName());

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
