package com.cs616.studybuddy_mockup.AsyncResponse;

import com.cs616.studybuddy_mockup.Repositories.Students;

import java.util.List;

/**
 * Created by Alex on 12/11/2015.
 */

public interface Login_AsyncResponse {
    void onLoginAsyncFinish(Boolean success);
    void onLoginAsyncFinish(Students success);
}
