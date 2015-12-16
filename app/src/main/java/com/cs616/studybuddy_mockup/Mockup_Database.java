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
       // _courseList.add(new Course(1,"Integration Project",15,MainActivity.colors.get(0) ));
       // _courseList.add(new Course(2,"Oracle Technologies",5, MainActivity.colors.get(1)));
       // _courseList.add(new Course(3,"Web Programming",3, MainActivity.colors.get(2)));
       // _courseList.add(new Course(4,"Unix Networking",7, MainActivity.colors.get(3)));
       // _courseList.add(new Course(5,"Telecommunications",9, MainActivity.colors.get(4)));
       // _courseList.add(new Course(6,"Human Relations",1, MainActivity.colors.get(5)));

        this._Student = new Student(101010,"Dominic Morin","testing" ,this._courseList);
    }

    public List<Course> getCourseList() {
        return _Student.getCourses();
    }

    public Student get_Student() {
        return _Student;
    }
}
