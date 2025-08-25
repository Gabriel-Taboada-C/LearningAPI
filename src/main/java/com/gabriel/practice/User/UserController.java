package com.gabriel.practice.User;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/{id}") // Esto seria un getUserById
    public UserEntity getUserById(@PathVariable UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("El usuario con el id: " + id + " no se encontró."));

    }

    @GetMapping // Esto seria un getUserAll
    public List<UserEntity> getUsers() {
        return userRepository.findAll();
        /*
         * Gracias a Spreen puedo buscar todos los usuarios del repositorio con
         * .findAll()
         */
    }

    @PostMapping()
    public UserEntity createUser(@RequestBody UserEntity user) {
        return userRepository.save(user);
        /* Guarda en la base de datos y devuelve el cliente */
    }

    @PutMapping("/{id}")
    public UserEntity updateUser(@PathVariable UUID id, @RequestBody UserEntity detallesUser) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("El usuario con el id: " + id + " no se encontró."));
        user.setName(detallesUser.getName());
        user.setPassword(detallesUser.getPassword());
        user.setCreatedDate(detallesUser.getCreatedDate());
        user.setRol(detallesUser.getRol());

        return userRepository.save(user);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable UUID id) {

        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("El usuario con el id: " + id + " no se encontró."));

        userRepository.delete(user);
        return "El usuario con el id: " + id + "se eliminó correctamente.";
    }

}
