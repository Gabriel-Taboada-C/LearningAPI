package com.gabriel.practice.Authorization;

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

    public AuthResponse login (LoginRequest request) {
        return null;
    }

    public AuthResponse register (RegisterRequest request) {
        UserEntity user = UserEntity.builder()
            .name(request.getName())
            .password(request.getPassword())
            .rol(Rol.ADMIN)
            .build();
        userRepository.save(user);

        return AuthResponse.builder()
            .token(jwtService.getToken(user)) // Necesitamos que se genere automaticamente, por ello agregamos un JwtService
            .build();
    }
// No olvidar implementar UserDetails en nuestra User Entity
}
