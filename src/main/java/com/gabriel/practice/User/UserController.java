package com.gabriel.practice.User;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabriel.practice.Exception.ErrorCode;
import com.gabriel.practice.Exception.ErrorResponse;
import com.gabriel.practice.Exception.MessageService;
import com.gabriel.practice.Exception.UserNotFoundException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Users", description = "Operaciones sobre usuarios")
@RestController
@RequestMapping("/users")
public class UserController {

    /*
     * @Controller
     * Recibe la petición y la interpreta.
     */

    /*
     * INYECCIÓN DE DEPENDENCIAS: Inyección por campo
     *
     * Es la más corta, pero también la menos recomendable porque:
     * 
     * No es tan fácil de testear.
     * 
     * No podés hacer la clase final.
     * 
     * Hace difícil ver qué necesita realmente tu clase.
     * 
     * A CONTINUACIÓN MOSTRAMOS LA PEOR MANERA DE INYECTAR DEPENDENCIAS
     * SE RECOMINEDA EVITAR ESTA FORMA:
     * 
     * Controlador sin @Service
     * En este caso, el controlador se comunica directamente con el repositorio:
     * Características:
     * 
     * Menos capas → más simple para proyectos muy pequeños.
     * 
     * El controlador contiene lógica de negocio y de acceso a datos, lo que mezcla
     * responsabilidades.
     * 
     * Difícil de probar unitariamente, porque para testear el controlador hay que
     * tocar la base de datos o simular el repositorio.
     * 
     * Si la lógica crece, el código se vuelve desordenado.
     * 
     * @Autowired
     * private UserRepository userRepository;
     */

    @Autowired
    private UserRepository userRepository;
    private MessageService messageService;

    @GetMapping("/{id}") // Esto seria un getUserById
    public UserEntity getUserById(@PathVariable Long id) {
        return userRepository.findById(id)
                .orElseThrow(
                        () -> new UserNotFoundException("user.notfound",
                                ErrorCode.USER_NOT_FOUND,
                                id));

    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping // Esto seria un getUserAll
    public List<UserEntity> getUsers() {
        return userRepository.findAll();
        /*
         * Gracias a Spreen puedo buscar todos los usuarios del repositorio con
         * .findAll()
         */
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @Operation(summary = "Crear usuario", description = "Registra un nuevo usuario en el sistema", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos para registrar el usuario", required = true, content = @Content(mediaType = "application/json", examples = {
            @ExampleObject(name = "Ejemplo válido", value = """
                    {
                      "username": "gabriel",
                      "email": "gabriel@example.com",
                      "password": "123456"
                    }
                    """)
    })), responses = {
            @ApiResponse(responseCode = "201", description = "Usuario creado correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserEntity.class))),
            @ApiResponse(responseCode = "400", description = "Error de validación", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "409", description = "El email ya existe", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<UserEntity> createUser(@Valid @RequestBody UserEntity user) {
        UserEntity created = userRepository.save(user);

        // URI del recurso creado
        URI location = URI.create("/users/" + created.getId());
        // La cabecera Location es obligatoria en un 201, porque indica la URL del
        // recurso recién creado.

        return ResponseEntity
                .created(location) // 201 created + Header Location
                .body(created); // Tambien devolvemos el usuario creado
        /* Guarda en la base de datos y devuelve el cliente */
    }

    @PutMapping("/{id}")
    public UserEntity updateUser(@PathVariable Long id, @RequestBody UserEntity detallesUser) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("El usuario con el id: " + id + " no se encontró."));
        user.setName(detallesUser.getName());
        user.setPassword(detallesUser.getPassword());
        user.setCreatedDate(detallesUser.getCreatedDate());
        user.setRol(detallesUser.getRol());

        return userRepository.save(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {

        UserEntity user = userRepository.findById(id)
                .orElseThrow(
                        () -> new UserNotFoundException("user.notfound",
                                ErrorCode.USER_NOT_FOUND,
                                id));

        userRepository.delete(user);

        String msg = messageService.getMessage("user.deleted");

        return ResponseEntity.ok(msg);
    }

}
