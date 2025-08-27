package com.gabriel.practice.Authorization;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping ("/auth")
@RequiredArgsConstructor
public class AuthController {

    @PostMapping(value = "login")
    public String login() {

        return "Login correcto";
    }
    @PostMapping(value = "register")
    public String register() {

        return "El usuario se registró correctamente";
    }
    
}
