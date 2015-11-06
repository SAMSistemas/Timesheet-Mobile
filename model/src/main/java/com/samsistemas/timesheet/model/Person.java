package com.samsistemas.timesheet.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.samsistemas.timesheet.data.R;
import com.samsistemas.timesheet.model.base.BaseModel;
import com.samsistemas.timesheet.util.ConversionUtil;

/**
 * Domain class that represents the data contained in the table Person.
 *
 * @author jonatan.salas
 */
public class Person implements BaseModel<Person>, Parcelable {
    private long personId;
    private String name;
    private String lastName;
    private String username;
    private String password;
    private long workPositionId;
    private int workHours;
    private Drawable picture;
    private boolean enabled;

    public Person() {}

    /**
     * Protected constructor with parameter.
     *
     * @param in - parcel containing class attributes data.
     */
    protected Person(Parcel in) {
        personId = in.readLong();
        name = in.readString();
        lastName = in.readString();
        username = in.readString();
        password = in.readString();
        workPositionId = in.readLong();
        workHours = in.readInt();
        byte[] pictureArray = new byte[1024];
        in.readByteArray(pictureArray);
        picture = ConversionUtil.byteArrayToDrawable(pictureArray);
        enabled = in.readByte() != 0x00;
    }

    /** Attributes setters and getters **/
    public Person setPersonId(long personId) {
        this.personId = personId;
        return this;
    }

    public Person setName(String name) {
        this.name = name;
        return this;
    }

    public Person setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Person setUsername(String username) {
        this.username = username;
        return this;
    }

    public Person setPassword(String password) {
        this.password = password;
        return this;
    }

    public Person setWorkPositionId(long workPositionId) {
        this.workPositionId = workPositionId;
        return this;
    }

    public Person setWorkHours(int workHours) {
        this.workHours = workHours;
        return this;
    }

    public Person setPicture(Drawable picture) {
        this.picture = picture;
        return this;
    }

    public Person setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public long getPersonId() {
        return personId;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public long getWorkPositionId() {
        return workPositionId;
    }

    public int getWorkHours() {
        return workHours;
    }

    public Drawable getPicture() {
        return picture;
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
        dest.writeLong(personId);
        dest.writeString(name);
        dest.writeString(lastName);
        dest.writeString(username);
        dest.writeString(password);
        dest.writeLong(workPositionId);
        dest.writeInt(workHours);
        dest.writeByteArray(ConversionUtil.drawableToByteArray(picture));
        dest.writeByte((byte) (enabled ? 0x01 : 0x00));
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Person> CREATOR = new Parcelable.Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    @Override
    public ContentValues asContentValues(@NonNull Context context) {
        final ContentValues values = new ContentValues(7);
        final int personEnabled = ConversionUtil.booleanToInt(enabled);
        final byte[] personPicture = ConversionUtil.drawableToByteArray(picture);

        values.put(context.getString(R.string.person_id), personId);
        values.put(context.getString(R.string.person_name), name);
        values.put(context.getString(R.string.person_last_name), lastName);
        values.put(context.getString(R.string.person_user_name), username);
        values.put(context.getString(R.string.person_password), password);
        values.put(context.getString(R.string.person_work_position_id), workPositionId);
        values.put(context.getString(R.string.person_work_hours), workHours);
        values.put(context.getString(R.string.person_picture), personPicture);
        values.put(context.getString(R.string.person_enabled), personEnabled);

        return values;
    }

    @Override
    public Person save(@NonNull Context context, Cursor cursor) {
        if (null != cursor && cursor.moveToFirst()) {

            final byte[] profile = cursor.getBlob(cursor.getColumnIndexOrThrow(context.getString(R.string.person_picture)));
            final int available = cursor.getInt(cursor.getColumnIndexOrThrow(context.getString(R.string.person_enabled)));
            final Drawable personPicture = ConversionUtil.byteArrayToDrawable(profile);
            final boolean personEnabled = ConversionUtil.intToBoolean(available);

            final Person person = new Person();

            person.setPersonId(cursor.getInt(cursor.getColumnIndexOrThrow(context.getString(R.string.person_id))))
                    .setName(cursor.getString(cursor.getColumnIndexOrThrow(context.getString(R.string.person_name))))
                    .setLastName(cursor.getString(cursor.getColumnIndexOrThrow(context.getString(R.string.person_last_name))))
                    .setUsername(cursor.getString(cursor.getColumnIndexOrThrow(context.getString(R.string.person_user_name))))
                    .setPassword(cursor.getString(cursor.getColumnIndexOrThrow(context.getString(R.string.person_password))))
                    .setWorkPositionId(cursor.getLong(cursor.getColumnIndexOrThrow(context.getString(R.string.person_work_position_id))))
                    .setWorkHours(cursor.getInt(cursor.getColumnIndexOrThrow(context.getString(R.string.person_work_hours))))
                    .setPicture(personPicture)
                    .setEnabled(personEnabled);

            if (!cursor.isClosed())
                cursor.close();

            return person;
        }

        return null;
    }
}
