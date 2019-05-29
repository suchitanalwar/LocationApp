/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xoriant.locationapp.service;

import com.xoriant.locationapp.exception.PlaceParseException;
import com.xoriant.locationapp.httpclients.PlacesHttpClient;
import com.xoriant.locationapp.model.Result;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 *
 * @author nalwar_s
 */
@RunWith(PowerMockRunner.class)
public class LocationServiceTest {
    
    
    @InjectMocks
    LocationService locationService = new LocationServiceImpl();
    
    @Mock
    PlacesHttpClient placesHttpClient;
    
    @Test()
    public void testSearchPlacesOneSearchResult() throws IOException, MalformedURLException, PlaceParseException {
        String searchText = "Museum";
        Mockito.when(placesHttpClient.searchPlaces(searchText)).thenReturn("{\n" +
"    \"html_attributions\": [],\n" +
"    \"results\": [\n" +
"        {\n" +
"            \"formatted_address\": \"The Lodhi, Lodhi Rd, CGO Complex, Pragati Vihar, New Delhi, Delhi 110003, India\",\n" +
"            \"icon\": \"https://maps.gstatic.com/mapfiles/place_api/icons/restaurant-71.png\",\n" +
"            \"id\": \"cf0a47eabb4007aa3140849ea2cc82d566560eb1\",\n" +
"            \"name\": \"Indian Accent\",\n" +
"            \"opening_hours\": {\n" +
"                \"open_now\": true\n" +
"            },\n" +
"            \"place_id\": \"ChIJd-3rnjD9DDkRJixh51I7E_0\",\n" +
"            \"plus_code\": {\n" +
"                \"compound_code\": \"H6RQ+P7 New Delhi, Delhi, India\",\n" +
"                \"global_code\": \"7JWVH6RQ+P7\"\n" +
"            },\n" +
"            \"price_level\": 4,\n" +
"            \"rating\": 4.6,\n" +
"            \"reference\": \"ChIJd-3rnjD9DDkRJixh51I7E_0\",\n" +
"            \"types\": [\n" +
"                \"bar\",\n" +
"                \"restaurant\",\n" +
"                \"food\",\n" +
"                \"point_of_interest\",\n" +
"                \"establishment\"\n" +
"            ],\n" +
"            \"user_ratings_total\": 1357\n" +
"        }\n" +
"    ],\n" +
"    \"status\": \"OK\"\n" +
"}");
        
        List<Result> list = locationService.searchPlaces(searchText);
        Assert.assertNotNull("Candidates should not be null", list);
        Assert.assertEquals("There should be one candidate returned", 1, list.size());
    }
    
    @Test(expected = PlaceParseException.class)
    public void testSearchPlacesException() throws IOException, MalformedURLException, PlaceParseException {
        String searchText = "Museum";
        Mockito.when(placesHttpClient.searchPlaces(searchText)).thenReturn("{   \"candidates\" : [],   \"error_message\" : \"You must use an API key to authenticate each request to Google Maps Platform APIs. For additional information, please refer to http://g.co/dev/maps-no-account\",   \"status\" : \"REQUEST_DENIED\"}");
        List<Result> list = locationService.searchPlaces(searchText);
    }
    
    
}
