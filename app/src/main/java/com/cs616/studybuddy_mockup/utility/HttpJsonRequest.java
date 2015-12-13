package com.cs616.studybuddy_mockup.utility;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * Created by ian on 15-10-24.
 */
public class HttpJsonRequest {

    /* private helper for makeHttp*Request() */
    private static void copyStreamBuffered(InputStream in, OutputStream out) throws IOException {
        BufferedInputStream inBuf = new BufferedInputStream(in, 1024);
        BufferedOutputStream outBuf = new BufferedOutputStream(out, 1024);
        int i;
        while((i = inBuf.read()) > 0)
            outBuf.write(i);
        outBuf.flush();
    }

    /**
     * Make an HTTP Request
     * @param urlStr URL for request
     * @param method HTTP method
     * @return An HttpResponse with status, headers and response body (if given).
     * @throws IOException
     */
    public static HttpResponse make(String urlStr, String method) throws IOException {
        return make(urlStr, method, null);
    }


    /**
     * Make an HTTP Request with JSON body
     * @param urlStr URL for request
     * @param method HTTP method
     * @param requestJson The JSON resquest body
     * @return An HttpResponse with status, headers and response body (if given).
     * @throws IOException
     */
    public static HttpResponse make(String urlStr, String method, String requestJson) throws IOException {
        // Create URL object for HTTP connection
        URL url = new URL(urlStr);

        // Create HTTP connection from URL
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod(method);

        // If the user has specified JSON for the request, configure connection, add content-type header
        // and copy JSON to output stream
        if(requestJson != null) {
            con.setDoOutput(true);
            con.setRequestProperty("content-type", "application/json");
            copyStreamBuffered(new ByteArrayInputStream(requestJson.getBytes()), con.getOutputStream());
        }

        // Retrieve information connection
        Map<String, List<String>> headers = con.getHeaderFields();
        int status = con.getResponseCode();
        String response = null;
        try {
            // copy JSON from output stream
            ByteArrayOutputStream responseBytes = new ByteArrayOutputStream();
            copyStreamBuffered(con.getInputStream(), responseBytes);
            response = new String(responseBytes.toByteArray());
        }
        catch(IOException e) { /* no response body */ }

        return new HttpResponse(status, headers, response);
//       /* // Create URL object for HTTP connection
//        URL url = new URL(urlStr);
//
//        // Create HTTP connection from URL
//        HttpURLConnection con = (HttpURLConnection) url.openConnection();
//        con.setRequestMethod(method);
//
//        // If the user has specified JSON for the request, configure connection, add content-type header
//        // and copy JSON to output stream
//        if(requestJson != null) {
//            con.setDoOutput(true);
//            con.setRequestProperty("content-type", "application/json");
//            copyStreamBuffered(new ByteArrayInputStream(requestJson.getBytes()), con.getOutputStream());
//        }
//
//        // Retrieve information connection
//        Map<String, List<String>> headers = con.getHeaderFields();
//        int status = con.getResponseCode();
//        String response = null;
//        try {
//            // copy JSON from output stream
//            ByteArrayOutputStream responseBytes = new ByteArrayOutputStream();
//            copyStreamBuffered(con.getInputStream(), responseBytes);
//            response = new String(responseBytes.toByteArray());
//        }
//        catch(IOException e) { *//* no response body *//* }
//
//        return new HttpResponse(status, headers, response);*/
    }

    private HttpJsonRequest() {}  // private constructor --> not-instantiable

}
