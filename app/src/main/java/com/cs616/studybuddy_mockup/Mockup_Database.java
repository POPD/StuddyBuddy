package com.cs616.studybuddy_mockup;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dominic on 2015-11-24.
 */
public class Mockup_Database {

    private ArrayList<Course> _courseList;
    private Student _Student ;

    public Mockup_Database() {
        this._courseList = new ArrayList<Course>();

        // Create sample courses
        _courseList.add(new Course(1,"Integration Project",15, Color.parseColor("#FF4E00")));
        _courseList.add(new Course(2,"Oracle Technologies",5, Color.parseColor("#8EA604")));
        _courseList.add(new Course(3,"Web Programming",3, Color.parseColor("#EC9F05")));
        _courseList.add(new Course(4,"Unix Networking",7, Color.parseColor("#BF3100")));
        _courseList.add(new Course(5,"Telecommunications",9, Color.parseColor("#F5BB00")));
        _courseList.add(new Course(6,"Human Relations",1, Color.parseColor("#888888")));

        this._Student = new Student(1286947,"Dominic Morin","kerbalspacekerbal" ,this._courseList);
    }

    public List<Course> getCourseList() {
        return _Student.getCourses();
    }

    public Student get_Student() {
        return _Student;
    }
}
