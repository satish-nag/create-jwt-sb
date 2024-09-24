package com.example.createjwtsb.service;

import com.example.createjwtsb.model.Users;
import com.example.createjwtsb.repositories.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TokenService {

        public static final String SECRET_KEY="a83892b072a4d80f5ef5e3cd50e47f637a40b63ad6c600c43d8aa6ef799eeef2";
    @Autowired
    UserRepository userRepository;

    public String checkAndGetToken(Integer userId,String roles){
        String verifiedroles = checkRolesForUser(userId, roles);
        return generateToken(userId,verifiedroles);

    }

    private String generateToken(Integer userId, String verifiedroles) {
        String name = userRepository.findById(userId).orElse(new Users()).name;
        List<String> collect = Arrays.stream(verifiedroles.split(",")).collect(Collectors.toList());
        return Jwts.builder()
                .setClaims(Map.of("roles",collect))
                .setSubject(name)
                .setAudience("FBA")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+(24*60*60*1000)))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .setHeaderParam("typ","JWT")
                .setIssuer("http://localhost:8281")
                .compact();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Base64.getDecoder().decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private String checkRolesForUser(Integer userId, String roles) {
        String commonRoles = "";
        Optional<Users> userEntityOptional = userRepository.findById(userId);
        String role = userEntityOptional.orElseGet(Users::new).role;
        if(role!=null){
            List<String> userRoles = Arrays.asList(role.split(","));
            List<String> expectedRoles = Arrays.asList(roles.split(","));
            commonRoles = expectedRoles.stream().filter(s -> userRoles.contains(s)).collect(Collectors.joining(","));
        }
        return commonRoles;
    }
}
