package com.cs616.studybuddy_mockup.AsyncTasks;

import android.os.AsyncTask;

import com.cs616.studybuddy_mockup.AsyncResponse.Login_AsyncResponse;
import com.cs616.studybuddy_mockup.Repositories.StudentRepository;
import com.cs616.studybuddy_mockup.Repositories.Students;

import org.json.JSONException;

import java.io.IOException;

/**
 * Created by Alex on 12/11/2015.
 */
public class Student_Login_AsyncTask extends AsyncTask<String, Integer, Boolean> {
    private Students usr;
    private Login_AsyncResponse delegate;
    public void setDelegate(Login_AsyncResponse delegate) {
        this.delegate = delegate;
    }

    @Override
    protected Boolean doInBackground(String... params) {
        try {
            usr = new StudentRepository().read(params[0]);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        if(delegate != null)
            delegate.onLoginAsyncFinish(usr);
    }
}
