package com.cs616.studybuddy_mockup.AsyncTasks;

import android.os.AsyncTask;

import com.cs616.studybuddy_mockup.AsyncResponse.Create_AsyncResponse;
import com.cs616.studybuddy_mockup.AsyncResponse.Login_AsyncResponse;
import com.cs616.studybuddy_mockup.AsyncResponse.PickCourses_AsyncResponse;
import com.cs616.studybuddy_mockup.Repositories.Courses;
import com.cs616.studybuddy_mockup.Repositories.RegisteredRepository;

import org.json.JSONException;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * Created by Alex on 12/11/2015.
 */
public class Registered_Courses_Create_AsyncTask extends AsyncTask<List<Courses>, Integer, Boolean> {
    private List<Courses> courses;
    private PickCourses_AsyncResponse delegate;
    public void setDelegate(PickCourses_AsyncResponse delegate) {
        this.delegate = delegate;
    }

    @Override
    protected Boolean doInBackground(List<Courses>... params) {
        try {
            courses = new RegisteredRepository().add(params[0]);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        if(delegate != null)
            delegate.onCourseRegisterAsyncFinish(courses);
    }
}
