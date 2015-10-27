package com.samsistemas.timesheet.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.samsistemas.timesheet.data.R;
import com.samsistemas.timesheet.model.base.BaseModel;

/**
 * @author jonatan.salas
 */
public class TaskForPosition implements BaseModel<TaskForPosition>, Parcelable {
    private long workPositionId;
    private long taskTypeId;

    public TaskForPosition() {}

    protected TaskForPosition(Parcel in) {
        workPositionId = in.readLong();
        taskTypeId = in.readLong();
    }

    /** Attribute setters and getters **/
    public TaskForPosition setWorkPositionId(long workPositionId) {
        this.workPositionId = workPositionId;
        return this;
    }

    public TaskForPosition setTaskTypeId(long taskTypeId) {
        this.taskTypeId = taskTypeId;
        return this;
    }

    public long getWorkPositionId() {
        return workPositionId;
    }

    public long getTaskTypeId() {
        return taskTypeId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(workPositionId);
        dest.writeLong(taskTypeId);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<TaskForPosition> CREATOR = new Parcelable.Creator<TaskForPosition>() {
        @Override
        public TaskForPosition createFromParcel(Parcel in) {
            return new TaskForPosition(in);
        }

        @Override
        public TaskForPosition[] newArray(int size) {
            return new TaskForPosition[size];
        }
    };

    @Override
    public ContentValues asContentValues(@NonNull Context context) {
        final ContentValues values = new ContentValues(2);

        values.put(context.getString(R.string.task_type_x_work_position_id), workPositionId);
        values.put(context.getString(R.string.task_type_x_work_position_task_id), taskTypeId);

        return values;
    }

    @Override
    public TaskForPosition save(@NonNull Context context, Cursor cursor) {
        if (null != cursor && cursor.moveToFirst()) {
            return new TaskForPosition()
                    .setWorkPositionId(cursor.getLong(cursor.getColumnIndexOrThrow(context.getString(R.string.task_type_x_work_position_id))))
                    .setTaskTypeId(cursor.getLong(cursor.getColumnIndexOrThrow(context.getString(R.string.task_type_x_work_position_task_id))));
        }

        return null;
    }
}
