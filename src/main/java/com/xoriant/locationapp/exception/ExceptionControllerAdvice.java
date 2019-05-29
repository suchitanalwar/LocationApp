/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xoriant.locationapp.exception;

import com.xoriant.locationapp.model.ErrorInfo;
import java.nio.file.NoSuchFileException;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author nalwar_s
 */
@ControllerAdvice
public class ExceptionControllerAdvice {
    
    private static final Logger LOGGER = LogManager.getLogger(ExceptionControllerAdvice.class);

    @ResponseBody
    @ExceptionHandler(PlaceParseException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    ErrorInfo handelPlaceParseError(HttpServletRequest req, PlaceParseException ex) {
        LOGGER.error("Place Parse Exception Handler:  " + ex.getMessage());
        LOGGER.error("Request URL: " + req.getRequestURI());
        
        ex.printStackTrace();
        
        return new ErrorInfo(req.getRequestURL(), ex);
    }
    
    @ResponseBody
    @ExceptionHandler(NoSuchFileException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ErrorInfo handelNoSuchFileError(HttpServletRequest req, NoSuchFileException ex) {
        LOGGER.error("No Such File Exception Handler:  " + ex.getMessage());
        LOGGER.error("Request URL: " + req.getRequestURI());
        
        ex.printStackTrace();
        
        return new ErrorInfo(req.getRequestURL(), ex);
    }
    
    @ResponseBody
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    ErrorInfo handelGenericError(HttpServletRequest req, Exception ex) {
        LOGGER.error("Generic Exception Handler:  " + ex.getMessage());
        LOGGER.error("Request URL: " + req.getRequestURI());
        
        ex.printStackTrace();
        
        return new ErrorInfo(req.getRequestURL(), ex);
    }

}
