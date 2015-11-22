package com.cs616.studybuddy_mockup;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;

public class SessionActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session);
        //SETTING UP THE MENU BUTTONS
        final Button home = (Button) findViewById(R.id.btn_home);
        final Button stats = (Button) findViewById(R.id.btn_stats);
        final Button account = (Button) findViewById(R.id.btn_account);

        home.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(SessionActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        stats.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(SessionActivity.this, StatisticsActivity.class);
                startActivity(intent);
            }
        });
        account.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(SessionActivity.this, StatisticsActivity.class);
                startActivity(intent);
            }
        });
    }

}
