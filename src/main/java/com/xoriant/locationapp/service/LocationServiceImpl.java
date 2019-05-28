/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xoriant.locationapp.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xoriant.locationapp.dao.LocationDao;
import com.xoriant.locationapp.exception.PlaceParseException;
import com.xoriant.locationapp.httpclients.PlacesHttpClient;
import com.xoriant.locationapp.model.Result;
import com.xoriant.locationapp.model.PlaceResponse;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author akshay.velhal
 */
@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    PlacesHttpClient placesHttpClient;
    
    @Autowired
    LocationDao locationDao;

    @Override
    public List<Result> searchPlaces(String text) throws MalformedURLException, IOException, PlaceParseException {
        
        if(text == null || text.isEmpty()) {
            throw new IllegalArgumentException("Search text cannot be empty");
        }
        
        String response = placesHttpClient.searchPlaces(text);
        
        ObjectMapper objectMapper = new ObjectMapper();
        PlaceResponse placeResponse = objectMapper.readValue(response, PlaceResponse.class);
        
        if(placeResponse.getErrorMessage() != null && !placeResponse.getErrorMessage().isEmpty()) {
            throw new PlaceParseException(placeResponse.getErrorMessage());
        }
        
        return placeResponse.getResults();
    }

    @Override
    public void markAsFav(String placeId) throws IOException {
        
        if(placeId == null || placeId.isEmpty()) {
            throw new IllegalArgumentException("Incorrect Place Id specified");
        }

        locationDao.markAsFav(placeId);
    }

    @Override
    public List<Result> getFavPlaces() throws IOException {
        
        List<String> favPlaces = locationDao.favPlaces();
        
        
        return null;
    }
}
