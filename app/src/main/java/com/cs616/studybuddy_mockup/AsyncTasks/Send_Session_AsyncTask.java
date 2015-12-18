package com.cs616.studybuddy_mockup.AsyncTasks;

import android.os.AsyncTask;

import com.cs616.studybuddy_mockup.Repositories.SessionRepository;
import com.cs616.studybuddy_mockup.Repositories.Sessions;
import com.cs616.studybuddy_mockup.SessionActivity;

/**
 * Created by zach on 2015-12-16.
 */
public class Send_Session_AsyncTask extends AsyncTask<Sessions, Void, Boolean> {
    SessionActivity delegate;
    Boolean taskSession;
    public void setDelegate(SessionActivity delegate) {
        this.delegate = delegate;
    }

    @Override
    protected Boolean doInBackground(Sessions... params) {
        try {
            taskSession = new SessionRepository().add(params[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return taskSession;
    }
    protected void onPostExecute(Boolean result) {
        if(delegate != null)
            delegate.onSessionAsyncFinish(true);
    }
}
