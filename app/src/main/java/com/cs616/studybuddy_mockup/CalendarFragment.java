package com.cs616.studybuddy_mockup;

import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidGridAdapter;

import java.util.HashMap;
import java.util.List;

import hirondelle.date4j.DateTime;

public class CalendarFragment extends CaldroidFragment {

	@Override
	public CaldroidGridAdapter getNewDatesGridAdapter(int month, int year) {
		// TODO Auto-generated method stub
		return new CalendarAdapter(getActivity(), month, year,
				getCaldroidData(), extraData);
	}
    protected HashMap<DateTime, List<Event>> events = new HashMap<DateTime, List<Event>>();

    public void refreshView() {
        // If month and year is not yet initialized, refreshView doesn't do
        // anything
        if (month == -1 || year == -1) {
            return;
        }

        refreshMonthTitleTextView();

        // Refresh the eventDate grid views
        for (CaldroidGridAdapter adapter : datePagerAdapters) {
            // Reset caldroid data
            adapter.setCaldroidData(getCaldroidData());

            // Reset extra data
            adapter.setExtraData(extraData);

            // Update today variable
            adapter.updateToday();

            // Refresh view
            adapter.notifyDataSetChanged();

            ((CalendarAdapter)adapter).events = events;
        }
    }
}
