package com.cs616.studybuddy_mockup;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;


import com.cs616.studybuddy_mockup.Repositories.Courses;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dominic on 2015-10-31.
 */
public class StatsCircleView extends View {

    Paint paint;
    Path path;
    RectF oval;
    int FullCircle = 360;
    List<Course> courses;
    List<Integer> colors;

    // Auto generated constructors
    public StatsCircleView(Context context) {
        super(context);
        init();
    }

    public StatsCircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public StatsCircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    //------------------------------------------

    private void init() {

        oval = new RectF();
        colors = new ArrayList<Integer>();
        courses = MainActivity.currentUser.getCourses();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

  /*
   * drawArc(RectF oval, float startAngle, float sweepAngle, boolean useCenter, Paint paint)
   *
   * oval - The bounds of oval used to define the shape and size of the arc
   * startAngle - Starting angle (in degrees) where the arc begins
   * sweepAngle - Sweep angle (in degrees) measured clockwise
   * useCenter - If true, include the center of the oval in the arc, and close it if it is being stroked. This will draw a wedge
   * paint - The paint used to draw the arc
   */


        // Make the circle fit the screen
        int min = (getWidth() < getHeight()) ? getWidth() : getHeight();

        //oval.set(float left, float top, float right, float bottom)
        oval.set(min/4, 20, min-min/4, (min/2)+20);


        if(courses == null || courses.isEmpty()) {

        }
        else {

            // Get the total time studied
            int totalStudyTime = 0;
            for (Course course : courses) {
                totalStudyTime += course.get_studyTime();
            }

            // Starting angle of the arc
            double startAngle = 0;
            // Each sweep angle is proportional to: (Total study time / Course study time)
            double sweepAngle;
            // Padding between different arcs
            int padding = courses.size() == 1 ? 0 : 5;

            // For each course in courses
            for (Course course : courses) {
                // Calculate the arc length
                sweepAngle = FullCircle / (totalStudyTime / course.get_studyTime());
                // Draw the arc
                canvas.drawArc(oval, (int) startAngle, (int) sweepAngle - padding, false, course.get_paint());
                // Increment the starting angle
                startAngle += sweepAngle;



            TextPaint textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
            textPaint.setTextSize(80);
            canvas.drawText("Stats", (min / 2) - 85, (min / 4) + 50, textPaint);
            }
        }

    }
}


