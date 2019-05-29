/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xoriant.locationapp.dao;

import java.io.File;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.List;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.assertj.core.api.Java6Assertions.linesOf;
import org.junit.After;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

/**
 *
 * @author nalwar_s
 */
public class LocationDaoTest {

    private static LocationDao locationDao;
    private static final String favFilePath = "./fav_places.txt";

    @BeforeClass
    public static void setUp() {
        locationDao = new LocationDaoImpl();
        ReflectionTestUtils.setField(locationDao, "favPlacesFile", favFilePath);
    }
    
    @After
    @Before
    public void cleanUp() {
        File file = new File(favFilePath);
        file.delete();
    }

    @Test
    public void markAsFavTest() throws IOException {
        String placeId = "SOME ID";
        locationDao.markAsFav(placeId);
        File file = new File(favFilePath);

        assertTrue(file.exists());
        assertThat(linesOf(file).equals(placeId + "\n"));
    }
    
    @Test
    public void favPlacesTest() throws IOException {
        String placeId = "SOME ID";
        locationDao.markAsFav(placeId);
        
        List<String> favs = locationDao.favPlaces();

        assertTrue(favs != null);
        assertTrue(!favs.isEmpty());
        assertTrue(favs.size() == 1); 
    }
    
    @Test(expected = NoSuchFileException.class)
    public void favPlacesNoSuchFileExceptionTest() throws IOException {
        List<String> favs = locationDao.favPlaces();
    }
    
    
}
