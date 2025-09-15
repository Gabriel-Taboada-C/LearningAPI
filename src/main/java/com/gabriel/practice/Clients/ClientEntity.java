package com.gabriel.practice.Clients;

import java.util.List;

import com.gabriel.practice.Orders.OrdersEntity;
import com.gabriel.practice.Products.ProductsEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class ClientEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "cliente_id")
    private Long id;
    @Column (name = "cliente", nullable = false)
    private String name;
    @Column (name = "contacto")
    private String contact;
    @Column (name = "cúmero_de_teléfono")
    private Long number;
    @Column (name = "correo_electrónico")
    private String email;
    @Column (name = "dirección")
    private String direction;
    /* Relacion OneToMany a cantidad de productos: 1 cliente tiene muchos productos */
    @OneToMany (mappedBy = "client")
    private List<ProductsEntity> products;
    /* Relacion OneToMany a ordenes solicitadas */
    @OneToMany (mappedBy = "client")
    private List<OrdersEntity> orders;

    /* Constructor */
    public ClientEntity (String name, String contact, Long number, String direction) {
        this.name = name;
        this.contact = contact;
        this.number = number;
        this.direction = direction;
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

}
