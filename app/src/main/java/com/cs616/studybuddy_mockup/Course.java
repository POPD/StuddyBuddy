package com.cs616.studybuddy_mockup;

import android.graphics.Paint;

import com.cs616.studybuddy_mockup.Repositories.Sessions;

import java.util.List;

/**
 * Created by Dominic on 2015-10-31.
 */
public class Course {

    // Backing fields
    private String _name;
    private double _studyTime;
    private Paint _paint;
    private long _id;
    private String courseNo;

    public String getCourseNo() {
        return courseNo;
    }

    public void setCourseNo(String courseNo) {
        this.courseNo = courseNo;
    }

    private List<Sessions> sessionsList;


    public Course(long id,String courseName,String courseNo, double studyTime, int color){

        // Initialize the paint object
        this._paint = new Paint();
        this._paint.setAntiAlias(true);
        this._paint.setStrokeWidth(20);
        this._paint.setStyle(Paint.Style.STROKE);
        this.courseNo = courseNo;
        // Set the backing fields
        this._id = id;
        this._paint.setColor(color);
        this._studyTime = studyTime;
        this._name = courseName;



    }

    // Getters
    public double get_studyTime() {
        return _studyTime;
    }
    public Paint get_paint(){
        return _paint;
    }
    public String get_name() {return _name;}

    public void set_studyTime(double _studyTime) {
        this._studyTime = _studyTime;
    }

    public long getId() {
        return _id;
    }
}
