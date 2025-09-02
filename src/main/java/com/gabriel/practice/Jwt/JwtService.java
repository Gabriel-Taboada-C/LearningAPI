package com.gabriel.practice.Jwt;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    /* Debo cambiar el objeto UserEntity por UserDetails 
    public String getToken(UserEntity user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getToken'");
    } */

    public String getToken(UserDetails user) {
        return null;
    }


}
