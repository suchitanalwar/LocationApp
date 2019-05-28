/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xoriant.locationapp.httpclients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 *
 * @author nalwar_s
 */
@Service
public class GooglePlacesHttpClient implements PlacesHttpClient {

    @Value("${API_KEY}")
    private String apiKey;

    @Value("${SEARCH_PLACES_URL}")
    private String searchPlacesUrl;

    @Value("${DETAILS_PLACES_URL}")
    private String detailsPlacesUrl;

    @Override
    public String searchPlaces(String text) throws MalformedURLException, IOException {
        Map<String, String> params = new HashMap<>();
        params.put("key", apiKey);
        params.put("query", text);
        return sendGet(searchPlacesUrl, params);
    }

    private String sendGet(String inputUrl, Map<String, String> params) throws MalformedURLException, IOException {

        String queryString = addParamsString(params);
        URL url = new URL(queryString == "" ? inputUrl : inputUrl + "?" + queryString);
        StringBuilder response = new StringBuilder();
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");

        int responseCode = httpURLConnection.getResponseCode();
        if (responseCode == HttpStatus.OK.value()) {
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

    private String addParamsString(Map<String, String> params) throws IOException {
        String resultString = "";
        if (params != null && !params.isEmpty()) {
            StringBuilder result = new StringBuilder();
            for (Map.Entry<String, String> entry : params.entrySet()) {
                result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
                result.append("=");
                result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
                result.append("&");
            }
            resultString = result.toString().substring(0, result.toString().length() - 1);
        }
        return resultString;
    }

    @Override
    public String getPlaceDetails(String placeId) throws MalformedURLException, IOException {

        Map<String, String> params = new HashMap<>();
        params.put("key", apiKey);
        params.put("placeid", placeId);

        return sendGet(detailsPlacesUrl, params);
    }
}
