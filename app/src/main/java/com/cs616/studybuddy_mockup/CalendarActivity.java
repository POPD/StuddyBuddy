package com.cs616.studybuddy_mockup;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import hirondelle.date4j.DateTime;

public class CalendarActivity extends FragmentActivity {
    public static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    //OUR CALENDAR FRAGMENT
    final CalendarFragment calfragment = new CalendarFragment();

    //SAMPLE STRINGS FOR MOCKUP
    String[] testArray = {"Exam 1: example","Exam 2: example"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        getActionBar().setLogo(R.mipmap.book);
        getActionBar().setDisplayShowHomeEnabled(true);
        createCalendar();

        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.exams_listview, testArray);

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

    }
    public void createCalendar(){

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


        // ***** TESTING OUR EVENTs ***** //
        //**NOTE: replace this code with our database once completed
        //get some dates to test
        List<DateTime> dates = new ArrayList<>();
        dates.add(new DateTime("2015-11-05 00:00:00.000000000"));
        dates.add(new DateTime("2015-11-07 00:00:00.000000000"));
        dates.add(new DateTime("2015-11-11 00:00:00.000000000"));
        dates.add(new DateTime("2015-11-15 00:00:00.000000000"));

        for (int i=0; i < dates.size(); i++) {
            DateTime date = dates.get(i);
            calfragment.events.put(date, new ArrayList<Event>());
            if(calfragment.events.containsKey(date)) {
                switch(i){
                    case 0:
                        calfragment.events.get(date).add(new Event(date, "Exam1"));
                        calfragment.events.get(date).add(new Event(date, "Exam2"));
                        break;
                    case 1:
                        calfragment.events.get(date).add(new Event(date, "Exam1"));
                        break;
                    case 2:
                        calfragment.events.get(date).add(new Event(date, "Exam1"));
                        calfragment.events.get(date).add(new Event(date, "Exam2"));
                        break;
                    case 3:
                        calfragment.events.get(date).add(new Event(date, "Exam1"));
                        calfragment.events.get(date).add(new Event(date, "Exam2"));
                        calfragment.events.get(date).add(new Event(date, "Exam3"));
                        calfragment.events.get(date).add(new Event(date, "Exam4"));
                        break;
                }
            }
        }
        // ****************************//

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

        return super.onOptionsItemSelected(item);
    }
}
