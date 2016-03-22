package com.samsistemas.timesheet.screen.addhours.adapter.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * @author jonatan.salas
 */
public abstract class BaseSpinnerAdapter<T> extends BaseAdapter {

    @NonNull
    private Context context;

    @Nullable
    private List<T> list;

    public BaseSpinnerAdapter(@NonNull Context ctx, @Nullable List<T> list) {
        this.context = ctx;
        this.list = list;
    }

    @Override
    public int getCount() {
        return (null == list || list.isEmpty()) ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return (null == list || list.isEmpty()) ? null : list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return (null == list || list.isEmpty()) ? 0 : position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getDropDownView(position, convertView, parent);
    }

    public abstract int findPositionById(long id);

    @Nullable
    public List<T> getList() {
        return list;
    }

    public void setList(@Nullable List<T> list) {
        this.list = list;
        this.notifyDataSetChanged();
    }

    @NonNull
    public Context getContext() {
        return context;
    }

    public void setContext(@NonNull Context context) {
        this.context = context;
    }
}
