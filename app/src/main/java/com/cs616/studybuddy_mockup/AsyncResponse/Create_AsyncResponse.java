package com.cs616.studybuddy_mockup.AsyncResponse;

import com.cs616.studybuddy_mockup.Repositories.Courses;
import com.cs616.studybuddy_mockup.Repositories.Students;

import java.util.List;

/**
 * Created by Alex on 12/11/2015.
 */

public interface Create_AsyncResponse {
    void onCreateAsyncFinish(Students student);
}
