package com.cs616.studybuddy_mockup;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dominic on 2015-11-24.
 */
public class MenuItemAdapter extends ArrayAdapter<String>{

    public MenuItemAdapter(Context context, List<String> objects) {
        super(context, -1, objects);
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View newRow;

        if(convertView == null) {
            // inflate the new row from the XML layout
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            newRow = inflater.inflate(R.layout.adapter_menu_list, parent, false);
        }
        else
            newRow = convertView;

        // set the Content and Title
        ImageView icon = (ImageView) newRow.findViewById(R.id.drawer_item_icon);
        TextView title = (TextView) newRow.findViewById(R.id.drawer_item_text);
        title.setText(getItem(position));

        switch(position){
            case 0:
                icon.setImageResource(R.drawable.ic_menu_home);
                break;
            case 1:
                icon.setImageResource(R.drawable.ic_menu_recent_history);
                break;
            case 2:
                icon.setImageResource(R.drawable.ic_menu_upload);
                break;
            case 3:
                icon.setImageResource(R.drawable.ic_menu_today);
                break;
            case 4:
                icon.setImageResource(R.drawable.ic_menu_sort_by_size);
                break;
            case 5:
                icon.setImageResource(R.drawable.ic_menu_manage);
                break;
        }
        return newRow;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position,convertView,parent);
    }
}
