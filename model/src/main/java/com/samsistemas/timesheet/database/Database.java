package com.samsistemas.timesheet.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.samsistemas.timesheet.data.R;

/**
 * Class helper used for database in memory creation.
 *
 * @author jonatan.salas
 */
public class Database extends SQLiteOpenHelper {
    private static Database instance = null;
    private final Context mContext;

    /**
     * Constructor using parameters. It receives the worker application context
     * to generate a new instance of the SQLiteDatabase class.
     *
     * @param context - the context used to create the SQLiteDatabase
     */
    public Database(@NonNull final Context context) {
        super(context, context.getString(R.string.database_name), null, Integer.parseInt(context.getString(R.string.database_version)));
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(mContext.getString(R.string.create_table_client));
        db.execSQL(mContext.getString(R.string.create_table_work_position));
        db.execSQL(mContext.getString(R.string.create_table_person));
        db.execSQL(mContext.getString(R.string.create_table_task_type));
        db.execSQL(mContext.getString(R.string.create_table_project));
        db.execSQL(mContext.getString(R.string.create_table_job_log));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(mContext.getString(R.string.drop_table_client));
        db.execSQL(mContext.getString(R.string.drop_table_work_position));
        db.execSQL(mContext.getString(R.string.drop_table_person));
        db.execSQL(mContext.getString(R.string.drop_table_task_type));
        db.execSQL(mContext.getString(R.string.drop_table_project));
        db.execSQL(mContext.getString(R.string.drop_table_job_log));

        //Calls onCreate to recreate database tables.
        onCreate(db);
    }

    /**
     *
     * @param context
     * @return
     */
    public static Database getInstance(@NonNull Context context) {
        if(null == instance)
            instance = new Database(context);
        return instance;
    }

    /**
     * Method that gets the database current name.
     *
     * @param context - the context used to get the String inside the R static class.
     * @return a String representation of the database name.
     */
    public String getName(@NonNull Context context) {
        return context.getString(R.string.database_name);
    }

    /**
     * Method that gets the database current version.
     *
     * @param context - the context used to get the String inside the R static class.
     * @return an int representation for the database version.
     */
    public int getVersion(@NonNull Context context) {
        return Integer.parseInt(context.getString(R.string.database_version));
    }
}
