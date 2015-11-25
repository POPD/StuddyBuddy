package com.cs616.studybuddy_mockup;

import java.util.List;

/**
 * Created by Dominic on 2015-11-24.
 */
public class Student {
    private int _studentId;
    private List<Course> courses;
    private String _name;
    private String _password;

    public Student(int _studentId, String name, String password, List<Course> courses) {
        this._studentId = _studentId;
        this.courses = courses;
        this._name = name;
        this._password = password;
    }

    public String get_name() {
        return _name;
    }

    public int get_studentId() {
        return _studentId;
    }

    public String get_password() {
        return _password;
    }

    public List<Course> getCourses() {
        return courses;

    }
}
