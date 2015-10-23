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
public class WorkPosition implements BaseModel<WorkPosition>, Parcelable {
    private long workPositionId;
    private String description;

    public WorkPosition() {}

    protected WorkPosition(Parcel in) {
        workPositionId = in.readLong();
        description = in.readString();
    }

    /** Attributes setters and getters **/
    public WorkPosition setWorkPositionId(long workPositionId) {
        this.workPositionId = workPositionId;
        return this;
    }

    public WorkPosition setDescription(String description) {
        this.description = description;
        return this;
    }

    public long getWorkPositionId() {
        return workPositionId;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(workPositionId);
        dest.writeString(description);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<WorkPosition> CREATOR = new Parcelable.Creator<WorkPosition>() {
        @Override
        public WorkPosition createFromParcel(Parcel in) {
            return new WorkPosition(in);
        }

        @Override
        public WorkPosition[] newArray(int size) {
            return new WorkPosition[size];
        }
    };

    @Override
    public ContentValues asContentValues(@NonNull Context context) {
        final ContentValues values = new ContentValues(2);

        values.put(context.getString(R.string.work_position_id), workPositionId);
        values.put(context.getString(R.string.work_position_description), description);

        return values;
    }

    @Override
    public WorkPosition save(@NonNull Context context, Cursor cursor) {
        return new WorkPosition()
                .setWorkPositionId(cursor.getLong(cursor.getColumnIndexOrThrow(context.getString(R.string.work_position_id))))
                .setDescription(cursor.getString(cursor.getColumnIndexOrThrow(context.getString(R.string.work_position_description))));
    }
}
