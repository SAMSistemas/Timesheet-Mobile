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
 * Domain class that represents the data contained in the table Client.
 *
 * @author jonatan.salas
 */
public class Client implements BaseModel<Client>, Parcelable {
    private long clientId;
    private String name;
    private String shortName;
    private boolean enabled;

    public Client() {}

    /**
     * Protected constructor with parameter.
     *
     * @param in - parcel containing class attributes data.
     */
    protected Client(Parcel in) {
        clientId = in.readLong();
        name = in.readString();
        shortName = in.readString();
        enabled = in.readByte() != 0x00;
    }

    /** Attributes setters and getters **/
    public Client setClientId(long clientId) {
        this.clientId = clientId;
        return this;
    }

    public Client setName(String name) {
        this.name = name;
        return this;
    }

    public Client setShortName(String shortName) {
        this.shortName = shortName;
        return this;
    }

    public Client setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
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

    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(clientId);
        dest.writeString(name);
        dest.writeString(shortName);
        dest.writeByte((byte) (enabled ? 0x01 : 0x00));
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Client> CREATOR = new Parcelable.Creator<Client>() {
        @Override
        public Client createFromParcel(Parcel in) {
            return new Client(in);
        }

        @Override
        public Client[] newArray(int size) {
            return new Client[size];
        }
    };

    @Override
    public ContentValues asContentValues(@NonNull Context context) {
        final ContentValues values = new ContentValues(4);
        final int clientEnabled = ConversionUtil.booleanToInt(enabled);

        values.put(context.getString(R.string.client_id), clientId);
        values.put(context.getString(R.string.client_name), name);
        values.put(context.getString(R.string.client_short_name), shortName);
        values.put(context.getString(R.string.client_enabled), clientEnabled);

        return values;
    }

    @Override
    public Client save(@NonNull Context context, Cursor cursor) {
        cursor.moveToFirst();

        final int available = cursor.getInt(cursor.getColumnIndexOrThrow(context.getString(R.string.client_enabled)));
        final boolean clientEnabled = ConversionUtil.intToBoolean(available);

        final Client client = new Client();

        client.setClientId(cursor.getLong(cursor.getColumnIndexOrThrow(context.getString(R.string.client_id))))
              .setName(cursor.getString(cursor.getColumnIndexOrThrow(context.getString(R.string.client_name))))
              .setShortName(cursor.getString(cursor.getColumnIndexOrThrow(context.getString(R.string.client_short_name))))
              .setEnabled(clientEnabled);

        if(!cursor.isClosed())
            cursor.close();

        return client;
    }
}
