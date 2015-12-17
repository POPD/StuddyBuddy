package com.cs616.studybuddy_mockup.Adapters;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.cs616.studybuddy_mockup.Course;
import com.cs616.studybuddy_mockup.R;

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

        // Here goes a nice way of displaying the time !
        // ===============================================================================
        String time;

        int hours,minutes = 0,
                seconds = 0,
                totalSecs = 0;

        totalSecs = (int) course.get_studyTime();

        hours = totalSecs / 3600;
        minutes = (totalSecs % 3600) / 60;
        seconds = totalSecs % 60;

        if(hours == 0 && minutes ==0 && seconds == 0 ) {
            commentTextView.setText(String.format("%20s", "-"));
        }
        else {
            time = "";

            if (hours != 0)
                time = String.format("%2s h", hours);

            if (minutes != 0)
                time = String.format("%4s %2s m", time, minutes);

            if (seconds != 0)
                time = String.format("%8s %2s s", time, seconds);

            commentTextView.setText(time);
        }
        // ===============================================================================
        return newRow;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position,convertView,parent);
    }
}
