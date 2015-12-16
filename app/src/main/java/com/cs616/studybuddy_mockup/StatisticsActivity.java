package com.cs616.studybuddy_mockup;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.cs616.studybuddy_mockup.Adapters.courseListAdapter;

public class StatisticsActivity extends Fragment {
    RelativeLayout llLayout;
    FragmentActivity faActivity;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        faActivity  = (FragmentActivity)    super.getActivity();
        llLayout    = (RelativeLayout)    inflater.inflate(R.layout.activity_statistics, container, false);
        refreshcourseAdapter();

        return llLayout; // We must return the loaded Layout
    }
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_statistics);
//        //SETTING UP THE MENU BUTTONS
//        final Button home = (Button) findViewById(R.id.btn_home);
//        final Button stats = (Button) findViewById(R.id.btn_stats);
//        final Button account = (Button) findViewById(R.id.btn_account);
//
//        home.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                Intent intent = new Intent(StatisticsActivity.this, MainActivity.class);
//                startActivity(intent);
//            }
//        });
//        account.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                Intent intent = new Intent(StatisticsActivity.this, StatisticsActivity.class);
//                startActivity(intent);
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

    public void refreshcourseAdapter(){
        ListView courseSpinner = (ListView) llLayout.findViewById(R.id.spinner_courseSpinner_statistics_activity);
        courseListAdapter adapter = new courseListAdapter(super.getActivity(),MainActivity.currentUser.getCourses());

        courseSpinner.setAdapter(adapter);

        courseSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast toast  = Toast.makeText(StatisticsActivity.super.getActivity(),"Course Details",Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }
}
