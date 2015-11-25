package com.cs616.studybuddy_mockup;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.ArrayList;

/**
 * Created by Dominic on 2015-11-24.
 */
public class courseListAdapter  extends ArrayAdapter<Course>{
    public courseListAdapter(Context context, List<Course> objects) {
        super(context, -1, objects);
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View newRow;
        final Course course = getItem(position);
        if(convertView == null) {
            // inflate the new row from the XML layout
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            newRow = inflater.inflate(R.layout.list_row_course, parent, false);
        }
        else
            newRow = convertView;

        // set the Content and Title
        TextView titleTextView = (TextView) newRow.findViewById(R.id.CategoryTitle_TextView_Activity_Note_List);
        TextView commentTextView = (TextView) newRow.findViewById(R.id.CategoryContent_TextView_Activity_Note_List);


        titleTextView.setText(course.get_name());
        titleTextView.setTextColor(course.get_paint().getColor());
        commentTextView.setText(String.valueOf(course.get_studyTime()) );

        return newRow;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position,convertView,parent);
    }
}
