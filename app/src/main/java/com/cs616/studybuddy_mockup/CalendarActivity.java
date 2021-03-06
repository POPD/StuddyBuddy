package com.cs616.studybuddy_mockup;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.internal.view.menu.MenuBuilder;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;

import com.cs616.studybuddy_mockup.AsyncResponse.PickCourses_AsyncResponse;
import com.cs616.studybuddy_mockup.SQLite.DatabaseHandler;
import com.cs616.studybuddy_mockup.SQLite.Event;
import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import hirondelle.date4j.DateTime;

public class CalendarActivity extends AppCompatActivity {
    public static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static SimpleDateFormat simpleFormatter = new SimpleDateFormat("MMM dd, yyyy");
    public Menu menus;
    //OUR CALENDAR FRAGMENT
    final CalendarFragment calfragment = new CalendarFragment();

    private DatabaseHandler databaseHandle;

    //SAMPLE STRINGS FOR MOCKUP
    String[] testArray = {"Exam 1: example","Exam 2: example"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        databaseHandle = new DatabaseHandler(this);
        try {
            createCalendar();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.exams_listview, testArray);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.book);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //ListView listView = (ListView) findViewById(R.id.listView);
        //listView.setAdapter(adapter);



    }
    public void createCalendar() throws ParseException {

        Bundle args = new Bundle();
        final Calendar cal = Calendar.getInstance();

        //*** OPTIONS FOR OUR CALENDAR *** //
        args.putInt(CaldroidFragment.THEME_RESOURCE, R.style.Default);
        args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
        args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
        args.putBoolean(CaldroidFragment.ENABLE_SWIPE, true);
        args.putBoolean(CaldroidFragment.SQUARE_TEXT_VIEW_CELL, true);
        args.putBoolean(CaldroidFragment.SIX_WEEKS_IN_CALENDAR, false);
        calfragment.setArguments(args);

        android.support.v4.app.FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        t.replace(R.id.calendarView, calfragment);
        t.commit();

//
//        // ***** TESTING OUR EVENTs ***** //
//        //**NOTE: replace this code with our database once completed
//        //get some dates to test
//        List<DateTime> dates = new ArrayList<>();
//        dates.add(new DateTime("2015-12-05 00:00:00.000000000"));
//        dates.add(new DateTime("2015-12-07 00:00:00.000000000"));
//        dates.add(new DateTime("2015-12-11 00:00:00.000000000"));
//        dates.add(new DateTime("2015-12-15 00:00:00.000000000"));
//
//        for (int i=0; i < dates.size(); i++) {
//            DateTime date = dates.get(i);
//                switch(i){
//                    case 0:
//                        databaseHandle.getEventTable().createEvent(new Event(date, "Exam1"));
//                        databaseHandle.getEventTable().createEvent(new Event(date, "Exam2"));
//                        break;
//                    case 1:
//                        databaseHandle.getEventTable().createEvent(new Event(date, "Exam1"));
//                        break;
//                    case 2:
//                        databaseHandle.getEventTable().createEvent(new Event(date, "Exam1"));
//                        databaseHandle.getEventTable().createEvent(new Event(date, "Exam2"));
//                        break;
//                    case 3:
//                        databaseHandle.getEventTable().createEvent(new Event(date, "Exam1"));
//                        databaseHandle.getEventTable().createEvent(new Event(date, "Exam2"));
//
//                        break;
//                }
//        }

        for (Event e : databaseHandle.getEventTable().getAllEvents()) {
            if(calfragment.events.get(e.getEventDate()) == null){
                calfragment.events.put(e.getEventDate(), new ArrayList<Event>());
            }
            calfragment.events.get(e.getEventDate()).add(e);
        }

        // ****************************//
//        calfragment.events.put(date, new ArrayList<Event>());
//        calfragment.events.get(date).add(new Event(date, "Exam3"));
        // *** LISTENER FOR CALENDAR
        final CaldroidListener listener = new CaldroidListener() {

            @Override
            public void onSelectDate(Date date, View view) {

                //move to select cell and display the events for that day in list

                calfragment.clearSelectedDates();
                calfragment.setSelectedDate(date);
                calfragment.refreshView();
            }

        };

        calfragment.setCaldroidListener(listener);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_calendar, menu);
        return true;
    }
//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
//        // Inflate the menu; this adds items to the action bar if it is present.
//        this.getMenuInflater().inflate(R.menu.menu_main, menu);
//        super.onCreateOptionsMenu(menu);
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == android.R.id.home){
            // API 5+ solution
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void onClick(View view) {
        Intent intent = new Intent(getApplicationContext(), CreateEventActivity.class);
        startActivityForResult(intent, 1);
    }

}
