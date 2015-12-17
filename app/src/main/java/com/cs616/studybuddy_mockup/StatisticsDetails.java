package com.cs616.studybuddy_mockup;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cs616.studybuddy_mockup.Repositories.Sessions;

import java.util.List;

public class StatisticsDetails extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics_details);

        // Title
        TextView title = (TextView) findViewById(R.id.statistics_details_course_name);
        int position = getIntent().getIntExtra("position", 0);
        title.setTextColor(MainActivity.currentUser.getCourses().get(position).get_paint().getColor());
        title.setText(MainActivity.currentUser.getCourses().get(position).get_name());

        // Fields
        //TextView amtTimeStudied = (TextView) findViewById(R.id.statistics_details_total_time_studied);

        double studytime = getStudyTime(position);


        LabeledCircleView c1 = (LabeledCircleView) findViewById(R.id.circleView1);
        c1.setLabel(prettyStudyTime( (int) studytime));


        double studyCount = getStudyCount(position);
        LabeledCircleView c2= (LabeledCircleView) findViewById(R.id.circleView2);
        c2.setLabel(String.valueOf(((int) studyCount)));

        LabeledCircleView c3= (LabeledCircleView) findViewById(R.id.circleView3);
        c3.setLabel( prettyStudyTime( (int)(studytime/studyCount)));
    }


    private int getStudyTime(int coursePosition){
        String courseNo = MainActivity.currentUser.getCourses().get(coursePosition).getCourseNo();
        List<Sessions> sessions = MainActivity.currentUser.getSession();
        int studyTime = 0;

        for (Sessions session : sessions) {
            if(session.getCourseNo().equals(courseNo))
                studyTime += session.getSecondsStudied();
        }
        return studyTime;
    }

    private int getStudyCount(int coursePosition){
        String courseNo = MainActivity.currentUser.getCourses().get(coursePosition).getCourseNo();
        List<Sessions> sessions = MainActivity.currentUser.getSession();
        int i = 0;

        for (Sessions session : sessions) {
            if(session.getCourseNo().equals(courseNo))
                i++;
        }
        return i;
    }

    private String prettyStudyTime(int studyTime){
        String time;

        int hours,minutes = 0,
                seconds = 0,
                totalSecs = 0;

        totalSecs = studyTime;

        hours = totalSecs / 3600;
        minutes = (totalSecs % 3600) / 60;
        seconds = totalSecs % 60;

        if(hours == 0 && minutes ==0 && seconds == 0 ) {
            return (String.format("%20s", "-"));
        }
        else {
            time = "";

            if (hours != 0)
                time = String.format("%2s h ", hours);

            if (minutes != 0)
                time = time + minutes + " m ";

            if (seconds != 0)
                time = time + seconds + "s";

            time.replaceAll("^\\s+", "");
            Log.d(time, "prettyStudyTime ");
            return time;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_statistics_details, menu);
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
