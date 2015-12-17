package com.cs616.studybuddy_mockup;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.cs616.studybuddy_mockup.Adapters.CourseArrayAdapter;
import com.cs616.studybuddy_mockup.SQLite.DatabaseHandler;
import com.cs616.studybuddy_mockup.SQLite.Event;

import java.util.Date;

import hirondelle.date4j.DateTime;

public class CreateEventActivity extends Activity {
    public static DateTime myDate;
    private DatabaseHandler databaseHandle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
        final Button save = (Button) findViewById(R.id.button_save_create_event);
        final Button cancel = (Button) findViewById(R.id.button_cancel_create_event);

        final Spinner courseSpinner = (Spinner) findViewById(R.id.spinner_courseSpinner_create_event);
        final EditText dateText = (EditText) findViewById(R.id.editText_create_event);
        final EditText titleText = (EditText) findViewById(R.id.text_Title_event);

        databaseHandle = new DatabaseHandler(this);
        CourseArrayAdapter adapter = new CourseArrayAdapter(this,MainActivity.currentUser.getCourses());

        courseSpinner.setAdapter(adapter);

        dateText.setKeyListener(null);

        setDateDialog fromDate = new setDateDialog(dateText, CreateEventActivity.this);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = String.valueOf(titleText.getText());
                String date = String.valueOf(dateText.getText());
                if(title.equals("") || date.equals("")){
                    Toast toast = Toast.makeText(CreateEventActivity.this, "Invalid values, please try again", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else{
                    Event event = new Event();
                    event.setEventDate(myDate);
                    event.setTitle(title);
                    databaseHandle.getEventTable().createEvent(event);
                    finish();
                }

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_event, menu);
        return true;
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
