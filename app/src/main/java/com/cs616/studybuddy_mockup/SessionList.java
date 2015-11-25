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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //
        setContentView(R.layout.activity_session_list);
        List<String> courseList = new ArrayList<String>();
        //hardcoded list for now, add i max number of courses to the list
        for(int i = 0; i < MAX_COURSES; i++) {
            courseList.add("course"+i);
        }

        ArrayAdapter<String> courseAdapter = new ArrayAdapter<String>(this, R.layout.adapter_list,courseList);
        ListView courseListView = (ListView) findViewById(R.id.Course_List);
        courseListView.setAdapter(courseAdapter);

        courseListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SessionList.this, SessionActivity.class);
                //Todo: httprequest to get the course and title
                intent.putExtra("sentCourseTitle", "PlaceHolder");
                startActivity(intent);
            }
        });
    }
}
