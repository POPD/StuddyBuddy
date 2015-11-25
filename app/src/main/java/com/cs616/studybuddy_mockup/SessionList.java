package com.cs616.studybuddy_mockup;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SessionList extends Activity {

    public static final int MAX_COURSES = 8;
    private Mockup_Database mdb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_session_list);

        ListView courseList = (ListView) findViewById(R.id.Course_List);
        mdb = new Mockup_Database();
        CourseArrayAdapter adapter = new CourseArrayAdapter(this,mdb.get_Student().getCourses());

        courseList.setAdapter(adapter);


        courseList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SessionList.this, SessionActivity.class);
                //Todo: httprequest to get the course and title
                intent.putExtra("sentCourseTitle", mdb.get_Student().getCourses().get(position).get_name());
                startActivity(intent);
            }
        });
    }
}