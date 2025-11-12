package com.gabriel.practice.Authorization;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    /* String token; */ // En desuso luego de agregar refresh token
    private String accessToken;
    private String refreshToken;
}

/* Es la respuesta, independientemente a si es un Login
 * o es un Register nos interesa que nos devuelva el token
 */