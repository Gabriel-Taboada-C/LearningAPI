package com.gabriel.practice.Jwt;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    private static final String SECRET_KEY = "N3W53C3TK3YF0RPR4CT1C300439454868";

    /* Debo cambiar el objeto UserEntity por UserDetails 
    public String getToken(UserEntity user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getToken'");
    } */

    public String getToken(UserDetails user) {
        // HashMap es una clase de colecciones que se utiliza para almacenar pares de clave valor
        // Lo vamos a utilizar en los claims de la aplicacion para pasar info adicional en el token
        return getToken(new HashMap<>(),user);
    }

    public String getToken (Map<String,Object> extraClaims, UserDetails user) {
        //libreria Json Web Token
        return Jwts
            .builder()
            .setClaims(extraClaims)
            .setSubject(user.getUsername())
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis()+1000*60*24))
            .signWith(getKey(), SignatureAlgorithm.HS256)
            .compact();
    }

    private Key getKey() {
        byte[] keyBytes=Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }


}
