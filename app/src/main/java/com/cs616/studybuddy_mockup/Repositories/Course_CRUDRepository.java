package com.cs616.studybuddy_mockup.Repositories;

import org.json.JSONException;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * Created by 1102764 on 2015-12-17.
 */
public interface Course_CRUDRepository<R,T> {

    T read(R id) throws IOException, JSONException, ParseException;
    List<T> readAll(R url) throws IOException, JSONException, ParseException;
    boolean update(T element) throws IOException;
    boolean delete(T element) throws IOException;




}
