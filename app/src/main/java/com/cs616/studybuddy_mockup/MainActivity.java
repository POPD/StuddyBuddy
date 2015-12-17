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
import com.cs616.studybuddy_mockup.utility.Color_Enum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends Fragment {
    public static Students currentUser;
    public static List<Integer> colors;
    public static List<Courses> db_courses;
    public static final int MAX_COURSES = 8;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final FragmentActivity faActivity  = (FragmentActivity)    super.getActivity();
        RelativeLayout        llLayout    = (RelativeLayout)    inflater.inflate(R.layout.activity_main, container, false);

//
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
}
