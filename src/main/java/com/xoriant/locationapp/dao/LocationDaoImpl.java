/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xoriant.locationapp.dao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

/**
 *
 * @author nalwar_s
 */
@Repository
public class LocationDaoImpl implements LocationDao {

    @Value("${FAV_PLACES_FILE}")
    private String favPlacesFile;
    
    @Override
    public void markAsFav(String placeId) throws IOException {
        placeId = placeId +"\n";
        Files.write(Paths.get(favPlacesFile), placeId.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }

    @Override
    public List<String> favPlaces() throws IOException {
        try {
        return Files.lines(Paths.get(favPlacesFile)).collect(Collectors.toList());
        } catch(NoSuchFileException exception) {
            throw new NoSuchFileException("You have no favourite files");
        }
    }
    
    
    
    
}
