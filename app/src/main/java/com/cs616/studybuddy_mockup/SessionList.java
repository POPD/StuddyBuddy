package com.cs616.studybuddy_mockup;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.cs616.studybuddy_mockup.Adapters.CourseArrayAdapter;
import com.cs616.studybuddy_mockup.Repositories.Courses;

import java.util.List;

public class SessionList extends Activity {

    public static final int MAX_COURSES = 8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_session_list);
        //Todo: FINISH dynamic list of courses/student
//        List<Courses> dbCourseList = MainActivity.currentUser.getCourses();
//        for(Courses item: dbCourseList)
//        {
//
//        }
        ListView courseList = (ListView) findViewById(R.id.Course_List);
        CourseArrayAdapter adapter = new CourseArrayAdapter(this,MainActivity.currentUser.getCourses());
        courseList.setAdapter(adapter);
        courseList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SessionList.this, SessionActivity.class);
                //Todo: httprequest to get the course and title
                intent.putExtra("sentCourseTitle", MainActivity.currentUser.getCourses().get(position).get_name());
                intent.putExtra("sentCourseNo", MainActivity.currentUser.getCourses().get(position).getCourseNo());
                startActivity(intent);
                finish();

            }
        });

    }
}
