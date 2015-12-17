package com.cs616.studybuddy_mockup.Repositories;

import com.cs616.studybuddy_mockup.Course;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 1286947 on 2015-12-10.
 */

public class Students {
    public static List<Students> fromJson(JSONArray root) throws IOException, JSONException {
        List<Students> users = new ArrayList<>();
        for(int i = 0; i < root.length(); i++)
            users.add(fromJson(root.getJSONObject(i)));
        return users;
    }
    public static Students fromJson(JSONObject root) throws IOException, JSONException {
        Students user = new Students();
        // --- GET THE REQUIRED FIELDS --- //
        String fname = root.getString("fname");
        String lname= root.getString("lname");
        String pass= root.getString("password");
        long id= root.getLong("id");

        if(fname == null || lname == null|| pass == null) throw new IOException("Missing required fields for JSON user");

        user.setFname(fname);
        user.setLname(lname);
        user.setPassword(pass);
        user.setId(id);

        // extract the post resource URL from the "_links" object
        JSONObject links = root.getJSONObject("_links");
        user.setUrl(links.getJSONObject("self").getString("href"));

        return user;
    }
    public String toJson() throws IOException {

        StringBuilder sb = new StringBuilder();
        //Make sure we have the required fields
        if (fname == null || lname == null || password == null || id == 0) {
            throw new IOException("Missing required fields for JSON");
        }
        // --- APPEND THE REQUIRED FIELDS --- //
        sb.append("{ \"fname\" : \"");
        sb.append(fname);
        sb.append("\", \"lname\" : \"");
        sb.append(lname);
        sb.append("\", \"password\" : \"");
        sb.append(password);
        sb.append("\", \"studentId\" : \"");
        sb.append(id);
        sb.append("\"}");

        return sb.toString();

    }
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private String url;
    private long id;

    private String password;

    private String fname;

    private String lname;

    private long studentId;

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public long getId() {

        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    private List<Course> courses;

}
