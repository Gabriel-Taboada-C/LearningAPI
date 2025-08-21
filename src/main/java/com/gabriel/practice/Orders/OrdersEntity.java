package com.gabriel.practice.Orders;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table (name = "Ordenes_de_Clientes")
public class OrdersEntity {

    @Id
    @GeneratedValue
    private Long id;
    @Column (name = "Cliente")
    private String client;
    @Column (name = "Producto")
    private String product;
    @Column (name = "Numero_de_Orden")
    private Long number;
    @Column (name = "Direccion")
    private String address;
    @Column(name = "Transporte")
    private String transport;
    @Column (name = "Fecha_de_Ingreso")
    private Date entryDate;
    @Column (name = "Fecha_de_Entrega")
    private Date deliveryDate;

    /* Bujes: tamaño y cantidad
     * Materiales de impresion y laminacion
     * Pedido: Kilos y metros
     * 
     */

}
