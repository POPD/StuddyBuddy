package com.cs616.studybuddy_mockup;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cs616.studybuddy_mockup.SQLite.Event;
import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidGridAdapter;

import java.util.HashMap;
import java.util.List;

import hirondelle.date4j.DateTime;

public class CalendarAdapter extends CaldroidGridAdapter {
	protected HashMap<DateTime, List<Event>> events = new HashMap<DateTime, List<Event>>();
	public CalendarAdapter(Context context, int month, int year,
                           HashMap<String, Object> caldroidData,
                           HashMap<String, Object> extraData) {
		super(context, month, year, caldroidData, extraData);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View cellView = convertView;

        // For reuse
        if (convertView == null) {
            cellView = inflater.inflate(R.layout.custom_cell, null);
        }

        int topPadding = cellView.getPaddingTop();
        int leftPadding = cellView.getPaddingLeft();
        int bottomPadding = cellView.getPaddingBottom();
        int rightPadding = cellView.getPaddingRight();

        TextView date_text = (TextView) cellView.findViewById(R.id.date_number);
        TextView event1 = (TextView) cellView.findViewById(R.id.event1);
        TextView event2 = (TextView) cellView.findViewById(R.id.event2);
        TextView event3 = (TextView) cellView.findViewById(R.id.event3);
        TextView overflow = (TextView) cellView.findViewById(R.id.event_overflow);

        date_text.setTextColor(Color.BLACK);

        // Get dateTime of this cell
        DateTime dateTime = this.datetimeList.get(position);
        Resources resources = context.getResources();

        // Set color of the dates in previous / next month
        if (dateTime.getMonth() != month) {
            date_text.setTextColor(resources
                    .getColor(com.caldroid.R.color.caldroid_darker_gray));
        }

        boolean shouldResetDiabledView = false;
        boolean shouldResetSelectedView = false;

        // Customize for disabled dates and eventDate outside min/max dates
        if ((minDateTime != null && dateTime.lt(minDateTime))
                || (maxDateTime != null && dateTime.gt(maxDateTime))
                || (disableDates != null && disableDates.indexOf(dateTime) != -1)) {

            date_text.setTextColor(CaldroidFragment.disabledTextColor);
            if (CaldroidFragment.disabledBackgroundDrawable == -1) {
                cellView.setBackgroundResource(com.caldroid.R.drawable.disable_cell);
            } else {
                cellView.setBackgroundResource(CaldroidFragment.disabledBackgroundDrawable);
            }

            if (dateTime.equals(getToday())) {
                cellView.setBackgroundResource(com.caldroid.R.drawable.red_border_gray_bg);
            }

        } else {
            shouldResetDiabledView = true;
        }

        // Customize for selected dates
        if (selectedDates != null && selectedDates.indexOf(dateTime) != -1) {
            cellView.setBackgroundColor(resources
                    .getColor(com.caldroid.R.color.caldroid_sky_blue));

            date_text.setTextColor(Color.BLACK);

        } else {
            shouldResetSelectedView = true;
        }

        if (shouldResetDiabledView && shouldResetSelectedView) {
            // Customize for today
            if (dateTime.equals(getToday())) {
                cellView.setBackgroundResource(com.caldroid.R.drawable.red_border);
            } else {
                cellView.setBackgroundResource(com.caldroid.R.drawable.cell_bg);
            }
        }

        date_text.setText("" + dateTime.getDay());

        // Somehow after setBackgroundResource, the padding collapse.
        // This is to recover the padding
        cellView.setPadding(leftPadding, topPadding, rightPadding,
                bottomPadding);

        // Set custom color if required
        setCustomResources(dateTime, cellView, date_text);

        if (events.containsKey(dateTime)) {
            List<Event> eventlist = events.get(dateTime);
            // do whatever you want with cellView
            for (int i = 0; i < eventlist.size(); i++) {
                Event event = eventlist.get(i);
                switch(i)  {
                    case 0:
                        event1.setText(event.title);
                        //  **** NOTE **** We will replace this with the class color
                        event1.setBackgroundColor(cellView.getResources().getColor(R.color.blue));
                        break;
                    case 1:
                        event2.setText(event.title);
                        //  **** NOTE **** We will replace this with the class color
                        event2.setBackgroundColor(cellView.getResources().getColor(R.color.green));
                        break;
                    case 2:
                        event3.setText(event.title);
                        //  **** NOTE **** We will replace this with the class color
                        event3.setBackgroundColor(cellView.getResources().getColor(R.color.red));
                        break;
                    default:
                        event1.setVisibility(View.GONE);
                        event2.setVisibility(View.GONE);
                        event3.setVisibility(View.GONE);
                        overflow.setVisibility(View.VISIBLE);
                        overflow.setText(String.valueOf(i+1));
                }
            }
        }

        /*LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        CellView cellView = (CellView) convertView;

        LayoutInflater localInflater = CaldroidFragment.getLayoutInflater(context, inflater, themeResource);

        // For reuse
        if (convertView == null) {
            if (squareTextViewCell) {
                cellView = (CellView) localInflater.inflate(R.layout.square_date_cell, null);
            } else {
                cellView = (CellView) localInflater.inflate(R.layout.normal_date_cell, null);
            }
        }

        customizeTextView(position, cellView);
        */

        return cellView;
	}

}
