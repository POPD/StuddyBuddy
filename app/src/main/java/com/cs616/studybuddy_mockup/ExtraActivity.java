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
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.cs616.studybuddy_mockup.Adapters.CourseArrayAdapter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ExtraActivity extends Fragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            final FragmentActivity faActivity  = (FragmentActivity)    super.getActivity();
            RelativeLayout llLayout    = (RelativeLayout)    inflater.inflate(R.layout.activity_extra, container, false);
            final Button save = (Button) llLayout.findViewById(R.id.button_save_extra_activity);
            final Button cancel = (Button) llLayout.findViewById(R.id.button_cancel_extra_activity);

            final Spinner courseSpinner = (Spinner) llLayout.findViewById(R.id.spinner_courseSpinner_extra_activity);
            final EditText editText = (EditText) llLayout.findViewById(R.id.editText);
            CourseArrayAdapter adapter = new CourseArrayAdapter(super.getActivity(),MainActivity.currentUser.getCourses());

            courseSpinner.setAdapter(adapter);


            setDateDialog fromDate = new setDateDialog(editText, getContext());
            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    returnHome();
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
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_extra);
//        getActionBar().setLogo(R.mipmap.book);
//        getActionBar().setDisplayShowHomeEnabled(true);
//
//        //SETTING UP THE MENU BUTTONS
//        final Button home = (Button) findViewById(R.id.btn_home);
//        final Button stats = (Button) findViewById(R.id.btn_stats);
//        final Button account = (Button) findViewById(R.id.btn_account);
//        final Button save = (Button) findViewById(R.id.button_save_extra_activity);
//        final Button cancel = (Button) findViewById(R.id.button_cancel_extra_activity);
//
//        final Spinner courseSpinner = (Spinner) findViewById(R.id.spinner_courseSpinner_extra_activity);
//
//        Mockup_Database mdb = new Mockup_Database();
//        CourseArrayAdapter adapter = new CourseArrayAdapter(this,mdb.getCourseList());
//
//        courseSpinner.setAdapter(adapter);
//
//        home.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                Intent intent = new Intent(ExtraActivity.this, MainActivity.class);
//                startActivity(intent);
//            }
//        });
//        stats.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                Intent intent = new Intent(ExtraActivity.this, StatisticsActivity.class);
//                startActivity(intent);
//            }
//        });
//        account.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                Intent intent = new Intent(ExtraActivity.this, AccountActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        save.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//
//        cancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//    }
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

}


