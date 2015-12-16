package com.cs616.studybuddy_mockup;

import android.app.Fragment;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.cs616.studybuddy_mockup.AsyncResponse.Create_AsyncResponse;
import com.cs616.studybuddy_mockup.AsyncResponse.PickCourses_AsyncResponse;
import com.cs616.studybuddy_mockup.AsyncTasks.Courses_ReadAll_AsyncTask;
import com.cs616.studybuddy_mockup.AsyncTasks.Registered_Courses_Create_AsyncTask;
import com.cs616.studybuddy_mockup.AsyncTasks.Registered_Courses_ReadAll_AsyncTask;
import com.cs616.studybuddy_mockup.Repositories.Courses;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class PickCoursesActivity extends Fragment implements PickCourses_AsyncResponse {
    List<String> listContent;
    List<Courses> allCourses;
    View rootView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.activity_pick_courses, container, false);
        final FragmentActivity faActivity  = (FragmentActivity)    super.getActivity();

        Courses_ReadAll_AsyncTask register = new Courses_ReadAll_AsyncTask();
        register.setDelegate(PickCoursesActivity.this);
        register.execute();
        return rootView;
    }

    @Override
    public void onCourseReadAsyncFinish(List<Courses> courses) {
        listContent = new ArrayList<>();
        allCourses = courses;
        final ListView courseList = (ListView)rootView.findViewById(R.id.pick_course_list);
        int i=0;
        for(Courses item: courses){
            listContent.add(item.getTitle());
        }
        ArrayAdapter<String> adapter

                = new ArrayAdapter<String>(super.getActivity(),

                android.R.layout.simple_list_item_multiple_choice,

                listContent);
        courseList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        courseList.setAdapter(adapter);

        Button getChoice = (Button)rootView.findViewById(R.id.btn_pick_finish);

        getChoice.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                List<Courses> registerCourses = new ArrayList<Courses>();
                int cntChoice = courseList.getCount();
                SparseBooleanArray sparseBooleanArray = courseList.getCheckedItemPositions();
                if(sparseBooleanArray.size() == 0){
                    Toast toast = Toast.makeText(PickCoursesActivity.super.getActivity(), "You must pick at least 1 course!", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else{
                    if(sparseBooleanArray.size() > MainActivity.MAX_COURSES){
                        Toast toast = Toast.makeText(PickCoursesActivity.super.getActivity(), "You must not go over "+MainActivity.MAX_COURSES+" classes", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    else{
                        for(int i = 0; i < cntChoice; i++){
                            if(sparseBooleanArray.get(i)) {
                                Courses course = new Courses();
                                course.setCourseNo(allCourses.get(i).getCourseNo());
                                course.setId(allCourses.get(i).getId());
                                course.setTitle(allCourses.get(i).getTitle());
                                registerCourses.add(course);
                            }
                        }
                        Registered_Courses_Create_AsyncTask register = new Registered_Courses_Create_AsyncTask();
                        register.setDelegate(PickCoursesActivity.this);
                        register.execute(registerCourses);
                    }
                }
            }});
    }

    @Override
    public void onCourseRegisterAsyncFinish(List<Courses> courses) {
            if(courses == null){
                Toast toast = Toast.makeText(super.getActivity(),"Error registering for classes, please try later!", Toast.LENGTH_SHORT);
                toast.show();
            }
            MainActivity.db_courses = courses;
            Intent intent = new Intent(super.getActivity(),MainDrawerActivity.class);
            startActivity(intent);
            getActivity().finish();
    }
}
