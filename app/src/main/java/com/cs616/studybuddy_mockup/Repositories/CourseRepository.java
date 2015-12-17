package com.cs616.studybuddy_mockup.Repositories;

import com.cs616.studybuddy_mockup.utility.HttpJsonRequest;
import com.cs616.studybuddy_mockup.utility.HttpResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * Created by Alex on 12/11/2015.
 */
public class CourseRepository implements Course_CRUDRepository<String, Courses>{


    public static final String SERVER = "159.203.29.133";
    public static final int PORT = 9999;
    public static final String PREFIX = "http://" + SERVER + ":" + String.valueOf(PORT);
//    public static final String SERVER = "10.0.2.2";
//    public static final int PORT = 9999;
//    public static final String PREFIX = "http://" + SERVER + ":" + String.valueOf(PORT);

      private static String currentId;

    @Override
    public Courses read(String id) throws IOException, JSONException {

        HttpResponse response = HttpJsonRequest.make(PREFIX + "/Courses/" + id, "GET");
        Courses receivedUser = Courses.fromJson((new JSONObject(new JSONTokener(response.getBody()))));

        return receivedUser;

    }

    @Override
    public List<Courses> readAll(String url) throws IOException, JSONException, ParseException {

        HttpResponse response = HttpJsonRequest.make(url + "/Courses", "GET");
        List<Courses> receivedCourses = Courses.fromJson((new JSONObject(new JSONTokener(response.getBody())).getJSONObject("_embedded").getJSONArray("Courses")));
        return receivedCourses;
    }

    @Override
    public boolean update(Courses element) throws IOException {


        return false;
    }

    @Override
    public boolean delete(Courses element) throws IOException {
        return false;
    }


    public List<Courses> readAll() throws IOException, JSONException, ParseException {


        return null;
    }





    public CourseRepository() {
    }


}
