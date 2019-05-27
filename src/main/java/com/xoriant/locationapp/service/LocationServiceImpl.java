/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xoriant.locationapp.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xoriant.locationapp.exception.PlaceParseException;
import com.xoriant.locationapp.httpclients.PlacesHttpClient;
import com.xoriant.locationapp.model.Candidate;
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

    @Override
    public List<Candidate> searchPlaces(String text) throws MalformedURLException, IOException, PlaceParseException {

        String response = placesHttpClient.searchPlaces(text);
        
        ObjectMapper objectMapper = new ObjectMapper();
        PlaceResponse placeResponse = objectMapper.readValue(response, PlaceResponse.class);
        
        if(placeResponse.getErrorMessage() != null && !placeResponse.getErrorMessage().isEmpty()) {
            throw new PlaceParseException(placeResponse.getErrorMessage());
        }
        
        return placeResponse.getCandidates();
    }
    

}
