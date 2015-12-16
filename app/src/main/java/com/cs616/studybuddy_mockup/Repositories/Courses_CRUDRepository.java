package com.cs616.studybuddy_mockup.Repositories;

import org.json.JSONException;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * Created by ian on 15-10-24.
 */
public interface Courses_CRUDRepository<R, T> {
    T read(R url) throws IOException, JSONException, ParseException;
    List<T> readAll() throws IOException, JSONException, ParseException;
}
