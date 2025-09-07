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
    public AuthResponse login (LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getName(), request.getPassword()));
        UserEntity user = userRepository.findByName(request.getName()).orElseThrow();
        String token = jwtService.getToken(user);
        return AuthResponse.builder()
            .token(token)
            .build();
    }


    public AuthResponse register (RegisterRequest request) {
        UserEntity user = UserEntity.builder()
            .name(request.getName())
            .password(passwordEncoder.encode(request.getPassword()))
            .rol(Rol.ADMIN)
            .build();
        userRepository.save(user);

        return AuthResponse.builder()
            .token(jwtService.getToken(user)) // Necesitamos que se genere automaticamente, por ello agregamos un JwtService
            .build();
    }
// No olvidar implementar UserDetails en nuestra User Entity
}
