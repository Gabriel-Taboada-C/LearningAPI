package com.gabriel.practice.Configuration;

import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.gabriel.practice.User.UserEntity;
import com.gabriel.practice.User.UserRepository;
import com.gabriel.practice.User.UserEntity.Rol;

//Esta clase se utiliza para crear usuarios con roles
//Apenas se inicializa el programa
@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDataBase (UserRepository userRepository, PasswordEncoder passwordEncoder) {
    return args -> {
        // --- USUARIO CON ROL ADMIN ---
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
        // --- USUARIO CON ROL USER_OPER ---
        if (userRepository.findByName("operario").isEmpty()) {
            UserEntity oper = new UserEntity();
            oper.setName("operario");
            oper.setPassword(passwordEncoder.encode("abcd"));
            oper.setRol(Rol.USER_OPER);
            oper.setCreatedDate(LocalDateTime.now());
            userRepository.save(oper);
            System.out.println("El usuario operario fue creado correctamente");
        }
        else {
            System.out.println("El usuario operario ya existe");
        }    
        // --- USUARIO CON ROL USER_ALMAC ---
        if (userRepository.findByName("almacenamiento").isEmpty()) {
            UserEntity almac = new UserEntity();
            almac.setName("almacenamiento");
            almac.setPassword(passwordEncoder.encode("5678"));
            almac.setRol(Rol.USER_ALMAC);
            almac.setCreatedDate(LocalDateTime.now());
            userRepository.save(almac);
            System.out.println("El usuario almacenamiento fue creado correctamente");
        }
        else {
            System.out.println("El usuario almacenamiento ya existe");
        }    
    };
  }  
}
