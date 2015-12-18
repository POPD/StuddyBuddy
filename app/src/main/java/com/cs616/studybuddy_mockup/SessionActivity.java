package com.cs616.studybuddy_mockup;

import android.Manifest;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInstaller;
import android.content.pm.PackageManager;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.os.SystemClock;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.cs616.studybuddy_mockup.AsyncResponse.Statistics_AsyncResponse;
import com.cs616.studybuddy_mockup.AsyncTasks.Session_Create_AsyncTask;
import com.cs616.studybuddy_mockup.AsyncTasks.Student_Login_AsyncTask;
import com.cs616.studybuddy_mockup.Repositories.Sessions;
import com.cs616.studybuddy_mockup.Repositories.Students;

import java.util.List;

import static android.app.PendingIntent.getActivity;

public class SessionActivity extends Activity implements Statistics_AsyncResponse{

    public static int OVERLAY_PERMISSION_REQ_CODE = 1234;
    //value determining length of timer created by user,
    // set value to negative to show that it is the first initiation/ no alarms are set
    public static int USERTIMER = -1;
    //time after alarm was set
    public static int CURRENT_SECONDS;
    //Time calculation constants
    public static int MINUTE = 60;
    public static int HOUR = 60*MINUTE;
    public static int DAY = 24*HOUR;
    public static int CURRENT_TIME=0;
    public static String CLASS_TITLE;
    Students usr = MainActivity.currentUser;



//    Uri alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
//    Ringtone ringtone = RingtoneManager.getRingtone(getApplicationContext(),  alert);
//    ringtone.setStreamType(AudioManager.STREAM_RING);
//    ringtone.play();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session);

        final Uri defaultRingtoneUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        final Ringtone defaultRingtone = RingtoneManager.getRingtone(this, defaultRingtoneUri);
        final AudioAttributes aa = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();

        //Get intent data
        final Intent intent = getIntent();

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
        CLASS_TITLE = (String) intent.getExtras().get("sentCourseTitle");
        title.setText(CLASS_TITLE);

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
                //if timer has been started and user set an alarm
                if(timer.getText() != null ){ //$%^&*&^%$%^&^%$%^&*&^%$^&*^%^&*&^%^&*&^%^&*&& USERTIMER > 0 && CURRENT_SECONDS != 0) {
                    //Check current time in the timer
                    long millisecondsElapsed = (SystemClock.elapsedRealtime() - timer.getBase());
                    int secondsElapsed = (int)millisecondsElapsed/1000;
                    CURRENT_TIME = secondsElapsed;
//                    Toast toast = Toast.makeText(SessionActivity.this, timer.getText(), Toast.LENGTH_SHORT);
//                    toast.show();
                    //make sure that the alarm + the number of seconds it was set at equals the amount of seconds on the timer
                    if (USERTIMER + CURRENT_SECONDS == CURRENT_TIME) {
                        Toast toast = Toast.makeText(SessionActivity.this, "UserTime matches timer", Toast.LENGTH_SHORT);
                        toast.show();
                        defaultRingtone.setAudioAttributes(aa);
                        defaultRingtone.play();
                        AlertDialog.Builder AlarmDialog = new AlertDialog.Builder(SessionActivity.this);
                        AlarmDialog.setTitle("ALARM");
                        AlarmDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                defaultRingtone.stop();
                            }
                        });
                        AlarmDialog.show();
                    }
                }
            }
        });
        //Todo: alarms on click
        alarms.setOnClickListener(new View.OnClickListener()
        {
//            @Override
            public void onClick(View v)
            {

                AlertDialog.Builder createTimerDialog = new AlertDialog.Builder(SessionActivity.this);

                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                //Store the layout as a view
                View timerOptions = inflater.inflate(R.layout.activity_timer_event, null);
                //set the custom layout as the dialogs view and set the title of the dialog box
                createTimerDialog.setView(timerOptions);
                createTimerDialog.setTitle("Set alarm for study session");

                //Todo RadioButtons inside custom layout
                final RadioButton radioBtn5min = (RadioButton) timerOptions.findViewById(R.id.Rad5min);
                final RadioButton radioBtn15min = (RadioButton) timerOptions.findViewById(R.id.Rad15min);
                final RadioButton radioBtn30min = (RadioButton) timerOptions.findViewById(R.id.Rad30min);

                //send back the selected radio button corresponding value
                createTimerDialog.setPositiveButton("Set", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // Check which radio button was clicked
                        if(radioBtn5min.isChecked())
                        {
                            //TEST 5 SECONDS AFTER set FOR ALARM
                            USERTIMER = 5;
                            //todo REMOVE TODO //USERTIMER = 5 * MINUTE;
                        }
                        else
                        if(radioBtn15min.isChecked())
                        {
                            USERTIMER = 15 * MINUTE;
                        }
                        else
                        if(radioBtn30min.isChecked())
                        {
                            USERTIMER = 30 * MINUTE;
                        }
                        CURRENT_SECONDS = (int) (SystemClock.elapsedRealtime() - timer.getBase())/1000;
                    }
                });
                createTimerDialog.setNeutralButton("Remove Alarms", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        USERTIMER = -1;
                        Toast toast = Toast.makeText(SessionActivity.this, "removed existing timers", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                });
                createTimerDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                createTimerDialog.show();
            }
        });
        finish.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Todo stop timer, add result to database, stop any alarms
                if(CURRENT_TIME != 0) {
                    String courseNo = intent.getStringExtra("sentCourseNo");
                    Sessions session = new Sessions(courseNo,CURRENT_SECONDS,MainActivity.currentUser.getStudentId());

                    Session_Create_AsyncTask createSessions = new Session_Create_AsyncTask();
                    createSessions.setDelegate(SessionActivity.this);
                    createSessions.execute(session);
                }
                else
                {
                    Toast toast = Toast.makeText(SessionActivity.this, "Session not logged", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        if (!Settings.canDrawOverlays(this)) {
            permissions();
        }
    }
@Override
    protected void onStop()
    {
        super.onStop();

        if (Settings.canDrawOverlays(this)) {
            startService(new Intent(this, BubbleActivity.class));
        }

    }

    @Override
    protected void onRestart()
    {
        super.onRestart();

        stopService(new Intent(this, BubbleActivity.class));
    }
    @Override
    protected void onDestroy()
    {
        super.onDestroy();

        stopService(new Intent(this, BubbleActivity.class));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == OVERLAY_PERMISSION_REQ_CODE) {
            if (Settings.canDrawOverlays(this)) {
                startService(new Intent(this, BubbleActivity.class));
            }
        }
    }
    public void permissions(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setMessage("You must allow activity overlap to use these great features (then press back)");

        dialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, OVERLAY_PERMISSION_REQ_CODE);
            }
        });

        dialogBuilder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        dialogBuilder.create();
        dialogBuilder.show();
    }

    @Override
    public void onSessionAsyncFinish(Boolean success) {

    }

    @Override
    public void onSessionAsyncFinish(List<Sessions> sessions) {

    }

    @Override
    public void onCreateSessionAsyncFinish(Sessions sessions) {
        Toast.makeText(getApplicationContext(), "Session Saved !", Toast.LENGTH_SHORT).show();
        finish();
    }

}
