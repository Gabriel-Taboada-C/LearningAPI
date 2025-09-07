package com.gabriel.practice.Jwt;

/* import java.security.Key; - con la version 0.12.3 no se usa mas*/
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.gabriel.practice.User.UserEntity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
/* import io.jsonwebtoken.SignatureAlgorithm; - con la version 0.12.3 no se usa mas*/
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    /* Para HS256, la clave debe tener al menos 256 bits = 32 bytes = 32 caracteres.
    Si la clave es más corta, JJWT lanza una excepción. */ 

    /* 
    Lo recomendable es guardar la clave en el archivo de configuración 
    en resources ---> application.properties o application.yml
    Y luego inyectarla con @Value
    ANTES:
    private static final String SECRET_KEY = "CLAVEDE32CARACTERESMINIMOS";
    AHORA: 
    */

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    /* Debo cambiar el objeto UserEntity por UserDetails 
    public String getToken(UserEntity user) {
       ESTO ERA EN LA VERSION 0.11.5 DE io.jsonwebtoken
    } */

    // con version 0.12.3 - UserDetails cambia ----> UserEntity (para obtener la id de los claims)
    /* public String getToken(UserDetails user) */
    public String getToken(UserEntity user) 
    {
        // HashMap es una clase de colecciones que se utiliza para almacenar pares de clave valor
        // Lo vamos a utilizar en los claims de la aplicacion para pasar info adicional en el token
        return getToken(new HashMap<>(),user);
    }

    /* CAMBIO DE VERSION DE 0.11.5 A 0.12.3
    Los metodos .set*** cambian a .***
    public String getToken (Map<String,Object> extraClaims, UserDetails user) {
        //libreria Json Web Token
        return Jwts
            .builder()
            .setClaims(extraClaims)
            .setSubject(user.getUsername())
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*24))
            .signWith(getKey(), SignatureAlgorithm.HS256)
            .compact();
    } */

    public String getToken (Map<String,Object> extraClaims, UserEntity user) {
        //Nuevos metodos con version 0.12.3 - UserDetails cambia ----> UserEntity (para obtener la id de los claims)
        return Jwts
            .builder()
            .claims(extraClaims)
            //CREAMOS LOS CLAIMS UNO A UNO (HAY 2 FORMAS DE HACERLO, ESTA ES 1)
            .claim("userId", user.getId())
            .claim("name", user.getName())
            /* EJEMPLOS QUE NO APLICAN EN NUESTRO USER 
            .claim("firstName", user.getFirstName())
            .claim("lastName", user.getLastName())
            .claim("email", user.getEmail()) */
            .subject(user.getUsername())
            .issuedAt(new Date(System.currentTimeMillis()))
            .expiration(new Date(System.currentTimeMillis()+1000*60*60*24))
            .signWith(getKey())
            .compact();
    }

    /* Con la version 0.12.3 clase cambia de nombre
     * Antes: Key
     * Ahora: SecretKey (javax.crypto.SecretKey)
     */
    private SecretKey getKey() {
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
                /* .parserBuilder() - version 0.12.3 cambia*/
                .parser()
                /* .setSigningKey(getKey()) - version 0.12.3 cambia*/
                .verifyWith(getKey())
                .build()
                /* .parseClaimsJws(token) - version 0.12.3 cambia*/
                .parseSignedClaims(token)
                /* .getBody(); - version 0.12.3 cambia*/
                .getPayload();
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
