package com.cs616.studybuddy_mockup;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import java.util.List;
import java.util.ArrayList;

import android.widget.TextView;
import android.widget.Toolbar;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by 1102764 on 2015-11-25.
 */
public class MyAccount extends Activity {


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
        protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_account);
            //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            //setSupportActionBar(toolbar);

            userInfo    = new String[3];
            passwdReset = (Button) findViewById(R.id.passwdbtn);
            editText    = (Button) findViewById(R.id.editbtn);
            s       =  (Spinner) findViewById(R.id.Spinner01);
            adapter =  new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, c);
            addbtn = (Button) findViewById(R.id.addbtn);

            //add.setOnClickListener(this);

            userInfo[1] = "Tom";  //name
            userInfo[0] = "2345"; //id


            c.add("Chemistry");
            c.add("Calculus 3");
            c.add("Programming 4");
            c.add( "Algorithms & DataStructures");
            c.add("DataBase");
            update();

            //Button remove = (Button) findViewById(R.id.remove);
            //remove.setOnClickListener(this);

        /* this.arraySpinner = new String[] {
                "Chemistry", "Calculus 3", "Programming 4", "Algorithms & DataStructures", "DataBase"
        }; */





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



/*/
        s.setOnClickListener(new AdapterView.OnClickListener() {

            @Override
            public void onClick(View v) {
                removeClass(v);

            }
        });
        */



          /*
        s.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View v, int arg2, long arg3) {
                removeClass(v);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                //optionally do something here
            }
        });
        */

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
            //SETTING UP THE MENU BUTTONS
            final Button home    = (Button) findViewById(R.id.btn_home);
            final Button stats   = (Button) findViewById(R.id.btn_stats);
            final Button account = (Button) findViewById(R.id.btn_account);


            home.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(MyAccount.this, MainActivity.class);
                    startActivity(intent);
                }
            });
            stats.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(MyAccount.this, StatisticsActivity.class);
                    startActivity(intent);
                }
            });

        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;
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

            AlertDialog.Builder builder =  new AlertDialog.Builder(this);

            builder.setTitle("Add entry");

            // Set up the input
            final EditText in = new EditText(this);
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

            new AlertDialog.Builder(this)
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

            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, c);
            adapter.notifyDataSetChanged();
            s.setAdapter(adapter);

        }

        public void resetPassword(View v){

            new AlertDialog.Builder(this)
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



            AlertDialog.Builder builder =  new AlertDialog.Builder(this);
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
            final EditText name = new EditText(this);
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

