package com.cs616.studybuddy_mockup.Repositories;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    public long getSecondsStudied() {
        return secondsStudied;
    }

    public void setSecondsStudied(long secondsStudied) {
        this.secondsStudied = secondsStudied;
    }


    private long id;

    private String courseNo;

    private Students studentId;

    private long secondsStudied;


    public static List<Sessions> fromJson(JSONArray root) throws IOException, JSONException {
        List<Sessions> sessions = new ArrayList<>();
        for(int i = 0; i < root.length(); i++)
            sessions.add(fromJson(root.getJSONObject(i)));
        return sessions;
    }

    public static Sessions fromJson(JSONObject root) throws IOException, JSONException {
        Sessions sessions = new Sessions();
        // --- GET THE REQUIRED FIELDS --- //
        String courseNo = root.getString("courseNo");
        Long secondsStudied= root.getLong("secondsStudied");

        if(courseNo == null || secondsStudied == null) throw new IOException("Missing required fields for JSON session");

        sessions.setCourseNo(courseNo);
        sessions.setSecondsStudied(secondsStudied);

        return sessions;
    }

}
