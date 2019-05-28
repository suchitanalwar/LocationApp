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
public class PlaceResponse {

    @JsonIgnoreProperties
    private List<Result> results;
    
    @JsonIgnoreProperties
    private Result result;

    private String status;

    @JsonIgnoreProperties
    @JsonProperty("error_message")
    private String errorMessage;

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> candidates) {
        this.results = candidates;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

}
