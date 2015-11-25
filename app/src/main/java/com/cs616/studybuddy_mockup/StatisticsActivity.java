package com.cs616.studybuddy_mockup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class StatisticsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        //SETTING UP THE MENU BUTTONS
        final Button home = (Button) findViewById(R.id.btn_home);
        final Button stats = (Button) findViewById(R.id.btn_stats);
        final Button account = (Button) findViewById(R.id.btn_account);

        home.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(StatisticsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        account.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(StatisticsActivity.this, StatisticsActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_statistics, menu);

        refreshcourseAdapter();
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

    public void refreshcourseAdapter(){
        ListView courseSpinner = (ListView) findViewById(R.id.spinner_courseSpinner_statistics_activity);
        Mockup_Database mdb = new Mockup_Database();
        courseListAdapter adapter = new courseListAdapter(this,mdb.getCourseList());

        courseSpinner.setAdapter(adapter);

        courseSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast toast  = Toast.makeText(StatisticsActivity.this,"Course Details",Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }
}
