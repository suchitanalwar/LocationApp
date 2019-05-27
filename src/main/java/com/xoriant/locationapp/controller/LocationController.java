/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xoriant.locationapp.controller;

import com.xoriant.locationapp.exception.PlaceParseException;
import com.xoriant.locationapp.model.Candidate;
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
    public List<Candidate> searchPlaces(HttpServletResponse response, @RequestParam String searchText)
            throws MalformedURLException, IOException, PlaceParseException {

        return locationService.searchPlaces(searchText);

    }

}
