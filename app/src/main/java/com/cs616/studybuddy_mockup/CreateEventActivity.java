package com.cs616.studybuddy_mockup;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.cs616.studybuddy_mockup.Adapters.CourseArrayAdapter;

public class CreateEventActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
        final Button save = (Button) findViewById(R.id.button_save_create_event);
        final Button cancel = (Button) findViewById(R.id.button_cancel_create_event);

        final Spinner courseSpinner = (Spinner) findViewById(R.id.spinner_courseSpinner_create_event);
        final EditText editText = (EditText) findViewById(R.id.editText_create_event);
        Mockup_Database mdb = new Mockup_Database();
        CourseArrayAdapter adapter = new CourseArrayAdapter(this,mdb.getCourseList());

        courseSpinner.setAdapter(adapter);


        setDateDialog fromDate = new setDateDialog(editText, this);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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
