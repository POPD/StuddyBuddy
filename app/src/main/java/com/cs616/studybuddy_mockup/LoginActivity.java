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
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class LoginActivity extends Fragment {
    View rootView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.activity_login, container, false);

        final Mockup_Database mdb = new Mockup_Database();


        Button login = (Button) rootView.findViewById(R.id.button_login_login_activity);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText username = (EditText) getActivity().findViewById(R.id.textview_username_login_activity);
                EditText password = (EditText) getActivity().findViewById(R.id.textview_password_login_activity);

                String userId = String.valueOf(username.getText());
                String myId = String.valueOf(mdb.get_Student().get_studentId());

                String userPass = String.valueOf(password.getText());
                String myPass = mdb.get_Student().get_password();

                Log.d(String.valueOf(username.getText()), "onClick ");
                Log.d(String.valueOf(String.valueOf(mdb.get_Student().get_studentId())), "onClick ");

                if(userId.equals(myId) && userId.equals(myId)){
                    Intent intent = new Intent(LoginActivity.super.getActivity(),MainDrawerActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast toast = Toast.makeText(LoginActivity.super.getActivity(),"Invalid login credentials.Use 101010, testing", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
        return rootView;
    }

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//
//        final Mockup_Database mdb = new Mockup_Database();
//
//
//        Button login = (Button) findViewById(R.id.button_login_login_activity);
//
//        login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                EditText username = (EditText) findViewById(R.id.textview_username_login_activity);
//                EditText password = (EditText) findViewById(R.id.textview_password_login_activity);
//
//                String userId = String.valueOf(username.getText());
//                String myId = String.valueOf(mdb.get_Student().get_studentId());
//
//                String userPass = String.valueOf(password.getText());
//                String myPass = mdb.get_Student().get_password();
//
//                Log.d(String.valueOf(username.getText()), "onClick ");
//                Log.d(String.valueOf(String.valueOf(mdb.get_Student().get_studentId())), "onClick ");
//
//                if(userId.equals(myId) && userId.equals(myId)){
//                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
//                    startActivity(intent);
//                }
//                else{
//                    Toast toast = Toast.makeText(LoginActivity.this,"Invalid login credentials.Use 101010, testing", Toast.LENGTH_SHORT);
//                    toast.show();
//                }
//            }
//        });
//    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        rootView..inflate(R.menu.menu_login, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
