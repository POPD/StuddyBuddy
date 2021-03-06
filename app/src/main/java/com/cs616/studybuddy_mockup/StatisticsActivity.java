package com.cs616.studybuddy_mockup;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
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
import com.cs616.studybuddy_mockup.AsyncResponse.Statistics_AsyncResponse;
import com.cs616.studybuddy_mockup.AsyncTasks.Session_ReadAll_s_AsyncTask;
import com.cs616.studybuddy_mockup.Repositories.Sessions;

import java.util.List;

public class StatisticsActivity extends Fragment implements Statistics_AsyncResponse{
    RelativeLayout llLayout;
    FragmentActivity faActivity;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        faActivity  = (FragmentActivity)    super.getActivity();
        llLayout    = (RelativeLayout)    inflater.inflate(R.layout.activity_statistics, container, false);
        GetSessions();
        return llLayout; // We must return the loaded Layout
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

    public void refreshcourseAdapter(){
        ListView courseSpinner = (ListView) llLayout.findViewById(R.id.spinner_courseSpinner_statistics_activity);
        courseListAdapter adapter = new courseListAdapter(super.getActivity(),MainActivity.currentUser.getCourses());

        courseSpinner.setAdapter(adapter);

        courseSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (MainActivity.currentUser.getCourses().get(position).get_studyTime() == 0) {
                    Toast.makeText(getContext(), "You have not studied for this course yet !", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(StatisticsActivity.super.getActivity(), StatisticsDetails.class);
                    intent.putExtra("position", position);
                    startActivity(intent);
                }

            }
        });

        llLayout.findViewById(R.id.circleView).invalidate();
    }


    private void GetSessions(){
        // Get the corresponding sessions
        Session_ReadAll_s_AsyncTask getSessions = new Session_ReadAll_s_AsyncTask();
        getSessions.setDelegate(StatisticsActivity.this);
        getSessions.execute(MainActivity.currentUser.getUrl());
    }
    @Override
    public void onSessionAsyncFinish(Boolean success) {

        if(!success){
            Toast toast = Toast.makeText(this.getContext(),"You have not studied for anything yet", Toast.LENGTH_SHORT);
            toast.show();

            Fragment fragment = new MainActivity();
            FragmentManager fragmentManager = faActivity.getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container, fragment);
            fragmentTransaction.commit();

        }
    }


    @Override
    public void onSessionAsyncFinish(List<Sessions> sessionsList) {
        for(Course course: MainActivity.currentUser.getCourses()){
            for (Sessions sessions : sessionsList) {
                if(course.getCourseNo().equals(sessions.getCourseNo())){
                    course.set_studyTime(course.get_studyTime() + sessions.getSecondsStudied());
                }
            }
        }
        if(sessionsList.isEmpty()){
            onSessionAsyncFinish(false);
        }
        MainActivity.currentUser.setSession(sessionsList);
        refreshcourseAdapter();
    }

    @Override
    public void onCreateSessionAsyncFinish(Sessions sessions) {

    }
}
