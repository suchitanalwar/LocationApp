/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xoriant.locationapp.model;

/**
 *
 * @author nalwar_s
 */
public class ErrorInfo {

    public final StringBuffer url;
    public final String ex;

    public ErrorInfo(StringBuffer url, Exception ex) {
        this.url = url;
        this.ex = ex.getLocalizedMessage();
    }

}
