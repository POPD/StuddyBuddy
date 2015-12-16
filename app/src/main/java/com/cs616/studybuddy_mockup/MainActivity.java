package com.cs616.studybuddy_mockup;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
//import android.app.Fragment;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Bundle;
//import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.cs616.studybuddy_mockup.Repositories.Students;

public class MainActivity extends Fragment {
    public static Students currentUser;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final FragmentActivity faActivity  = (FragmentActivity)    super.getActivity();
        RelativeLayout        llLayout    = (RelativeLayout)    inflater.inflate(R.layout.activity_main, container, false);

//
//        //SETTING UP AVTIVITY BUTTONS
        final Button session  = (Button) llLayout.findViewById(R.id.btn_session);
        final Button extra    = (Button) llLayout.findViewById(R.id.btn_extra);
        final Button calendar = (Button) llLayout.findViewById(R.id.btn_calendar);

        session.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.super.getActivity(), SessionList.class);
                startActivity(intent);
            }
        });

        extra.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Fragment fragment = new ExtraActivity();
                FragmentManager fragmentManager = faActivity.getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container, fragment);
                fragmentTransaction.commit();
            }
        });
        calendar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity.super.getActivity(), CalendarActivity.class);
                startActivity(intent1);
            }
        });
//        Toast toast = Toast.makeText(MainActivity.super.getActivity(),"Welcome "+currentUser.getFname(), Toast.LENGTH_SHORT);
//        toast.show();
        return llLayout; // We must return the loaded Layout
    }
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        getActionBar().setLogo(R.mipmap.book);
//        getActionBar().setDisplayShowHomeEnabled(true);
//
//        //SETTING UP THE MENU BUTTONS
//        final Button home    = (Button) findViewById(R.id.btn_home);
//        final Button stats   = (Button) findViewById(R.id.btn_stats);
//        final Button account = (Button) findViewById(R.id.btn_account);
//
//
//
//        stats.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, StatisticsActivity.class);
//                startActivity(intent);
//            }
//        });
//        account.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, AccountActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        //SETTING UP AVTIVITY BUTTONS
//        final Button session  = (Button) findViewById(R.id.btn_session);
//        final Button extra    = (Button) findViewById(R.id.btn_extra);
//        final Button calendar = (Button) findViewById(R.id.btn_calendar);
//
//        session.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, SessionList.class);
//                startActivity(intent);
//            }
//        });
//        extra.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, ExtraActivity.class);
//                startActivity(intent);
//            }
//        });
//        calendar.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, CalendarActivity.class);
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

    private void createHeadView() {
        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
                PixelFormat.TRANSLUCENT);
        params.gravity = Gravity.LEFT;

        Context context = super.getContext();
        FrameLayout frameLayout = new FrameLayout(context);

        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.addView(frameLayout, params);

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // Here is the place where you can inject whatever layout you want.
        layoutInflater.inflate(R.layout.activity_bubble, frameLayout);
    }
}
