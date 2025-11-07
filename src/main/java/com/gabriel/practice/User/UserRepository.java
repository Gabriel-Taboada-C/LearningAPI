package com.gabriel.practice.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


/* 
 * @Repository
 * Es la puerta de entrada a la base de datos.
 * Usa JpaRepository para generar consultas automáticamente.
 * Con extends le asignamos a UserRepository las propiedades y funciones que posee JPAReposiroty
 * JPAReposiroty es una interfaz que incluye métodos CRUD listos para usar: findAll(), save(), deleteById(), findById(), etc.
 */
public interface UserRepository extends JpaRepository <UserEntity,Long>{

    Optional<UserEntity> findByName(String name);
    Optional<UserEntity> findById(Long id);
    /*Optional es una forma segura de manejar valores que pueden estar vacíos o nulos. 
     * Devuelve UN solo valor, para varios valores se utiliza List
    */
}
