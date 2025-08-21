package com.gabriel.practice.User;

import java.sql.Date;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
/* import lombok.NonNull;  
 * Es lo mismo que @Column (nullable = false)
*/

@Entity
/* 
@Entity 
Define cómo es la tabla en la base de datos.
JPA usa esta clase para mapear filas a objetos Java. 
*/
@Table (name="Usuarios")
public class UserEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @Column (name = "Nombre", nullable = false)
    private String name;
    @Column (name = "Apellido", nullable = false)
    private String surname;
    @Column (name = "Contraseña", nullable = false)
    private String password;
    @Column (name = "Fecha_de_nacimiento", nullable = false)
    private Date birthday;
    
    /* Constructor: dentro del entity es útil para los DTOs, no es obligatorio que esté 
     * Con @AllArgsConstructor nos evitamos tener que escribirlo
    */

    public UserEntity (String name, String surname, String password, Date birthdate) {
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.birthday = birthdate;
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
    
    public String getSurname () {
        return surname;
    }

    public void setSurname (String surname) {
        this.surname = surname;
    }

    public String getPassword () {
        return password;
    }

    public void setPassword (String password) {
        this.password = password;
    }

    public Date getBirthday () {
        return birthday;
    }

    public void setBirthday (Date birthday) {
        this.birthday = birthday;
    }
}
