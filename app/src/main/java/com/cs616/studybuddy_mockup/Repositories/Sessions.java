package com.cs616.studybuddy_mockup.Repositories;



/**
 * Created by 1286947 on 2015-12-10.
 */

public class Sessions {

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCourseNo() {
        return courseNo;
    }

    public void setCourseNo(String courseNo) {
        this.courseNo = courseNo;
    }

    public Students getStudentId() {
        return studentId;
    }

    public void setStudentId(Students studentId) {
        this.studentId = studentId;
    }

    public String getSecondsStudied() {
        return secondsStudied;
    }

    public void setSecondsStudied(String secondsStudied) {
        this.secondsStudied = secondsStudied;
    }


    private long id;

    private String courseNo;

    private Students studentId;

    String secondsStudied;


}
