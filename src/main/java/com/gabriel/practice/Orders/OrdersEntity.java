package com.gabriel.practice.Orders;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
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
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id_Orden") /* Agregar relacion ManyToMany con productos*/
    private Long id;
    @Column (name = "Cliente")  /* Agregar relacion OneToMany con ordenes*/
    private String client;
    @Column (name = "Producto") /* Agregar relacion ManyToMany con ordenes*/
    private String product;
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_seq")
    @SequenceGenerator (name = "order_seq", sequenceName = "producto_order_seq", allocationSize = 1)
    @Column (name = "Numero_de_Orden")
    private Long orderNumber;
    @Column (name = "Dirección")
    private String address;
    @Column(name = "Transporte")
    private String carry;
    @Column (name = "Fecha_de_Ingreso")
    private LocalDate entryDate;
    @Column (name = "Fecha_de_Entrega")
    private LocalDate deliveryDate;

    /* Bujes: tamaño y cantidad
     * Materiales de impresion y laminacion
     * Pedido: Kilos y metros
     * 
     */

}
