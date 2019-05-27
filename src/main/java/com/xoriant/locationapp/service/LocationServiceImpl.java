/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xoriant.locationapp.service;

import com.xoriant.locationapp.httpclients.PlacesHttpClient;
import com.xoriant.locationapp.model.Place;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.LinkedList;
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
    public List<Place> searchPlaces(String text) throws MalformedURLException, IOException {

        List<Place> list = new LinkedList<Place>();   //Use a linked list here, since the only operation is insert. Avoiding the overhead of growing the array.
        String places = placesHttpClient.searchPlaces(text);
        
        //TODO parse response
        
        
        return list;
    }

}
