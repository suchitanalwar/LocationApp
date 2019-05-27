/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xoriant.locationapp.service;

import com.xoriant.locationapp.exception.PlaceParseException;
import com.xoriant.locationapp.httpclients.PlacesHttpClient;
import com.xoriant.locationapp.model.Candidate;
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
 * @author akshay.velhal
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
"   \"candidates\" : [\n" +
"      {\n" +
"         \"formatted_address\" : \"140 George St, The Rocks NSW 2000, Australia\",\n" +
"         \n" +
"         \"name\" : \"Museum of Contemporary Art Australia\",\n" +
"         \"opening_hours\" : {\n" +
"            \"open_now\" : false,\n" +
"            \"weekday_text\" : []\n" +
"         },\n" +
"         \n" +
"         \"rating\" : 4.3\n" +
"      }\n" +
"   ],\n" +
"   \"debug_log\" : {\n" +
"      \"line\" : []\n" +
"   },\n" +
"   \"status\" : \"OK\"\n" +
"}");
        
        List<Candidate> list = locationService.searchPlaces(searchText);
        Assert.assertNotNull("Candidates should not be null", list);
        Assert.assertEquals("There should be one candidate returned", 1, list.size());
    }
    
    @Test(expected = PlaceParseException.class)
    public void testSearchPlacesException() throws IOException, MalformedURLException, PlaceParseException {
        String searchText = "Museum";
        Mockito.when(placesHttpClient.searchPlaces(searchText)).thenReturn("{   \"candidates\" : [],   \"error_message\" : \"You must use an API key to authenticate each request to Google Maps Platform APIs. For additional information, please refer to http://g.co/dev/maps-no-account\",   \"status\" : \"REQUEST_DENIED\"}");
        List<Candidate> list = locationService.searchPlaces(searchText);
    }
    
    
}
