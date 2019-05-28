/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xoriant.locationapp.controller;

import com.xoriant.locationapp.exception.PlaceParseException;
import com.xoriant.locationapp.model.Result;
import com.xoriant.locationapp.service.LocationService;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author HP
 */
@RestController
@RequestMapping("/api/places")
public class LocationController {

    @Autowired
    LocationService locationService;

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public List<Result> searchPlaces(HttpServletResponse response, @RequestParam String searchText)
            throws MalformedURLException, IOException, PlaceParseException {
        return locationService.searchPlaces(searchText);
    }
    
    @ResponseStatus(value = HttpStatus.CREATED)
    @RequestMapping(value = "/fav", method = RequestMethod.POST)
    public void markPlaceAsFav(HttpServletResponse response, @RequestParam String placeId) 
            throws IOException {
        locationService.markAsFav(placeId);
    }
    
    
    @RequestMapping(value = "/fav", method = RequestMethod.GET)
    public List<Result> getFavPlaces(HttpServletResponse response)
            throws MalformedURLException, IOException, PlaceParseException {
        return locationService.getFavPlaces();
    }
    

}
