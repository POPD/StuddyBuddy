package com.cs616.studybuddy_mockup;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.cs616.studybuddy_mockup.Adapters.CourseArrayAdapter;
import com.cs616.studybuddy_mockup.AsyncResponse.Statistics_AsyncResponse;
import com.cs616.studybuddy_mockup.AsyncTasks.Session_Create_AsyncTask;
import com.cs616.studybuddy_mockup.Repositories.Sessions;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class ExtraActivity extends Fragment implements Statistics_AsyncResponse{

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            final FragmentActivity faActivity  = (FragmentActivity)    super.getActivity();
            LinearLayout llLayout    = (LinearLayout)    inflater.inflate(R.layout.activity_extra, container, false);
            final Button save = (Button) llLayout.findViewById(R.id.button_save_extra_activity);
            final Button cancel = (Button) llLayout.findViewById(R.id.button_cancel_extra_activity);
            final TimePicker picker = (TimePicker) llLayout.findViewById(R.id.extra_activity_time_picker);
            final Spinner courseSpinner = (Spinner) llLayout.findViewById(R.id.spinner_courseSpinner_extra_activity);
            CourseArrayAdapter adapter = new CourseArrayAdapter(super.getActivity(),MainActivity.currentUser.getCourses());

            courseSpinner.setAdapter(adapter);
            picker.setHour(0);
            picker.setMinute(0);
            picker.setIs24HourView(true);

            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String courseNo;
                    long secondsStudied;


                    // Get the course number
                    courseNo = MainActivity.currentUser.getCourses().get(courseSpinner.getSelectedItemPosition()).getCourseNo();

                    // Get the amount of time studied;


                    secondsStudied = ((picker.getHour() * 3600) + (picker.getMinute() * 60));

                    if(secondsStudied != 0) {

                        Sessions session = new Sessions(courseNo, secondsStudied, MainActivity.currentUser.getStudentId());

                        Session_Create_AsyncTask createSessions = new Session_Create_AsyncTask();
                        createSessions.setDelegate(ExtraActivity.this);
                        createSessions.execute(session);
                    }
                    else{
                        Toast toast = Toast.makeText(getContext(),"Cannot add a session where you did not study.", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
            });

            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    returnHome();
                }
            });
            return llLayout; // We must return the loaded Layout
        }
    public void returnHome(){
        Fragment fragment = new MainActivity();
        FragmentManager fragmentManager = this.getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

@Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        // Inflate the menu; this adds items to the action bar if it is present.
        this.getActivity().getMenuInflater().inflate(R.menu.menu_main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSessionAsyncFinish(Boolean success) {
        if(!success){
            Toast toast = Toast.makeText(this.getContext(), "Error Adding a session.", Toast.LENGTH_SHORT);
            toast.show();
            returnHome();
        }
    }

    @Override
    public void onSessionAsyncFinish(List<Sessions> sessions) {
        // Do nothing
    }

    @Override
    public void onCreateSessionAsyncFinish(Sessions sessions) {
        Toast toast = Toast.makeText(this.getContext(),"Succesfully added an extra session !", Toast.LENGTH_SHORT);
        toast.show();
        returnHome();
    }
}


