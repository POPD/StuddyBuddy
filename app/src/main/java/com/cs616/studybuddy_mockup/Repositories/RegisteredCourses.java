package com.cs616.studybuddy_mockup.Repositories;


import java.io.IOException;

/**
 * Created by 1286947 on 2015-12-10.
 */


public class RegisteredCourses {
    public String toJson() throws IOException {

        StringBuilder sb = new StringBuilder();
        //Make sure we have the required fields
        if (courseno == null || studentid == 0) {
            throw new IOException("Missing required fields for JSON");
        }
        // --- APPEND THE REQUIRED FIELDS --- //
        sb.append("{ \"courseNo\" : \"");
        sb.append(courseno);
        sb.append("\", \"studentId\" : \"");
        sb.append(studentid);
        sb.append("\"}");

        return sb.toString();

    }

    private long id;

    private String courseno;

    private long studentid;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCourseno() {
        return courseno;
    }

    public void setCourseno(String courseno) {
        this.courseno = courseno;
    }

    public long getStudentid() {
        return studentid;
    }

    public void setStudentid(long studentid) {
        this.studentid = studentid;
    }
}
