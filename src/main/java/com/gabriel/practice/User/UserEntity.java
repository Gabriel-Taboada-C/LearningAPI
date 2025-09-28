package com.gabriel.practice.User;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
/* import lombok.NonNull;  
 * Es lo mismo que @Column (nullable = false)
*/
import lombok.Builder;

@Entity
/* 
@Entity 
Define cómo es la tabla en la base de datos.
JPA usa esta clase para mapear filas a objetos Java. 
*/
@Builder
@Table (name="Usuarios")
public class UserEntity implements UserDetails{

/* UserDetails es una clase ya creada que sirve para la autenticacion */

    @Id
    @GeneratedValue 
    /* @GeneratedValue (strategy = GenerationType.IDENTITY)
     * CON H2 ESTO DA ERROR Y NO GENERA LOS id POR NO
     * SOPORTAR LOS DATOS DE TIPO UUID
    */
    private UUID id;

    @Column (name = "Nombre_de_Usuario", nullable = false)
    private String name;
    @Column (name = "Contraseña", nullable = false)
    private String password;
    @CreationTimestamp
    @Column (name = "Fecha_de_Creación")
    private LocalDateTime createdDate;
    @Enumerated (EnumType.STRING)
    @Column (name = "Rol", nullable = false)
    private Rol rol;

    public enum Rol {
        ADMIN,
        USER_ADMIN,
        USER_COMMON
    }

    /* Crear otra entidad empleado,s con estos datos y agregar dni, edad, fecha de ingreso, puesto, trabaja actualmente (true o false), categoria.  */
    /* Constructor: dentro del entity es útil para los DTOs, no es obligatorio que esté 
     * Con @AllArgsConstructor nos evitamos tener que escribirlo
    */

    public UserEntity (UUID id, String name, String password, LocalDateTime createdDate, Rol rol) {
        this.id = id; //Agregado por cambio de version io.jsonwebtoken a 0.12.3 para poder usar .getId
        this.name = name;
        this.password = password;
        this.createdDate =createdDate;
        this.rol = rol;
    }

    /* Constructor vacío: es OBLIGATORIO para que JPA pueda generar nuevos objetos
     * Con @NoArgsConstructor nos evitamos tener que escribirlo
     */

     public UserEntity () {}

    /* Getters y Setters
     * Getters sirven para traer informacion, por eso el return variable
     * Setters sirven para generar informacion, son como constructores, por eso "this.variable"
     * Con @Getter y @Setter nos evitamos escribirlos
     * Con @Data reemplazamos @Getter, @Setter y @AllArgsConstructor (ademas de @toString)
     */

    public UUID getId () {
        return id;
    }

    public void setId (UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }
    

    public String getPassword () {
        return password;
    }

    public void setPassword (String password) {
        this.password = password;
    }

    public LocalDateTime getCreatedDate () {
        return createdDate;
    }

    public void setCreatedDate (LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

     public Rol getRol () {
        return rol;
    }

    public void setRol (Rol rol) {
        this.rol =rol;
    }

    /* ASI SE AUTOGENERA AL IMPLEMENTAR LOS METODOS DEL USERDETAILS 
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAuthorities'");
    } */

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(rol.name()));
    }

    @Override
    public String getUsername() {
        return name;
    }
}
