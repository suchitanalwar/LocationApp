/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xoriant.locationapp.dao;

import java.io.IOException;
import java.util.List;

/**
 *
 * @author nalwar_s
 */
public interface LocationDao {
    
    public void markAsFav(String placeId) throws IOException;
    public List<String> favPlaces() throws IOException;
    
}
