package com.samsistemas.timesheet.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.samsistemas.timesheet.data.R;
import com.samsistemas.timesheet.model.base.BaseModel;
import com.samsistemas.timesheet.util.ConversionUtil;

/**
 * Domain class that represents the data contained in the table TaskType.
 *
 * @author jonatan.salas
 */
public class TaskType implements BaseModel<TaskType>, Parcelable {
    private long taskTypeId;
    private String name;
    private boolean enabled;

    public TaskType() {}

    /**
     * Protected constructor with parameter.
     *
     * @param in - parcel containing class attributes data.
     */
    protected TaskType(Parcel in) {
        taskTypeId = in.readLong();
        name = in.readString();
        enabled = in.readByte() != 0x00;
    }

    /** Attribute setters and getters **/
    public TaskType setTaskTypeId(long taskTypeId) {
        this.taskTypeId = taskTypeId;
        return this;
    }

    public TaskType setName(String name) {
        this.name = name;
        return this;
    }

    public TaskType setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public long getTaskTypeId() {
        return taskTypeId;
    }

    public String getName() {
        return name;
    }

    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(taskTypeId);
        dest.writeString(name);
        dest.writeByte((byte) (enabled ? 0x01 : 0x00));
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<TaskType> CREATOR = new Parcelable.Creator<TaskType>() {
        @Override
        public TaskType createFromParcel(Parcel in) {
            return new TaskType(in);
        }

        @Override
        public TaskType[] newArray(int size) {
            return new TaskType[size];
        }
    };

    @Override
    public ContentValues asContentValues(@NonNull Context context) {
        final ContentValues values = new ContentValues(3);
        final int taskTypeEnabled = ConversionUtil.booleanToInt(enabled);

        values.put(context.getString(R.string.task_type_id), taskTypeId);
        values.put(context.getString(R.string.task_type_name), name);
        values.put(context.getString(R.string.task_type_enabled), taskTypeEnabled);

        return values;
    }

    @Override
    public TaskType save(@NonNull Context context, Cursor cursor) {
        final int available = cursor.getInt(cursor.getColumnIndexOrThrow(context.getString(R.string.task_type_enabled)));
        final boolean taskTypeEnabled = ConversionUtil.intToBoolean(available);

        final TaskType taskType = new TaskType();

        taskType.setTaskTypeId(cursor.getInt(cursor.getColumnIndexOrThrow(context.getString(R.string.task_type_id))))
                .setName(cursor.getString(cursor.getColumnIndexOrThrow(context.getString(R.string.task_type_name))))
                .setEnabled(taskTypeEnabled);

        if(!cursor.isClosed())
            cursor.close();

        return taskType;
    }
}
