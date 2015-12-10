package com.cs616.studybuddy_mockup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateActivity extends Fragment {
    View rootView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.activity_create, container, false);

        final Mockup_Database mdb = new Mockup_Database();


        Button login = (Button) rootView.findViewById(R.id.button_new_create_activity);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText username = (EditText) getActivity().findViewById(R.id.textview_username_create_activity);
                EditText password = (EditText) getActivity().findViewById(R.id.textview_password_create_activity);

                String userId = String.valueOf(username.getText());
                String myId = String.valueOf(mdb.get_Student().get_studentId());

                String userPass = String.valueOf(password.getText());
                String myPass = mdb.get_Student().get_password();

                Log.d(String.valueOf(username.getText()), "onClick ");
                Log.d(String.valueOf(String.valueOf(mdb.get_Student().get_studentId())), "onClick ");

                if(userId.equals(myId) && userId.equals(myId)){
                    Intent intent = new Intent(CreateActivity.super.getActivity(),MainDrawerActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast toast = Toast.makeText(CreateActivity.super.getActivity(),"Invalid login credentials.Use 101010, testing", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        return rootView;
    }
}
