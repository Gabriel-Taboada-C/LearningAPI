package com.gabriel.practice.Configuration;

import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.gabriel.practice.User.UserEntity;
import com.gabriel.practice.User.UserRepository;
import com.gabriel.practice.User.UserEntity.Rol;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDataBase (UserRepository userRepository, PasswordEncoder passwordEncoder) {
    return args -> {
        if (userRepository.findByName("admin").isEmpty()) {
            UserEntity admin = new UserEntity();
            admin.setName("admin");
            admin.setPassword(passwordEncoder.encode("1234"));
            admin.setRol(Rol.ADMIN);
            admin.setCreatedDate(LocalDateTime.now());
            userRepository.save(admin);
            System.out.println("El usuario admin fue creado correctamente");
        }
        else {
            System.out.println("El usuario admin ya existe");
        }    
    };
  }  
}
