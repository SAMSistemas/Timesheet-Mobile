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

import java.util.Date;

/**
 * Domain class that represents the data contained in the table Project.
 *
 * @author jonatan.salas
 */
public class Project implements BaseModel<Project>, Parcelable {
    private long projectId;
    private long clientId;
    private String name;
    private String shortName;
    private Date startDate;
    private boolean enabled;

    public Project() {}

    /**
     * Protected constructor with parameter.
     *
     * @param in - parcel containing class attributes data.
     */
    protected Project(Parcel in) {
        clientId = in.readLong();
        projectId = in.readLong();
        name = in.readString();
        shortName = in.readString();
        long tmpStartDate = in.readLong();
        startDate = tmpStartDate != -1 ? new Date(tmpStartDate) : null;
        enabled = in.readByte() != 0x00;
    }

    /** Attribute setters and getters **/
    public Project setProjectId(long projectId) {
        this.projectId = projectId;
        return this;
    }

    public Project setClientId(long clientId) {
        this.clientId = clientId;
        return this;
    }

    public Project setName(String name) {
        this.name = name;
        return this;
    }

    public Project setShortName(String shortName) {
        this.shortName = shortName;
        return this;
    }

    public Project setStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }

    public Project setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public long getProjectId() {
        return projectId;
    }

    public long getClientId() {
        return clientId;
    }

    public String getName() {
        return name;
    }

    public String getShortName() {
        return shortName;
    }

    public Date getStartDate() {
        return startDate;
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
        dest.writeLong(projectId);
        dest.writeLong(clientId);
        dest.writeString(name);
        dest.writeString(shortName);
        dest.writeLong(startDate != null ? startDate.getTime() : -1L);
        dest.writeByte((byte) (enabled ? 0x01 : 0x00));
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Project> CREATOR = new Parcelable.Creator<Project>() {
        @Override
        public Project createFromParcel(Parcel in) {
            return new Project(in);
        }

        @Override
        public Project[] newArray(int size) {
            return new Project[size];
        }
    };

    @Override
    public ContentValues asContentValues(@NonNull Context context) {
        final ContentValues values = new ContentValues(6);
        final int projectEnabled = ConversionUtil.booleanToInt(enabled);

        values.put(context.getString(R.string.project_id), projectId);
        values.put(context.getString(R.string.project_client_id), clientId);
        values.put(context.getString(R.string.project_name), name);
        values.put(context.getString(R.string.project_short_name), shortName);
        values.put(context.getString(R.string.project_start_date), startDate.getTime());
        values.put(context.getString(R.string.project_enabled), projectEnabled);

        return values;
    }

    @Override
    public Project save(@NonNull Context context, Cursor cursor) {
        final int available = cursor.getInt(cursor.getColumnIndexOrThrow(context.getString(R.string.project_enabled)));
        final long millis = cursor.getLong(cursor.getColumnIndexOrThrow(context.getString(R.string.project_start_date)));
        final boolean projectEnabled = ConversionUtil.intToBoolean(available);

        final Project project = new Project();

        project.setProjectId(cursor.getLong(cursor.getColumnIndexOrThrow(context.getString(R.string.project_id))))
               .setClientId(cursor.getLong(cursor.getColumnIndexOrThrow(context.getString(R.string.project_client_id))))
               .setName(cursor.getString(cursor.getColumnIndexOrThrow(context.getString(R.string.project_name))))
               .setShortName(cursor.getString(cursor.getColumnIndexOrThrow(context.getString(R.string.project_short_name))))
               .setStartDate(new Date(millis))
               .setEnabled(projectEnabled);

        if(!cursor.isClosed())
            cursor.close();

        return project;
    }
}
