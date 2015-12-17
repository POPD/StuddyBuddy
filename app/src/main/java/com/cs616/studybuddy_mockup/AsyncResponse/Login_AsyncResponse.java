package com.cs616.studybuddy_mockup.AsyncResponse;

import android.support.annotation.Nullable;

import com.cs616.studybuddy_mockup.Course;
import com.cs616.studybuddy_mockup.Repositories.Courses;
import com.cs616.studybuddy_mockup.Repositories.Sessions;
import com.cs616.studybuddy_mockup.Repositories.Students;

import java.util.List;

/**
 * Created by Alex on 12/11/2015.
 */

public interface Login_AsyncResponse {
    void onLoginAsyncFinish(Students success);
    void onLoginAsyncFinish(List<Courses> courses);

}
