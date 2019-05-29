/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xoriant.locationapp.service;

import com.xoriant.locationapp.exception.PlaceParseException;
import com.xoriant.locationapp.model.Result;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

/**
 *
 * @author nalwar_s
 */
public interface LocationService {

    public List<Result> searchPlaces(String text) throws MalformedURLException, IOException, PlaceParseException;
    public void markAsFav(String placeId) throws IOException;
    public List<Result> getFavPlaces() throws IOException,PlaceParseException, MalformedURLException;
    public List<Result> searchPlaceByType(String searchText, String category) throws MalformedURLException, IOException, PlaceParseException;
    public Result getPlaceDetails(String placeId) throws MalformedURLException, IOException, PlaceParseException;

}
