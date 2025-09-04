package com.gabriel.practice.Jwt;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
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

    /* -------------------------------------------------------------------------------------------------- */
    /* Esta parte se realiza luego de terminar de configurar el JwtAuthenticationFilter 
     * Creamos metodos requeridos para que el filtro de JWT funcione
    */

    //Tercero creamos este metodo requerido por JwtAuthFilter
    public String getUsernameFromToken(String token) {
        return getClaim(token, Claims::getSubject);
    }

    //Sexto y ultimo creamos este metodo requerido por JwtAuthFilter
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username=getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }


    //Primero creamos este metodo para acceder a los claims (mediante la libreria Jwts)
    private Claims getAllClaims(String token) 
    {
        return Jwts
                .parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }


    //Segundo creamos un metodo publico GENERICO (T)
    public <T> T getClaim(String token, Function <Claims,T> claimsResolver)
    {
        final Claims claims= getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Cuarto creamos esta funcion necesaria para el metodo isTokenValid()
    private Date getExpiration(String token)
    {
        return getClaim(token, Claims::getExpiration);
    }

    //Quinto creamos esta funcion necesaria para el metodo isTokenValid()
    private boolean isTokenExpired(String token)
    {
        return getExpiration(token).before(new Date());
    }

}
