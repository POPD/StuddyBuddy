package com.cs616.studybuddy_mockup.AsyncTasks;

import android.os.AsyncTask;

import com.cs616.studybuddy_mockup.ExtraActivity;
import com.cs616.studybuddy_mockup.Repositories.SessionRepository;
import com.cs616.studybuddy_mockup.Repositories.Sessions;
import com.cs616.studybuddy_mockup.SessionActivity;
import com.cs616.studybuddy_mockup.StatisticsActivity;

import java.io.IOException;

/**
 * Created by Dominic on 2015-12-16.
 */
public class Session_Create_AsyncTask extends AsyncTask<Sessions, Integer, Boolean> {
    private Sessions sessions;
    private SessionActivity SessionsDelegate;
    private ExtraActivity ExtraDelegate;
    public void setDelegate(SessionActivity delegate) {
        this.SessionsDelegate = delegate;
    }

    public void setDelegate(ExtraActivity delegate) {
        this.ExtraDelegate = delegate;
    }

    @Override
    protected Boolean doInBackground(Sessions... params) {
        try {
            sessions = new SessionRepository().create(params[0]);
        } catch (IOException e) {
            e.printStackTrace();

        }
        if(sessions == null)
            return false;
        else
            return true;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        if(SessionsDelegate != null){
            if(result){
                SessionsDelegate.onCreateSessionAsyncFinish(sessions);
            }
            else{
                SessionsDelegate.onSessionAsyncFinish(false);
            }
        }
        else if(ExtraDelegate != null){
            if(result){
                ExtraDelegate.onCreateSessionAsyncFinish(sessions);
            }
            else{
                ExtraDelegate.onSessionAsyncFinish(false);
            }
        }

    }

}
