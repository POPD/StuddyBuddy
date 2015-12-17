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

public class Courses {
    public static List<Courses> fromJson(JSONArray root) throws IOException, JSONException {
        List<Courses> users = new ArrayList<>();
        for(int i = 0; i < root.length(); i++)
            users.add(fromJson(root.getJSONObject(i)));
        return users;
    }
    public static Courses fromJson(JSONObject root) throws IOException, JSONException {
        Courses course = new Courses();
        // --- GET THE REQUIRED FIELDS --- //
        String title = root.getString("title");
        Long id= root.getLong("id");

        if(title == null || id == null) throw new IOException("Missing required fields for JSON course");

        course.setTitle(title);
        course.setId(id);
        // extract the post resource URL from the "_links" object
        JSONObject links = root.getJSONObject("_links");
        course.setCourseNo(links.getJSONObject("self").getString("href"));

        return course;
    }
    public String toJson() throws IOException {

        StringBuilder sb = new StringBuilder();
        //Make sure we have the required fields
        if (title == null || courseNo == null) {
            throw new IOException("Missing required fields for JSON");
        }
        // --- APPEND THE REQUIRED FIELDS --- //
        sb.append("{ \"title\" : \"");
        sb.append(title);
        sb.append("\", \"courseNo\" : \"");
        sb.append(courseNo);
        sb.append("\"}");

        return sb.toString();

    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCourseNo(String courseNo) {
        this.courseNo = courseNo;
    }

    private Long id;

    public Long getId() {
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
