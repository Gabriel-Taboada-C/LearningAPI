package com.gabriel.practice.Clients;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ClientEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (name = "Cliente", nullable = false)
    private String name;
    @Column (name = "Contacto")
    private String contact;
    @Column (name = "Número_de_Teléfono")
    private Long number;
    @Column (name = "Correo_Electrónico")
    private String email;
    @Column (name = "Dirección")
    private String direction;
    @Column (name = "Productos") /* Relacion one to many a cantidad de productos que nos compra */
    private String product;

    /* Constructor */
    public ClientEntity (String name, String contact, Long number, String direction, String product) {
        this.name = name;
        this.contact = contact;
        this.number = number;
        this.direction = direction;
        this.product = product;
    }

    /* Constructor vacio para JPA */
    public ClientEntity () {}

    /* Getters y Setters */

    public Long getId () {
        return id;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getContact () {
        return contact;
    }

    public void setContact (String contact) {
        this.contact = contact;
    }

    public Long getNumber () {
        return number;
    }

    public void setNumber (Long number) {
        this.number = number;
    }

    public String getEmail () {
        return email;
    }

    public void setEmail (String email) {
        this.email = email;
    }

    public String getDirection () {
        return direction;
    }

    public void setDirection (String direction) {
        this.direction = direction;
    }

    public String getProduct () {
        return product;
    }

    public void setProduct (String product) {
        this.product = product;
    }


}
