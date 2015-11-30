package com.cs616.studybuddy_mockup;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

public class SessionActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session);

        //Get intent data
        Intent intent = getIntent();

        //CONSTANTS
        final long[] lastPause = {SystemClock.elapsedRealtime()};

        final Button alarms = (Button) findViewById(R.id.btn_Alarms);
        final Button finish = (Button) findViewById(R.id.btn_Finish);

        //SETTING UP TIME DISPLAYS
        final Chronometer timer = (Chronometer) findViewById(R.id.chronometer);
        final EditText DisplayTimer = (EditText) findViewById(R.id.DisplayTimer);
        ToggleButton toggle = (ToggleButton) findViewById(R.id.btn_Toggle);

        //SETTING UP TITLE
        final TextView title = (TextView) findViewById(R.id.text_Title);
        title.setText((String) intent.getExtras().get("sentCourseTitle"));

        timer.setVisibility(View.INVISIBLE);
        //Time Functionality
        timer.setBase(SystemClock.elapsedRealtime());
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // The toggle is enabled
                    timer.setBase(timer.getBase() + SystemClock.elapsedRealtime() - lastPause[0]);
                    timer.start();
                } else {
                    // The toggle is disabled
                    lastPause[0] = SystemClock.elapsedRealtime();
                    timer.stop();
                }
            }
        });
        timer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                DisplayTimer.setText(timer.getText());
            }
        });

        finish.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }

}
