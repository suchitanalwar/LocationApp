/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xoriant.locationapp.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
 * @author nalwar_s
 */
@RestController
@RequestMapping("/api/places")
public class LocationController {
    
    private static final Logger logger = LogManager.getLogger(LocationController.class);

    @Autowired
    LocationService locationService;

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public List<Result> searchPlaces(HttpServletResponse response, @RequestParam String searchText)
            throws MalformedURLException, IOException, PlaceParseException {
        logger.info("Search text passed is" + searchText);
        return locationService.searchPlaces(searchText);
    }

    @RequestMapping(value = "/type", method = RequestMethod.GET)
    public List<Result> searchPlaceByType(HttpServletResponse response, @RequestParam String searchText, @RequestParam String category)
            throws MalformedURLException, IOException, PlaceParseException {
        return locationService.searchPlaceByType(searchText, category);
    }

    @RequestMapping(value = "/details", method = RequestMethod.GET)
    public Result getPlaceDetails(HttpServletResponse response, @RequestParam String placeId)
            throws MalformedURLException, IOException, PlaceParseException {
        return locationService.getPlaceDetails(placeId);
    }

    @RequestMapping(value = "/fav", method = RequestMethod.GET)
    public List<Result> getFavPlaces(HttpServletResponse response)
            throws MalformedURLException, IOException, PlaceParseException {
        return locationService.getFavPlaces();
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @RequestMapping(value = "/fav", method = RequestMethod.POST)
    public void markPlaceAsFav(HttpServletResponse response, @RequestParam String placeId)
            throws IOException {
        locationService.markAsFav(placeId);
    }

}
