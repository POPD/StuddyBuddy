package com.cs616.studybuddy_mockup;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.cs616.studybuddy_mockup.AsyncResponse.Passwd_AsyncResponse;
import com.cs616.studybuddy_mockup.AsyncResponse.User_AsyncResponse;
import com.cs616.studybuddy_mockup.AsyncTasks.Update_Passwd_AsyncTask;
import com.cs616.studybuddy_mockup.AsyncTasks.Update_userName_AsyncTask;
import com.cs616.studybuddy_mockup.Repositories.Courses;
import com.cs616.studybuddy_mockup.Repositories.Students;

import org.json.JSONObject;

import java.io.Writer;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by 1102764 on 2015-11-25.
 */
public class AccountActivity extends Fragment {


        private String[] arraySpinner;

        List<String> c  = new ArrayList<String>();
        //List<Course> cs = new ArrayList<>();
        Spinner s;
        Button addbtn;
        Button passwdReset;
        Button editText;
        Button removeClass;
        String userInfo [];
        ArrayAdapter<String> adapter;

        JSONObject userFname;
        JSONObject userPasswd;

        private int counter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final FragmentActivity faActivity  = (FragmentActivity)    super.getActivity();
        LinearLayout llLayout    = (LinearLayout)    inflater.inflate(R.layout.activity_account, container, false);


        userInfo    = new String[3];
        passwdReset = (Button) llLayout.findViewById(R.id.passwdbtn);
        editText    = (Button) llLayout.findViewById(R.id.editbtn);
        s           = (Spinner) llLayout.findViewById(R.id.Spinner01);
        adapter     = new ArrayAdapter<String>(super.getActivity(), android.R.layout.simple_spinner_item, c);
        addbtn      = (Button) llLayout.findViewById(R.id.addbtn);
        removeClass = (Button) llLayout.findViewById(R.id.remove);

        //add.setOnClickListener(this);


        //// TODO: 2015-12-16
        //  1) put courses in spinner
        //  2) Remove course
        //  3) Add course
        //  4) change user name
        //  5) change user password
        //  6) Style the UI


        userInfo[1] = MainActivity.currentUser.getFname().toString();    // name
        userInfo[0] = MainActivity.currentUser.getPassword().toString(); // password


        // get courses
        for ( Courses temp : MainActivity.db_courses) {

            c.add(temp.getTitle().toString());
        }

        update();


        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addClass(v);
            }
        });


        removeClass.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                removeClass(v);
            }
        });

        passwdReset.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                resetPassword(v);
            }
        });

        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editUserInfo(v);
            }
        });



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



        public void addClass(View v) {


            List<Course> courses = new ArrayList<>();
            int i=0;
            for(Courses item: MainActivity.db_courses){
                courses.add(new Course(item.getId(),item.getTitle(),0,MainActivity.colors.get(i++)));
            }



            AlertDialog.Builder builder =  new AlertDialog.Builder(super.getActivity());

            builder.setTitle("Add A Course");

            // Set up the input
            final EditText in = new EditText(super.getActivity());
            builder.setView(in);

            builder.setMessage("Please choose an account");




            builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {

                    c.add(in.getText().toString());
                    update();

                }
            });


            builder.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // do nothing
                }
            });

            builder.setIcon(android.R.drawable.ic_dialog_alert);
            builder.show();
        }

        public void removeClass(View v){

            new AlertDialog.Builder(super.getActivity())
                    .setTitle("Delete entry")
                    .setMessage("Are you sure you want to delete this entry?")


                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                            String text = s.getSelectedItem().toString();
                            c.remove(text);
                            update();
                        }
                    })


                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // do nothing
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();

        }



        public void update(){

            adapter = new ArrayAdapter<String>(super.getActivity(), android.R.layout.simple_spinner_item, c);
            adapter.notifyDataSetChanged();
            s.setAdapter(adapter);

        }



       public void update_User(){

       //use to JSON, up date database
           JSONObject jsonObject = new JSONObject();

           Students s = MainActivity.currentUser;
           s.setFname(userInfo[1].toString());

           //jsonObject.put("id", 10);
           //jsonObject.put("name", "myname");

           Update_userName_AsyncTask task = new Update_userName_AsyncTask();

           task.setDelegate(new User_AsyncResponse() {


               @Override
               public void onUserAsyncFinish(Boolean success) {
                   // when done task this runs!!
                   MainActivity.currentUser.setFname(userInfo[1].toString());

               }
           });


           task.execute(s);


       }


    public void update_Pass(){

        // use to JSON, update table

        Students s = MainActivity.currentUser;
        s.setPassword(userInfo[0].toString());


        Update_Passwd_AsyncTask task = new Update_Passwd_AsyncTask();

        task.setDelegate(new Passwd_AsyncResponse() {

            @Override
            public void onPasswdAsyncFinish(Boolean success) {
                /// when done task this runs!!
                MainActivity.currentUser.setPassword(userInfo[0].toString());

            }
        });

        task.execute(s);


    }


        public void resetPassword(View v){

            AlertDialog.Builder builder =  new AlertDialog.Builder(super.getActivity());
            final EditText pass = new EditText(super.getActivity());
            builder.setView(pass);


                    builder.setTitle("Resset password");
                    builder.setMessage("reset your password");

                    builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {


                        public void onClick(DialogInterface dialog, int which) {

                            String temp_pass = (pass.getText()).toString();
                            userInfo[0] = temp_pass;

                            // now set password in database
                            update_Pass();

                        }
                    });

                    builder.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // do nothing
                        }
                    });

            builder.setIcon(android.R.drawable.ic_dialog_alert);
            builder.show();
        }


        public void editUserInfo(View v){

            AlertDialog.Builder builder =  new AlertDialog.Builder(super.getActivity());
            //
            builder.setTitle("Change User info");

            // Set up the input
          /*
          LayoutInflater factory = LayoutInflater.from(this);

          //text_entry is an Layout XML file containing two text field to display in alert dialog
          final View textEntryView = factory.inflate(R.layout.text_entry, null);
          */


            builder.setMessage("NAME");
            final EditText name = new EditText(super.getActivity());
            name.setText(userInfo[1], TextView.BufferType.EDITABLE);
            builder.setView(name);


            builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {


                    //String temp_id   = (id.getText()).toString();
                    String temp_name = (name.getText()).toString();

                    // userInfo[0] = temp_id;
                    userInfo[1] = temp_name;

                    //now set user name in database
                    update_User();

                }
            });

            builder.show();
        }


    public void  updateAddClass(){







        Students s = MainActivity.currentUser;
        s.setPassword(userInfo[0].toString());


        Update_Passwd_AsyncTask task = new Update_Passwd_AsyncTask();

        task.setDelegate(new Passwd_AsyncResponse() {

            @Override
            public void onPasswdAsyncFinish(Boolean success) {
                /// when done task this runs!!
                MainActivity.currentUser.setPassword(userInfo[0].toString());

            }
        });

        task.execute(s);








    }


    public void updateRemoveClass(){








    }







    }

