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

import java.util.List;
import java.util.ArrayList;

/**
 * Created by 1102764 on 2015-11-25.
 */
public class AccountActivity extends Fragment {


        private String[] arraySpinner;

        List<String> c = new ArrayList<String>();
        Spinner s;
        Button addbtn;
        Button passwdReset;
        Button editText;
        String userInfo [];
        ArrayAdapter<String> adapter;
        private int counter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final FragmentActivity faActivity  = (FragmentActivity)    super.getActivity();
        LinearLayout llLayout    = (LinearLayout)    inflater.inflate(R.layout.activity_account, container, false);

        userInfo    = new String[3];
        passwdReset = (Button) llLayout.findViewById(R.id.passwdbtn);
        editText    = (Button) llLayout.findViewById(R.id.editbtn);
        s       =  (Spinner) llLayout.findViewById(R.id.Spinner01);
        adapter =  new ArrayAdapter<String>(super.getActivity(), android.R.layout.simple_spinner_item, c);
        addbtn = (Button) llLayout.findViewById(R.id.addbtn);

        //add.setOnClickListener(this);

        userInfo[1] = "Tom";  //name
        userInfo[0] = "2345"; //id


        c.add("Chemistry");
        c.add("Calculus 3");
        c.add("Programming 4");
        c.add("Algorithms & DataStructures");
        c.add("DataBase");
        update();


        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addClass(v);
            }
        });


        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View v, int arg2, long arg3) {
                removeClass(v);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                //optionally do something here
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

            AlertDialog.Builder builder =  new AlertDialog.Builder(super.getActivity());

            builder.setTitle("Add entry");

            // Set up the input
            final EditText in = new EditText(super.getActivity());
            builder.setView(in);

            builder.setMessage("Add entry?");




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

        public void resetPassword(View v){

            new AlertDialog.Builder(super.getActivity())
                    .setTitle("Resset password")
                    .setMessage("An email has been sent to reset your password")

                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                            //send an email

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


          /*
          builder.setMessage("ID");
          final EditText id = new EditText(this);
          id.setText(userInfo[0], TextView.BufferType.EDITABLE);
          builder.setView(id);
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


                }
            });

            builder.show();
        }



    }

