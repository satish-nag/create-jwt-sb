package com.example.createjwtsb.controller;

import com.example.createjwtsb.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class TokenController {

    @Autowired
    TokenService tokenService;

    @GetMapping("/getToken/{userId}/{roles}")
    public String getToken(@PathVariable Integer userId,@PathVariable String roles){
        return tokenService.checkAndGetToken(userId,roles);
    }
}
