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
public class StudentRepository implements Students_CRUDRepository<String, Students> {

    public static final String SERVER = "159.203.29.133";
    public static final int PORT = 9999;
    public static final String PREFIX = "http://" + SERVER + ":" + String.valueOf(PORT);
//    public static final String SERVER = "10.0.2.2";
//    public static final int PORT = 9999;
//    public static final String PREFIX = "http://" + SERVER + ":" + String.valueOf(PORT);

    private static String currentId;

    @Override
    public boolean create(Students element) throws IOException {
        HttpResponse response = HttpJsonRequest.make(PREFIX + "/Students/", "POST", element.toJson());
        if(response.getStatus() == 201){
            element.setUrl(response.getHeaders().get("Location").get(0)); // Header from POST contains the URL of the new user
            return true;
        }
        return false;
    }

    @Override
    public Students read(String id) throws IOException, JSONException {
        HttpResponse response = HttpJsonRequest.make(PREFIX + "/Students/" + id, "GET");
        Students receivedUser = Students.fromJson((new JSONObject(new JSONTokener(response.getBody()))));
        return receivedUser;
    }

    @Override
    public List<Students> readAll() throws IOException, JSONException, ParseException {
        return null;
    }

    @Override
    public boolean update(Students element) throws IOException {

        HttpResponse response = HttpJsonRequest.make(element.getUrl(), "PUT", element.toJson());

        if(response.getStatus()== 204){
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Students element) throws IOException {
        HttpResponse response = HttpJsonRequest.make(element.getUrl(), "DELETE");
        if(response.getStatus()== 204){
            return true;
        }
        return false;
    }
}
