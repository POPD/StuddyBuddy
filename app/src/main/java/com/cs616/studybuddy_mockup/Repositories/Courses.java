package com.cs616.studybuddy_mockup.Repositories;

import java.util.List;

/**
 * Created by 1286947 on 2015-12-10.
 */

public class Courses {

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCourseNo(String courseNo) {
        this.courseNo = courseNo;
    }

    private long id;

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCourseNo() {
        return courseNo;
    }

    private String title;

    private String courseNo;

}
