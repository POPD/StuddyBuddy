package com.cs616.studybuddy_mockup;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
//import android.app.Fragment;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Bundle;
//import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cs616.studybuddy_mockup.Repositories.Courses;
import com.cs616.studybuddy_mockup.Repositories.Students;
import com.cs616.studybuddy_mockup.SQLite.DatabaseHandler;
import com.cs616.studybuddy_mockup.SQLite.Event;
import com.cs616.studybuddy_mockup.utility.Color_Enum;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class MainActivity extends Fragment {
    private RelativeLayout llLayout;
    public static Students currentUser;
    public static List<Integer> colors;
    public static List<Courses> db_courses;
    public static final int MAX_COURSES = 8;
    private DatabaseHandler databaseHandle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final FragmentActivity faActivity  = (FragmentActivity)    super.getActivity();
        llLayout    = (RelativeLayout)    inflater.inflate(R.layout.activity_main, container, false);

        databaseHandle = new DatabaseHandler(super.getActivity());
//        //SETTING UP AVTIVITY BUTTONS
        final Button session  = (Button) llLayout.findViewById(R.id.btn_session);
        final Button extra    = (Button) llLayout.findViewById(R.id.btn_extra);
        final Button calendar = (Button) llLayout.findViewById(R.id.btn_calendar);

        session.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.super.getActivity(), SessionList.class);
                startActivity(intent);
            }
        });

        extra.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Fragment fragment = new ExtraActivity();
                FragmentManager fragmentManager = faActivity.getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container, fragment);
                fragmentTransaction.commit();
            }
        });
        calendar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity.super.getActivity(), CalendarActivity.class);
                startActivity(intent1);
            }
        });
        setupCourses();

        TextView welcome = (TextView) llLayout.findViewById(R.id.welcomeText_main_activity);
        welcome.setText("Welcome "+currentUser.getFname());

        try {
            setUpcomingEvents();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return llLayout; // We must return the loaded Layout
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        // Inflate the menu; this adds items to the action bar if it is present.
        this.getActivity().getMenuInflater().inflate(R.menu.menu_main, menu);
        super.onCreateOptionsMenu(menu, inflater);
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

    private void createHeadView() {
        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
                PixelFormat.TRANSLUCENT);
        params.gravity = Gravity.LEFT;

        Context context = super.getContext();
        FrameLayout frameLayout = new FrameLayout(context);

        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.addView(frameLayout, params);

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // Here is the place where you can inject whatever layout you want.
        layoutInflater.inflate(R.layout.activity_bubble, frameLayout);
    }
    public void setupColors(){
        //We need a pallet for our colors
        Resources res = getResources();
        colors = new ArrayList<>();
        colors.add(res.getColor(R.color.course_color1));
        colors.add(res.getColor(R.color.course_color2));
        colors.add(res.getColor(R.color.course_color3));
        colors.add(res.getColor(R.color.course_color4));
        colors.add(res.getColor(R.color.course_color5));
        colors.add(res.getColor(R.color.course_color6));
        colors.add(res.getColor(R.color.course_color7));
        colors.add(res.getColor(R.color.course_color8));
    }
    public void setupCourses(){
        setupColors();
        List<Course> courses = new ArrayList<>();
        int i=0;
        for(Courses item: db_courses){
            courses.add(new Course(item.getId(),item.getTitle(),item.getCourseNo(),0,colors.get(i++)));
        }
        currentUser.setCourses(courses);
    }
    public void setUpcomingEvents() throws ParseException {
        TextView event_course_1 = (TextView) llLayout.findViewById(R.id.text_eventCourse_1);
        TextView event_title_1 = (TextView) llLayout.findViewById(R.id.text_eventTitle_1);
        TextView event_date_1 = (TextView) llLayout.findViewById(R.id.text_eventDate_1);

        TextView event_course_2 = (TextView) llLayout.findViewById(R.id.text_eventCourse_2);
        TextView event_title_2 = (TextView) llLayout.findViewById(R.id.text_eventTitle_2);
        TextView event_date_2 = (TextView) llLayout.findViewById(R.id.text_eventDate_2);

        TextView event_course_3 = (TextView) llLayout.findViewById(R.id.text_eventCourse_3);
        TextView event_title_3 = (TextView) llLayout.findViewById(R.id.text_eventTitle_3);
        TextView event_date_3 = (TextView) llLayout.findViewById(R.id.text_eventDate_3);

        List<Event> events = databaseHandle.getEventTable().getWeekEvents();
        Course course = null;
        int i = 0;
        for(Event item: events){
            course = getCourseById(item.forCourse);
            switch(i){
                case 0:
                    if(course != null) {
                        event_course_1.setText(course.get_name());
                        event_course_1.setTextColor(course.get_paint().getColor());
                        event_title_1.setText("Event:" + item.getTitle().toString());
                        event_date_1.setText("Date:" + item.eventDate.getDay() + "-" + item.eventDate.getMonth() + "-" + item.eventDate.getYear());
                    }
                    break;
                case 1:
                    if(course != null) {
                        course = getCourseById(item.forCourse);
                        event_course_2.setText(course.get_name());
                        event_course_2.setTextColor(course.get_paint().getColor());
                        event_title_2.setText("Event: " + item.getTitle());
                        event_date_1.setText("Date:" + item.eventDate.getDay() + "-" + item.eventDate.getMonth() + "-" + item.eventDate.getYear());
                    }
                    break;
                case 2:
                    if(course != null) {
                        course = getCourseById(item.forCourse);
                        event_course_3.setText(course.get_name());
                        event_course_3.setTextColor(course.get_paint().getColor());
                        event_title_3.setText("Event: " + item.getTitle());
                        event_date_3.setText("Date:" + item.eventDate.format("MMM dd, yyyy", Locale.US));
                    }
                    break;
            }
            i++;
        }

    }
    public Course getCourseById(long id){
        Course course = null;
        int i=0;
        for(Courses item: db_courses){
            if(item.getId() == id){
                course = currentUser.getCourses().get(i);
            }
            i++;
        }
        return course;
    }
}
