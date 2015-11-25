package com.cs616.studybuddy_mockup;

import android.graphics.Paint;

/**
 * Created by Dominic on 2015-10-31.
 */
public class Course {

    // Backing fields
    double _studyTime;
    Paint _paint;

    public Course(double studyTime, int color){

        // Initialize the paint object
        this._paint = new Paint();
        this._paint.setAntiAlias(true);
        this._paint.setStrokeWidth(20);
        this._paint.setStyle(Paint.Style.STROKE);

        // Set the backing fields
        this._paint.setColor(color);
        this._studyTime = studyTime;


    }

    // Getters
    public double get_studyTime() {
        return _studyTime;
    }
    public Paint get_paint(){
        return _paint;
    }
}
