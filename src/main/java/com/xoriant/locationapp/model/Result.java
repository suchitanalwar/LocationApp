/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xoriant.locationapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 *
 * @author nalwar_s
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Result {

    @JsonIgnoreProperties
    private String id;

    @JsonIgnoreProperties
    private String icon;

    @JsonIgnoreProperties
    @JsonProperty("place_id")
    private String placeId;

    @JsonProperty("formatted_address")
    private String formattedAddress;

    @JsonIgnoreProperties
    @JsonProperty("formatted_phone_number")
    private String formattedPhoneNumber;

    @JsonIgnoreProperties
    @JsonProperty("international_phone_number")
    private String internationalPhoneNumber;

    @JsonIgnoreProperties
    private String name;

    @JsonIgnoreProperties
    private String rating;

    @JsonIgnoreProperties
    private List<String> types;
    
    @JsonIgnoreProperties
    private String website;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getFormattedAddress() {
        return formattedAddress;
    }

    public void setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }

    public String getFormattedPhoneNumber() {
        return formattedPhoneNumber;
    }

    public void setFormattedPhoneNumber(String formattedPhoneNumber) {
        this.formattedPhoneNumber = formattedPhoneNumber;
    }

    public String getInternationalPhoneNumber() {
        return internationalPhoneNumber;
    }

    public void setInternationalPhoneNumber(String internationalPhoneNumber) {
        this.internationalPhoneNumber = internationalPhoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }
    
    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
