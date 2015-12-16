package com.cs616.studybuddy_mockup.AsyncTasks;

import android.os.AsyncTask;

import com.cs616.studybuddy_mockup.AsyncResponse.Create_AsyncResponse;
import com.cs616.studybuddy_mockup.AsyncResponse.Login_AsyncResponse;
import com.cs616.studybuddy_mockup.Repositories.StudentRepository;
import com.cs616.studybuddy_mockup.Repositories.Students;

import java.io.IOException;

/**
 * Created by Alex on 12/11/2015.
 */
public class Student_Create_AsyncTask extends AsyncTask<Students, Integer, Boolean> {
    private Create_AsyncResponse delegate;
    private Students student;
    public Student_Create_AsyncTask setDelegate(Create_AsyncResponse delegate) {
        this.delegate = delegate;
        return null;
    }

    @Override
    protected Boolean doInBackground(Students... params) {
        boolean valid=false;
        try {
           student = new StudentRepository().create(params[0]);
        } catch (IOException e) {
            valid=false;
        }
        return valid;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        if(delegate != null)
            delegate.onCreateAsyncFinish(student);
    }
}
