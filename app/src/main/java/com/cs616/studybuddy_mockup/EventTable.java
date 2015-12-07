package com.cs616.studybuddy_mockup;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import hirondelle.date4j.DateTime;

/**
 * Created by Alex on 9/15/2015.
 */
public class EventTable {
    private static final String TABLE_NAME = "event";
    private static final String COLUMN_NAME_TITLE = "title";
    private static final String COLUMN_NAME_DATE = "eventDate";
    private static final String COLUMN_NAME_ID = "id";

    private static final String[] allColumns = { COLUMN_NAME_ID, COLUMN_NAME_TITLE, COLUMN_NAME_DATE};

    // DatabaseHandler creation sql statement
    private static final String CREATE_TABLE = "create table " + TABLE_NAME +
            "(_id integer primary key autoincrement, " +
            COLUMN_NAME_TITLE + " text not null," +
            COLUMN_NAME_DATE + " text, " +
            COLUMN_NAME_ + " integer not null);";

    private DatabaseHandler databaseHandle;

    public EventTable(DatabaseHandler databaseHandle) {
        this.databaseHandle = databaseHandle;
    }

    public String getCreateSQL() {
        return CREATE_TABLE;
    }

    public String getTableName() {
        return TABLE_NAME;
    }

    private Event cursorToNote(Cursor cursor) throws ParseException {
        Event project = new Event();
        project.setId(cursor.getLong(0));
        project.setTitle(cursor.getString(1));
        project.setEventDate(new DateTime(cursor.getString(2)));
        return project;
    }

    public void createNote(Event event) {

        SQLiteDatabase database = databaseHandle.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_TITLE, event.getTitle());
        values.put(COLUMN_NAME_DATE, CalendarActivity.formatter.format(event.getEventDate()));


        long insertId = database.insertOrThrow(TABLE_NAME, null, values);
        category.setId(insertId);

        database.close();
    }

    public Event readNote(long id) {
        SQLiteDatabase db = databaseHandle.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, new String[] { "_id", COLUMN_NAME_TITLE, COLUMN_NAME_COMMENT, COLUMN_NAME_CATEGORYID }, "_id =?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Event note = cursorToNote(cursor);
        return note;
    }

    public Event readNoteByTitle(String title) {
        SQLiteDatabase db = databaseHandle.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, new String[] { "_id", COLUMN_NAME_TITLE, COLUMN_NAME_COMMENT, COLUMN_NAME_CATEGORYID }, COLUMN_NAME_TITLE + " =?",
                new String[] { title }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Note note = cursorToNote(cursor);
        return note;
    }

    public List<Event> getAllNotes() {
        SQLiteDatabase database = databaseHandle.getReadableDatabase();
        List<Event> comments = new ArrayList<>();

        Cursor cursor = database.query(TABLE_NAME, allColumns, null, null, null, null, null);
        if(cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                comments.add(cursorToNote(cursor));
                cursor.moveToNext();
            }
            // make sure to close the cursor
            cursor.close();
        }
        return comments;
    }

    /**
     * Get the number of rows in the table.
     * @return The number of rows in the table.
     */
    public int getNotesCount() {
        return -1;
    }


    public void updateNotes(Note note) {
        SQLiteDatabase database = databaseHandle.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("_id", note.getId());
        values.put(COLUMN_NAME_TITLE, note.getTitle());
        values.put(COLUMN_NAME_COMMENT, note.getComment());
        values.put(COLUMN_NAME_CATEGORYID, note.getCategoryID());

        database.update(TABLE_NAME, values, "_id = ? ", new String[] {String.valueOf(note.getId())});
        database.close();
    }


    public void deleteNote(Note note) {
        SQLiteDatabase database = databaseHandle.getWritableDatabase();
        database.delete(TABLE_NAME, "_id = " + note.getId(), null);
        database.close();
    }


}
