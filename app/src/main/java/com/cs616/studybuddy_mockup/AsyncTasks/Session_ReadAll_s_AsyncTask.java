package com.cs616.studybuddy_mockup.AsyncTasks;

import android.os.AsyncTask;

import com.cs616.studybuddy_mockup.Repositories.SessionRepository;
import com.cs616.studybuddy_mockup.Repositories.Sessions;
import com.cs616.studybuddy_mockup.StatisticsActivity;

import org.json.JSONException;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * Created by Dominic on 2015-12-16.
 */
public class Session_ReadAll_s_AsyncTask extends AsyncTask<String, Integer, Boolean> {
    private List<Sessions> sessions;
    private StatisticsActivity delegate;
    public void setDelegate(StatisticsActivity delegate) {
        this.delegate = delegate;
    }

    @Override
    protected Boolean doInBackground(String... params) {
        try {
            sessions = new SessionRepository().readAll(params[0]);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        if(delegate != null)
            delegate.onSessionAsyncFinish(sessions);
    }

}
