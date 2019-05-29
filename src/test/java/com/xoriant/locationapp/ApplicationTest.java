/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xoriant.locationapp;

import com.xoriant.locationapp.exception.ExceptionControllerAdvice;
import com.xoriant.locationapp.service.LocationService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
     
/**
 *
 * @author nalwar_s
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ApplicationTest {
    
    @Autowired
    LocationService locationService;
    
    @Autowired
    ExceptionControllerAdvice exceptionControllerAdvice;
    
    @Test
    public void doNothingTest() {
        Assert.assertNotNull("Location Service should not be null", locationService);
        Assert.assertNotNull("Exception Controller Advice should not be null", exceptionControllerAdvice);
    }
}
