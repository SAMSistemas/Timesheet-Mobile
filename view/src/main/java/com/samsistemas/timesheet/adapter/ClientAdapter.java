package com.samsistemas.timesheet.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.samsistemas.timesheet.model.Client;

import java.util.List;

/**
 * @author jonatan.salas
 */
public class ClientAdapter extends BaseAdapter {

    @NonNull
    private Context mContext;

    @Nullable
    private List<Client> mItems;

    public ClientAdapter(@NonNull Context context, @Nullable List<Client> items) {
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
        final Client client = (Client) getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
        }

        final TextView text1 = (TextView) convertView.findViewById(android.R.id.text1);
        text1.setText(client.getName());

        return convertView;
    }

    public void setItems(@Nullable List<Client> items) {
        this.mItems = items;
    }
}
