package com.cs616.studybuddy_mockup;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by zach on 2015-11-25.
 */
public class CourseArrayAdapter extends ArrayAdapter<Course> {

    public CourseArrayAdapter(Context context, List<Course> objects) {
        super(context, -1, objects);
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public long getItemId(int position) {
       // return getItem(position).getCourseid();
        return 1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View newRow;

        if(convertView == null) {
            // inflate the new row from the XML layout
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            newRow = inflater.inflate(R.layout.adapter_list, parent, false);
        }
        else
            newRow = convertView;

        // set the name and phone number
        //TODO: PUT COURSE TITLE INTO COURSE CLASS
        //TextView CourseTitle  = (TextView) newRow.findViewById(R.id.CourseTitle);
        //ADD IN FOR CIRCLES
        /// /LabeledCircleView categoryCircle = (LabeledCircleView) newRow.findViewById(R.id.CategoryCircle);
        return newRow;
    }
}