package com.cs616.studybuddy_mockup.AsyncTasks;

import android.os.AsyncTask;

import com.cs616.studybuddy_mockup.AsyncResponse.Login_AsyncResponse;
import com.cs616.studybuddy_mockup.AsyncResponse.Passwd_AsyncResponse;
import com.cs616.studybuddy_mockup.AsyncResponse.User_AsyncResponse;
import com.cs616.studybuddy_mockup.Repositories.StudentRepository;
import com.cs616.studybuddy_mockup.Repositories.Students;

import java.io.IOException;

/**
 * Created by 1102764 on 2015-12-17.
 */
public class Update_userName_AsyncTask extends AsyncTask<Students, Integer, Boolean> {


    private String   passwd;
    private User_AsyncResponse delegate;

    public Student_Create_AsyncTask setDelegate(User_AsyncResponse delegate) {

        this.delegate = delegate;
        return null;
    }

    //passwd = MainActivity.currentUser.getPassword();



    @Override
    protected Boolean doInBackground(Students... params) {

        Students s = params[0];

        boolean valid=false;

        try {
            if(( new StudentRepository().update(params[0])==true))
                valid=true;

        } catch (IOException e) {
            valid=false;
        }

        return valid;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        if(delegate != null)
            delegate.onUserAsyncFinish(result);
    }
}