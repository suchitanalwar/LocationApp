/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xoriant.locationapp.service;

import com.xoriant.locationapp.dao.LocationDao;
import com.xoriant.locationapp.exception.PlaceParseException;
import com.xoriant.locationapp.httpclients.PlacesHttpClient;
import com.xoriant.locationapp.model.Result;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 *
 * @author nalwar_s
 */
@RunWith(PowerMockRunner.class)
@PowerMockIgnore("javax.management.*")
public class LocationServiceTest {

    @InjectMocks
    LocationService locationService = new LocationServiceImpl();

    @Mock
    PlacesHttpClient placesHttpClient;

    @Mock
    LocationDao locationDao;

    @Test()
    public void testSearchPlacesOneSearchResult() throws IOException, MalformedURLException, PlaceParseException {
        String searchText = "Museum";
        Mockito.when(placesHttpClient.searchPlaces(searchText)).thenReturn("{\n"
                + "    \"html_attributions\": [],\n"
                + "    \"results\": [\n"
                + "        {\n"
                + "            \"formatted_address\": \"The Lodhi, Lodhi Rd, CGO Complex, Pragati Vihar, New Delhi, Delhi 110003, India\",\n"
                + "            \"icon\": \"https://maps.gstatic.com/mapfiles/place_api/icons/restaurant-71.png\",\n"
                + "            \"id\": \"cf0a47eabb4007aa3140849ea2cc82d566560eb1\",\n"
                + "            \"name\": \"Indian Accent\",\n"
                + "            \"opening_hours\": {\n"
                + "                \"open_now\": true\n"
                + "            },\n"
                + "            \"place_id\": \"ChIJd-3rnjD9DDkRJixh51I7E_0\",\n"
                + "            \"plus_code\": {\n"
                + "                \"compound_code\": \"H6RQ+P7 New Delhi, Delhi, India\",\n"
                + "                \"global_code\": \"7JWVH6RQ+P7\"\n"
                + "            },\n"
                + "            \"price_level\": 4,\n"
                + "            \"rating\": 4.6,\n"
                + "            \"reference\": \"ChIJd-3rnjD9DDkRJixh51I7E_0\",\n"
                + "            \"types\": [\n"
                + "                \"bar\",\n"
                + "                \"restaurant\",\n"
                + "                \"food\",\n"
                + "                \"point_of_interest\",\n"
                + "                \"establishment\"\n"
                + "            ],\n"
                + "            \"user_ratings_total\": 1357\n"
                + "        }\n"
                + "    ],\n"
                + "    \"status\": \"OK\"\n"
                + "}");

        List<Result> list = locationService.searchPlaces(searchText);
        Assert.assertNotNull("Candidates should not be null", list);
        Assert.assertEquals("There should be one candidate returned", 1, list.size());
    }

    @Test(expected = PlaceParseException.class)
    public void testSearchPlacesException() throws IOException, MalformedURLException, PlaceParseException {
        String searchText = "Museum";
        Mockito.when(placesHttpClient.searchPlaces(searchText)).thenReturn("{   \"candidates\" : [],   \"error_message\" : \"You must use an API key to authenticate each request to Google Maps Platform APIs. For additional information, please refer to http://g.co/dev/maps-no-account\",   \"status\" : \"REQUEST_DENIED\"}");
        List<Result> list = locationService.searchPlaces(searchText);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSearchPlacesInCorrectArgs() throws IOException, MalformedURLException, PlaceParseException {
        String searchText = "";
        List<Result> list = locationService.searchPlaces(searchText);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSearchPlacesInCorrectArgsNull() throws IOException, MalformedURLException, PlaceParseException {
        String searchText = null;
        List<Result> list = locationService.searchPlaces(searchText);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMarkAsFavNull() throws IOException, MalformedURLException, PlaceParseException {
        String searchText = null;
        locationService.markAsFav(searchText);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMarkAsFavEmpty() throws IOException, MalformedURLException, PlaceParseException {
        String searchText = "";
        locationService.markAsFav(searchText);
    }

    @Test()
    public void testMarkAsFav() throws IOException, MalformedURLException, PlaceParseException {
        String searchText = "abcde";
        locationService.markAsFav(searchText);

        Mockito.verify(locationDao, Mockito.times(1)).markAsFav(searchText);
    }

    @Test()
    public void testGetFavPlaces() throws IOException, PlaceParseException, MalformedURLException {
        String placeId = "ChIJrTLr-GyuEmsRBfy61i59si0";
        Mockito.when(locationDao.favPlaces()).thenReturn(Arrays.asList(placeId));
        Mockito.when(placesHttpClient.getPlaceDetails(placeId)).thenReturn("{\n"
                + "   \"html_attributions\" : [],\n"
                + "   \"result\" : {\n"
                + "      \"formatted_address\" : \"5, 48 Pirrama Rd, Pyrmont NSW 2009, Australia\",\n"
                + "      \"formatted_phone_number\" : \"(02) 9374 4000\",\n"
                + "      \n"
                + "      \"icon\" : \"https://maps.gstatic.com/mapfiles/place_api/icons/generic_business-71.png\",\n"
                + "      \"id\" : \"4f89212bf76dde31f092cfc14d7506555d85b5c7\",\n"
                + "      \"international_phone_number\" : \"+61 2 9374 4000\",\n"
                + "      \"name\" : \"Google\",\n"
                + "      \"place_id\" : \"ChIJN1t_tDeuEmsRUsoyG83frY4\",\n"
                + "      \"rating\" : 4.5,\n"
                + "      \n"
                + "      \"scope\" : \"GOOGLE\",\n"
                + "      \"types\" : [ \"point_of_interest\", \"establishment\" ],\n"
                + "      \"url\" : \"https://maps.google.com/?cid=10281119596374313554\",\n"
                + "      \"utc_offset\" : 600,\n"
                + "      \"vicinity\" : \"5, 48 Pirrama Road, Pyrmont\",\n"
                + "      \"website\" : \"https://www.google.com.au/about/careers/locations/sydney/\"\n"
                + "   },\n"
                + "   \"status\" : \"OK\"\n"
                + "}\n"
                + "      ");

        List<Result> list = locationService.getFavPlaces();
        Assert.assertNotNull("Result should not be null", list);
        Assert.assertEquals("There should be one result returned", 1, list.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPlaceDetailNull() throws IOException, MalformedURLException, PlaceParseException {
        String placeId = null;
        locationService.getPlaceDetails(placeId);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPlaceDetailEmpty() throws IOException, MalformedURLException, PlaceParseException {
        String placeId = "";
        locationService.getPlaceDetails(placeId);
    }

    @Test()
    public void testPlaceDetail() throws IOException, MalformedURLException, PlaceParseException {
        String placeId = "ChIJrTLr-GyuEmsRBfy61i59si0";

        Mockito.when(placesHttpClient.getPlaceDetails(placeId)).thenReturn("{\n"
                + "   \"html_attributions\" : [],\n"
                + "   \"result\" : {\n"
                + "      \"formatted_address\" : \"5, 48 Pirrama Rd, Pyrmont NSW 2009, Australia\",\n"
                + "      \"formatted_phone_number\" : \"(02) 9374 4000\",\n"
                + "      \n"
                + "      \"icon\" : \"https://maps.gstatic.com/mapfiles/place_api/icons/generic_business-71.png\",\n"
                + "      \"id\" : \"4f89212bf76dde31f092cfc14d7506555d85b5c7\",\n"
                + "      \"international_phone_number\" : \"+61 2 9374 4000\",\n"
                + "      \"name\" : \"Google\",\n"
                + "      \"place_id\" : \"ChIJN1t_tDeuEmsRUsoyG83frY4\",\n"
                + "      \"rating\" : 4.5,\n"
                + "      \n"
                + "      \"scope\" : \"GOOGLE\",\n"
                + "      \"types\" : [ \"point_of_interest\", \"establishment\" ],\n"
                + "      \"url\" : \"https://maps.google.com/?cid=10281119596374313554\",\n"
                + "      \"utc_offset\" : 600,\n"
                + "      \"vicinity\" : \"5, 48 Pirrama Road, Pyrmont\",\n"
                + "      \"website\" : \"https://www.google.com.au/about/careers/locations/sydney/\"\n"
                + "   },\n"
                + "   \"status\" : \"OK\"\n"
                + "}\n"
                + "      ");

        Result result = locationService.getPlaceDetails(placeId);
        Assert.assertNotNull("Result should not be null", result);
    }

    @Test(expected = PlaceParseException.class)
    public void testPlaceDetailPlacesException() throws IOException, MalformedURLException, PlaceParseException {
        String placeId = "ChIJrTLr-GyuEmsRBfy61i59si0";
        Mockito.when(placesHttpClient.getPlaceDetails(placeId)).thenReturn("{   \"error_message\" : \"The provided API key is invalid.\",   \"html_attributions\" : [],   \"status\" : \"REQUEST_DENIED\"}");
        Result result = locationService.getPlaceDetails(placeId);
    }

    @Test()
    public void testSearchPlaceByTypeOneSearchResult() throws IOException, MalformedURLException, PlaceParseException {
        String searchText = "Museum";
        Mockito.when(placesHttpClient.searchPlaces(searchText)).thenReturn("{\n"
                + "    \"html_attributions\": [],\n"
                + "    \"results\": [\n"
                + "        {\n"
                + "            \"formatted_address\": \"The Lodhi, Lodhi Rd, CGO Complex, Pragati Vihar, New Delhi, Delhi 110003, India\",\n"
                + "            \"icon\": \"https://maps.gstatic.com/mapfiles/place_api/icons/restaurant-71.png\",\n"
                + "            \"id\": \"cf0a47eabb4007aa3140849ea2cc82d566560eb1\",\n"
                + "            \"name\": \"Indian Accent\",\n"
                + "            \"opening_hours\": {\n"
                + "                \"open_now\": true\n"
                + "            },\n"
                + "            \"place_id\": \"ChIJd-3rnjD9DDkRJixh51I7E_0\",\n"
                + "            \"plus_code\": {\n"
                + "                \"compound_code\": \"H6RQ+P7 New Delhi, Delhi, India\",\n"
                + "                \"global_code\": \"7JWVH6RQ+P7\"\n"
                + "            },\n"
                + "            \"price_level\": 4,\n"
                + "            \"rating\": 4.6,\n"
                + "            \"reference\": \"ChIJd-3rnjD9DDkRJixh51I7E_0\",\n"
                + "            \"types\": [\n"
                + "                \"bar\",\n"
                + "                \"restaurant\",\n"
                + "                \"food\",\n"
                + "                \"point_of_interest\",\n"
                + "                \"establishment\"\n"
                + "            ],\n"
                + "            \"user_ratings_total\": 1357\n"
                + "        }\n"
                + "    ],\n"
                + "    \"status\": \"OK\"\n"
                + "}");
        List<Result> list = locationService.searchPlaceByType(searchText, "bar");
        Assert.assertNotNull("Result should not be null", list);
        Assert.assertEquals("There should be one result returned", 1, list.size());
    }

}
