/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xoriant.locationapp.httpclients;

import java.io.IOException;
import java.net.MalformedURLException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.util.ReflectionTestUtils;

/**
 *
 * @author akshay.velhal
 */
@RunWith(PowerMockRunner.class)
@PropertySource("classpath:application.properties")
public class GooglePlacesHttpClientTest {
    
    GooglePlacesHttpClient googlePlacesHttpClient;
    
    @Before
    public void setUp() {
        googlePlacesHttpClient = new GooglePlacesHttpClient(); 
        ReflectionTestUtils.setField(googlePlacesHttpClient, "apiKey", "AIzaSyCvberVP2XFiqKtXZhLb3sCLoXjgc46mUc");
        ReflectionTestUtils.setField(googlePlacesHttpClient, "searchPlacesUrl", "https://maps.googleapis.com/maps/api/place/textsearch/json");
    }
    
    //Expecting that no exception should be thrown
    //We also expect that the unit test to connect to google's place api and get a response.
    //Since the response is something that can keep changing, we cannot verify what the response might be
    @Test()
    public void searchPlacesTest() throws MalformedURLException, IOException {
        googlePlacesHttpClient.searchPlaces("restaurants in india");
    }
}
