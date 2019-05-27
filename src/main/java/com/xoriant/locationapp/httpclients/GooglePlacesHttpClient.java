/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xoriant.locationapp.httpclients;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;

/**
 *
 * @author akshay.velhal
 */
@Service
public class GooglePlacesHttpClient implements PlacesHttpClient {

    @Override
    public String searchPlaces(String text) throws MalformedURLException, IOException {

        String url = "https://maps.googleapis.com/maps/api/place/findplacefromtext/json";
        Map<String, String> params = new HashMap<>();
        params.put("key", "TODO");
        params.put("input", text);
        params.put("inputtype", "textquery");

        return sendGet(url, params);
    }

    private String sendGet(String inputUrl, Map<String, String> params) throws MalformedURLException, IOException {
        URL url = new URL(inputUrl);
        StringBuilder response = new StringBuilder();
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        addParamsString(params, httpURLConnection);

        int responseCode = httpURLConnection.getResponseCode();
        if (responseCode == 200) {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()))) {
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
            }
        }
        return response.toString();
    }

    private void addParamsString(Map<String, String> params, HttpURLConnection httpURLConnection) throws IOException {
        if (params != null && !params.isEmpty()) {
            StringBuilder result = new StringBuilder();
            for (Map.Entry<String, String> entry : params.entrySet()) {
                result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
                result.append("=");
                result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
                result.append("&");
            }

            String resultString = result.toString();
            resultString = resultString.substring(0, resultString.length() - 1);
            httpURLConnection.setDoOutput(true);
            try (DataOutputStream out = new DataOutputStream(httpURLConnection.getOutputStream())) {
                out.writeBytes(resultString);
                out.flush();
            }

        }
    }

//    public static String getParamsString(Map<String, String> params) 
//      throws UnsupportedEncodingException{
//        StringBuilder result = new StringBuilder();
// 
//        for (Map.Entry<String, String> entry : params.entrySet()) {
//          result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
//          result.append("=");
//          result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
//          result.append("&");
//        }
// 
//        String resultString = result.toString();
//        return resultString.length() > 0
//          ? resultString.substring(0, resultString.length() - 1)
//          : resultString;
//    }
}