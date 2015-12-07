package com.cs616.studybuddy_mockup;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


/**
 * Created by ian on 15-08-26.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "notes.db";
    private static final int DATABASE_VERSION = 2;

    /*  Database Tables */
    private EventTable noteTable;

    /**
     * Construct a new database handler.
     * @param context The application context.
     */
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        noteTable = new EventTable(this);
    }

    public EventTable getNoteTable() { return noteTable; }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(noteTable.getCreateSQL());
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        Log.w(DatabaseHandler.class.getName(), "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
        database.execSQL("DROP TABLE IF EXISTS " + noteTable.getTableName());
        onCreate(database);
    }

}
