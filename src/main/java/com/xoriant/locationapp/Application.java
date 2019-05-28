/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xoriant.locationapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
     
/**
 *
 * @author nalwar_s
 */
@SpringBootApplication
@EntityScan("com.xoriant.locationapp")
public class Application {
    public static void main(String args[]){
        SpringApplication.run(Application.class, args);
    }
    
}
