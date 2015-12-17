package com.cs616.studybuddy_mockup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cs616.studybuddy_mockup.AsyncResponse.Login_AsyncResponse;
import com.cs616.studybuddy_mockup.AsyncTasks.Registered_Courses_ReadAll_AsyncTask;
import com.cs616.studybuddy_mockup.AsyncTasks.Student_Login_AsyncTask;
import com.cs616.studybuddy_mockup.Repositories.Courses;
import com.cs616.studybuddy_mockup.Repositories.Students;

import java.util.List;

public class LoginActivity extends Fragment implements Login_AsyncResponse{
    View rootView;
    String myPass;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.activity_login, container, false);



        Button login = (Button) rootView.findViewById(R.id.button_login_login_activity);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText username = (EditText) getActivity().findViewById(R.id.textview_username_login_activity);
                EditText password = (EditText) getActivity().findViewById(R.id.textview_password_login_activity);

                String userId = String.valueOf(username.getText());
                myPass = String.valueOf(password.getText());

                Student_Login_AsyncTask login = new Student_Login_AsyncTask();
                login.setDelegate(LoginActivity.this);
                login.execute(userId);
            }
        });
        return rootView;
    }
    //Function used as a callback from the login_async
    @Override
    public void onLoginAsyncFinish(Students success) {
        if(success == null){
            Toast toast = Toast.makeText(LoginActivity.super.getActivity(),"Invalid values, please try again", Toast.LENGTH_SHORT);
            toast.show();
        }
        else
            if(success.getPassword().equals(myPass)){
                MainActivity.currentUser = success;
                Registered_Courses_ReadAll_AsyncTask login = new Registered_Courses_ReadAll_AsyncTask();
                login.setDelegate(LoginActivity.this);
                login.execute(success.getUrl());
            }
            else{
                Toast toast = Toast.makeText(LoginActivity.super.getActivity(),"Invalid values, please try again", Toast.LENGTH_SHORT);
                toast.show();
            }
    }

    @Override
    public void onLoginAsyncFinish(List<Courses> courses) {
        MainActivity.db_courses = courses;
        Intent intent = new Intent(LoginActivity.super.getActivity(),MainDrawerActivity.class);
        startActivity(intent);
        getActivity().finish();

    }

}
