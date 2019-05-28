/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xoriant.locationapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import static jdk.nashorn.internal.objects.NativeJava.type;

/**
 *
 * @author akshay.velhal
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Result {

    @JsonIgnoreProperties
    private String id;
    
    @JsonIgnoreProperties
    private String icon;
    
    @JsonIgnoreProperties
    private String placeId;
    @JsonProperty("formatted_address")
    private String formattedAddress;
    
    @JsonIgnoreProperties
    private String name;
    
    @JsonIgnoreProperties
    private String rating;
    
    @JsonIgnoreProperties
    private List<String> types;
    

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

}
