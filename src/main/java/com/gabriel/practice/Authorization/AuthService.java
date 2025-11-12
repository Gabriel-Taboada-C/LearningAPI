package com.gabriel.practice.Authorization;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
/* import org.springframework.security.core.userdetails.UserDetails; en desuso por version 0.12.3*/
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gabriel.practice.Jwt.JwtService;
import com.gabriel.practice.User.UserEntity;
import com.gabriel.practice.User.UserRepository;
import com.gabriel.practice.User.UserEntity.Rol;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;


    /* Al cambiar la version de io.jsonwebtoken de 0.11.5 a 0.12.33
     * se cambia UserDetails por UserEntity (tambien en JwtService)
     * esto nos permite obtener el valor de id para los claims
     * con UserDetails este parametro no puede obtenerse
     */
// LOGIN DE USUARIO
    public AuthResponse login (LoginRequest request) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getName(), request.getPassword()));
        UserEntity user = userRepository.findByName(request.getName()).orElseThrow();
        /* String token = jwtService.getToken(user); */ // En desuso luego de agregar refresh token

        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        /* return AuthResponse.builder()
            .token(token)
            .build(); */
        return new AuthResponse(accessToken,refreshToken);
    }

// REGISTRO DE USUARIO
/* Antes: public AuthResponse register (RegisterRequest request) */
    public AuthResponse register (RegisterRequest request) {
        UserEntity user = UserEntity.builder()
            .name(request.getName())
            .password(passwordEncoder.encode(request.getPassword()))
            .rol(request.getRol() !=null ? request.getRol() : Rol.ADMIN) //Rol elegido o ADMIN por defecto
            .build();
        userRepository.save(user);
        //IMPORTANTE: usar "user" (el guardado), no "request"

        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        return new AuthResponse(accessToken, refreshToken);
        
        /*
        Cambia al agregar el refresh token 
        return AuthResponse.builder()
        // Necesitamos que se genere automaticamente, por ello agregamos un JwtService
        .token(jwtService.getToken(user)) 
        .build(); */
    }
// No olvidar implementar UserDetails en nuestra User Entity

// REFRESH TOKEN (renueva los tokens)
    public AuthResponse refreshToken (String refreshToken) {
    // Validamos si es un refreshToken
    if (!jwtService.isRefreshToken(refreshToken)) {
        throw new RuntimeException("El token proporcionado no es un refresh token válido.");
    }

    String username = jwtService.getUsernameFromToken (refreshToken);

    UserEntity user = userRepository.findByName(username)
                .orElseThrow(()->new RuntimeException("Usuario no encontrado."));

    if (!jwtService.isTokenValid(refreshToken, user)) {
        throw new RuntimeException("El refresh token ha expirado o es inválido");
    }

    String newAccessToken = jwtService.generateAccessToken(user);

    return new AuthResponse(newAccessToken,refreshToken);
    }
}
