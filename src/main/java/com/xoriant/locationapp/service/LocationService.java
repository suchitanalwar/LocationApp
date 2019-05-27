/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xoriant.locationapp.service;

import com.xoriant.locationapp.exception.PlaceParseException;
import com.xoriant.locationapp.model.Candidate;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

/**
 *
 * @author akshay.velhal
 */
public interface LocationService {

    public List<Candidate> searchPlaces(String text) throws MalformedURLException, IOException, PlaceParseException;

}
