package com.cs616.studybuddy_mockup;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cs616.studybuddy_mockup.AsyncResponse.Login_AsyncResponse;
import com.cs616.studybuddy_mockup.AsyncTasks.Student_Login_AsyncTask;
import com.cs616.studybuddy_mockup.Repositories.Courses;
import com.cs616.studybuddy_mockup.Repositories.Students;

import java.util.List;

public class CreateActivity extends Fragment implements Login_AsyncResponse {
    View rootView;
    private long id;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.activity_create, container, false);
        final FragmentActivity faActivity  = (FragmentActivity)    super.getActivity();



        Button login = (Button) rootView.findViewById(R.id.button_new_create_activity);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText username = (EditText) getActivity().findViewById(R.id.textview_username_create_activity);
                String studentId = String.valueOf(username.getText());


                if(studentId.length() != 7){
                    Toast toast = Toast.makeText(CreateActivity.super.getActivity(),"Student ID must be length of 7", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else {
                    id = Long.valueOf(studentId);
                    Student_Login_AsyncTask login = new Student_Login_AsyncTask();
                    login.setDelegate(CreateActivity.this);
                    login.execute(studentId);
                }



//                if(userId.equals(myId) && userId.equals(myId)){
//                    Intent intent = new Intent(CreateActivity.super.getActivity(),MainDrawerActivity.class);
//                    startActivity(intent);
//                }
//                else{
//                    Toast toast = Toast.makeText(CreateActivity.super.getActivity(),"Invalid login credentials.Use 101010, testing", Toast.LENGTH_SHORT);
//                    toast.show();
//                }
            }
        });

        return rootView;
    }

    @Override
    public void onLoginAsyncFinish(Students success) {
        if(success == null){
            Intent intent = new Intent(CreateActivity.super.getActivity(),SetupActivity.class);
            intent.putExtra("studentid",id);
            startActivity(intent);
        }
        else{
            Toast toast = Toast.makeText(CreateActivity.super.getActivity(),"That student ID is already in use", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    @Override
    public void onLoginAsyncFinish(List<Courses> courses) {

    }
}
