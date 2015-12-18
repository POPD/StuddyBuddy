package com.cs616.studybuddy_mockup.AsyncResponse;

import com.cs616.studybuddy_mockup.Repositories.Courses;
import com.cs616.studybuddy_mockup.Repositories.Students;

import java.util.List;

/**
 * Created by Alex on 12/11/2015.
 */

public interface PickCourses_AsyncResponse {
    void onCourseReadAsyncFinish(List<Courses> courses);
    void onCourseRegisterAsyncFinish(List<Courses> courses);
    void onCourseReadAsyncFinish(Boolean success);
}
