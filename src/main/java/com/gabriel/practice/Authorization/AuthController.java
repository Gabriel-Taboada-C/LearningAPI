package com.gabriel.practice.Authorization;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping ("/auth")
@RequiredArgsConstructor
public class AuthController {


    // Atributo por el cual accedemos a los metodos Login y Response
    // Para obtener el token, primero debemos crearlos (AuthService)
    private final AuthService authService;

    @PostMapping(value = "login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {

        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping(value = "register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {

        return ResponseEntity.ok(authService.register(request));
    }
    
}

/* Una vez creado el JwtAuthenticationFilter,
crearemos 3 clases para las rutas del controller
 * LoginRequest
 * RegisterRequest
 * AuthResponse
 */

 /* Utilizamos el objeto ResponseEntity, que representa toda la respuesta HTTP
  * Va a incluir los codigos de estado, los Headers (encabezados)
  * y los Requestbody (cuerpos de respuesta).
  * Esta clase nos proporciona flexibilidad para configurar y personalizar
  la HTTPRequest (respuesta HTTP)
  */