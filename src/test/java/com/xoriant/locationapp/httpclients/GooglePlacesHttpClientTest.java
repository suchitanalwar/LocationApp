/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xoriant.locationapp.httpclients;

import java.io.IOException;
import java.net.MalformedURLException;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.util.ReflectionTestUtils;

/**
 *
 * @author nalwar_s
 */
@RunWith(PowerMockRunner.class)
@PowerMockIgnore("javax.management.*")
@PropertySource("classpath:application.properties")
public class GooglePlacesHttpClientTest {

    private static GooglePlacesHttpClient googlePlacesHttpClient;

    @BeforeClass
    public static void setUp() {
        googlePlacesHttpClient = new GooglePlacesHttpClient();
        ReflectionTestUtils.setField(googlePlacesHttpClient, "apiKey", "AIzaSyCvberVP2XFiqKtXZhLb3sCLoXjgc46mUc");
        ReflectionTestUtils.setField(googlePlacesHttpClient, "searchPlacesUrl", "https://maps.googleapis.com/maps/api/place/textsearch/json");
        ReflectionTestUtils.setField(googlePlacesHttpClient, "detailsPlacesUrl", "https://maps.googleapis.com/maps/api/place/details/json");
    }

    //Expecting that no exception should be thrown
    //We also expect that the unit test to connect to google's place api and get a response.
    //Since the response is something that can keep changing, we cannot verify what the response might be
    @Test()
    public void searchPlacesTest() throws MalformedURLException, IOException {
        googlePlacesHttpClient.searchPlaces("restaurants in india");
    }

    @Test()
    public void placeDetailsTest() throws MalformedURLException, IOException {
        googlePlacesHttpClient.getPlaceDetails("ChIJrTLr-GyuEmsRBfy61i59si0");
    }

}
