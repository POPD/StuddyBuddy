package com.cs616.studybuddy_mockup.SQLite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.cs616.studybuddy_mockup.CalendarActivity;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import hirondelle.date4j.DateTime;

/**
 * Created by Alex on 9/15/2015.
 */
public class EventTable {
    private static final String TABLE_NAME = "event";
    private static final String COLUMN_NAME_TITLE = "title";
    private static final String COLUMN_NAME_DATE = "eventDate";
    private static final String COLUMN_NAME_ID = "id";
    private static final String COLUMN_NAME_COURSE = "courseFor";
    private static final int MAX_DISPLAY = 3;

    private static final String[] allColumns = { COLUMN_NAME_ID, COLUMN_NAME_TITLE, COLUMN_NAME_DATE, COLUMN_NAME_COURSE};

    // DatabaseHandler creation sql statement
    private static final String CREATE_TABLE = "create table " + TABLE_NAME +
            "(id integer primary key autoincrement, " +
            COLUMN_NAME_TITLE + " text not null," +
            COLUMN_NAME_DATE + " text," +
            COLUMN_NAME_COURSE+ " integer);";

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
        project.setForCourse(cursor.getLong(3));
        return project;
    }

    public void createEvent(Event event) {

        SQLiteDatabase database = databaseHandle.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_TITLE, event.getTitle());
        values.put(COLUMN_NAME_DATE, event.getEventDate().getRawDateString());
        values.put(COLUMN_NAME_COURSE, event.getForCourse());


        long insertId = database.insertOrThrow(TABLE_NAME, null, values);
        event.setId(insertId);

        database.close();
    }

    public Event readEvent(long id) throws ParseException {
        SQLiteDatabase db = databaseHandle.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, new String[] { "id", COLUMN_NAME_TITLE, COLUMN_NAME_DATE, COLUMN_NAME_COURSE }, "id =?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Event note = cursorToNote(cursor);
        return note;
    }

//    public Event readEventByDate(DateTime date) {
//        SQLiteDatabase db = databaseHandle.getReadableDatabase();
//
//        Cursor cursor = db.query(TABLE_NAME, new String[] { "id", COLUMN_NAME_TITLE, COLUMN_NAME_DATE }, COLUMN_NAME_DATE + " =?",
//                new String[] { date.toString() }, null, null, null, null);
//        if (cursor != null)
//            cursor.moveToFirst();
//
//        Event event = cursorToNote(cursor);
//        return event;
//    }

    public List<Event> getAllEvents() throws ParseException {
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
    public int getEventCount() {
        return -1;
    }


    public void updateEvent(Event event) {
        SQLiteDatabase database = databaseHandle.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_NAME_ID, event.getId());
        values.put(COLUMN_NAME_TITLE, event.getTitle());
        values.put(COLUMN_NAME_DATE, CalendarActivity.formatter.format(event.getEventDate()));
        values.put(COLUMN_NAME_COURSE, event.getForCourse());

        database.update(TABLE_NAME, values, "id = ? ", new String[] {String.valueOf(event.getId())});
        database.close();
    }

    public List<Event> getWeekEvents() throws ParseException {
        TimeZone here =TimeZone.getTimeZone("EST");
        DateTime today = DateTime.now(here);
        List<Event> list = getAllEvents();
        List<Event> weekEvents = new ArrayList<>();
        int i;
        for(i = 0; i <list.size(); i++){
            Event e = list.get(i);
            if(e.getEventDate().gteq(today)){
                break;
            }
        }
        if(i < list.size()){
            int i2 = 0;
            for(i=i; i < list.size() && i2 < MAX_DISPLAY;i++,i2++){
                weekEvents.add(list.get(i));
            }
        }
        return weekEvents;
    }
    public void deleteEvent(Event event) {
        SQLiteDatabase database = databaseHandle.getWritableDatabase();
        database.delete(TABLE_NAME, "id = " + event.getId(), null);
        database.close();
    }


}
