package com.gabriel.practice.Authorization;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    String name;
    String password;
}

/* El login va a pedir las credenciales del usuario
 * es decir el user y la password
 */