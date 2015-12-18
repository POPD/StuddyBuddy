package com.cs616.studybuddy_mockup;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cs616.studybuddy_mockup.AsyncResponse.Create_AsyncResponse;
import com.cs616.studybuddy_mockup.AsyncResponse.Login_AsyncResponse;
import com.cs616.studybuddy_mockup.AsyncTasks.Registered_Courses_Create_AsyncTask;
import com.cs616.studybuddy_mockup.AsyncTasks.Student_Create_AsyncTask;
import com.cs616.studybuddy_mockup.AsyncTasks.Student_Login_AsyncTask;
import com.cs616.studybuddy_mockup.Repositories.Courses;
import com.cs616.studybuddy_mockup.Repositories.Students;

import java.util.List;

public class StudentInfoActivity extends Fragment implements Create_AsyncResponse{
    public static int MIN_PASSWORD = 6;
    View rootView;
    private long id;
    private String fname;
    private String lname;
    private String password;
    private String confirm_password;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.activity_student_info, container, false);
        final FragmentActivity faActivity  = (FragmentActivity)    super.getActivity();

        Intent intent = super.getActivity().getIntent();
        id = intent.getExtras().getLong("studentid");

        Button login = (Button) rootView.findViewById(R.id.btn_create_finish);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fname = String.valueOf(((EditText)rootView.findViewById(R.id.first_info)).getText());
                lname = String.valueOf(((EditText) rootView.findViewById(R.id.last_info)).getText());
                password = String.valueOf(((EditText)rootView.findViewById(R.id.password_info)).getText());
                confirm_password =  String.valueOf(((EditText)rootView.findViewById(R.id.password_confirm_info)).getText());
                if(verifyInfo()){
                    Students student = new Students();
                    student.setFname(fname);
                    student.setLname(lname);
                    student.setPassword(password);
                    student.setId(id);
                    Student_Create_AsyncTask register = new Student_Create_AsyncTask();
                    register.setDelegate(StudentInfoActivity.this);
                    register.execute(student);
                }
            }
        });

        return rootView;
    }
    public boolean verifyInfo(){
        if(fname.equals("")){
            showToast("First name cannot be empty");
            return false;
        }
        if(lname.equals("")){
            showToast("Last name cannot be empty");
            return false;
        }
        if(password.equals("")){
            showToast("Password cannot be empty");
            return false;
        }
        if(confirm_password.equals("")){
            showToast("Confirm Password cannot be empty");
            return false;
        }
        if(password.length() < MIN_PASSWORD){
            showToast("Password cannot be smaller than "+ MIN_PASSWORD);
            return false;
        }
        if(!password.equals(confirm_password)){
            showToast("Passwords must match");
            return false;
        }
        return true;
    }
    public void showToast(String message){
        Toast toast = Toast.makeText(StudentInfoActivity.super.getActivity(), message, Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void onCreateAsyncFinish(Students student) {
        if(student == null){
            Toast toast = Toast.makeText(super.getActivity(),"Error creating student, please try later!", Toast.LENGTH_SHORT);
            toast.show();
        }
        else {
            MainActivity.currentUser = student;
            android.app.Fragment fragment = new PickCoursesActivity();
            FragmentManager fragmentManager = super.getActivity().getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.setup_container, fragment);
            fragmentTransaction.commit();
        }
    }
}