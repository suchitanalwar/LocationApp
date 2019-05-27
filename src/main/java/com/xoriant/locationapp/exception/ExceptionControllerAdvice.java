/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xoriant.locationapp.exception;

import com.xoriant.locationapp.model.ErrorInfo;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author akshay.velhal
 */
@ControllerAdvice
public class ExceptionControllerAdvice {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    ErrorInfo handelAllError(HttpServletRequest req, Exception ex) {
        if (ex != null && ex.getMessage() != null) {
            
            System.err.println("Exception Handler: Exception " + ex.getMessage());
            System.err.println("Request URL: " + req.getRequestURI());
            
            ex.printStackTrace(System.err);
        }
        System.err.println("Generic Exception Handler:  " + ex.getMessage());
        System.err.println("Request URL: " + req.getRequestURI());
        return new ErrorInfo(req.getRequestURL(), ex);
    }

}
