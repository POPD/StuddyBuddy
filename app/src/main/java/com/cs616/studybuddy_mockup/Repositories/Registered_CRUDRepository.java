package com.cs616.studybuddy_mockup.Repositories;

import org.json.JSONException;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * Created by ian on 15-10-24.
 */
public interface Registered_CRUDRepository<R, T> {
    boolean add(T element) throws IOException;
    T read(R id) throws IOException, JSONException, ParseException;
    List<T> readAll(R url) throws IOException, JSONException, ParseException;
    boolean update(T element) throws IOException;
    boolean delete(T element) throws IOException;
}
