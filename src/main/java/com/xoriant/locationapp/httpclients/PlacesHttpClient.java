/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xoriant.locationapp.httpclients;

import java.io.IOException;
import java.net.MalformedURLException;

/**
 *
 * @author nalwar_s
 */
public interface PlacesHttpClient {

    public String searchPlaces(String text) throws MalformedURLException, IOException;
    public String getPlaceDetails(String placeId) throws MalformedURLException, IOException;
    
}
