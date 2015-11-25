package com.cs616.studybuddy_mockup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

public class ExtraActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extra);
        getActionBar().setLogo(R.mipmap.book);
        getActionBar().setDisplayShowHomeEnabled(true);

        //SETTING UP THE MENU BUTTONS
        final Button home = (Button) findViewById(R.id.btn_home);
        final Button stats = (Button) findViewById(R.id.btn_stats);
        final Button account = (Button) findViewById(R.id.btn_account);
        final Button save = (Button) findViewById(R.id.button_save_extra_activity);
        final Button cancel = (Button) findViewById(R.id.button_cancel_extra_activity);

        final Spinner courseSpinner = (Spinner) findViewById(R.id.spinner_courseSpinner_extra_activity);

        Mockup_Database mdb = new Mockup_Database();
        CourseArrayAdapter adapter = new CourseArrayAdapter(this,mdb.getCourseList());

        courseSpinner.setAdapter(adapter);

        home.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ExtraActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        stats.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ExtraActivity.this, StatisticsActivity.class);
                startActivity(intent);
            }
        });
        account.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ExtraActivity.this, StatisticsActivity.class);
                startActivity(intent);
            }
        });

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
        getMenuInflater().inflate(R.menu.menu_extra, menu);




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
