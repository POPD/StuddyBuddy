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
 * Created by Dominic on 12/11/2015.
 */
public class SessionRepository implements Session_CRUDRepository<String, Sessions>{

    public static final String SERVER = "159.203.29.133";
    public static final int PORT = 9999;
    public static final String PREFIX = "http://" + SERVER + ":" + String.valueOf(PORT);


    @Override
    public boolean add(Sessions element) throws IOException {
        return false;
    }

    @Override
    public Sessions read(String id) throws IOException, JSONException, ParseException {
        return null;
    }

    @Override
    public List<Sessions> readAll(String url) throws IOException, JSONException, ParseException {
        HttpResponse response = HttpJsonRequest.make(url + "/Sessions", "GET");
        List<Sessions> receivedSessions = Sessions.fromJson((new JSONObject(new JSONTokener(response.getBody())).getJSONObject("_embedded").getJSONArray("Sessions")));
        return receivedSessions;
    }

    @Override
    public boolean update(Sessions element) throws IOException {
        return false;
    }

    @Override
    public boolean delete(Sessions element) throws IOException {
        return false;
    }
}
