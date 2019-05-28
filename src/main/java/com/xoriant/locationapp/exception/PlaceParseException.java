/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xoriant.locationapp.exception;

/**
 *
 * @author nalwar_s
 */
public class PlaceParseException extends Exception {
    
    	private static final long serialVersionUID = 1L;
	
	public PlaceParseException(String message){
        super(message);         
    } 
	
	public PlaceParseException(String message, Throwable cause){
		super(message, cause);
	}
    
}
