package com.cs616.studybuddy_mockup.AsyncTasks;

import android.os.AsyncTask;

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
    private SessionActivity delegate;
    public void setDelegate(SessionActivity delegate) {
        this.delegate = delegate;
    }

    @Override
    protected Boolean doInBackground(Sessions... params) {
        try {
            sessions = new SessionRepository().create(params[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        if(delegate != null)
            delegate.onCreateSessionAsyncFinish(sessions);
    }

}
