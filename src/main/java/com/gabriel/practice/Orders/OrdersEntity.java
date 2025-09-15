package com.gabriel.practice.Orders;

import java.time.LocalDate;

import com.gabriel.practice.Clients.ClientEntity;
import com.gabriel.practice.Products.ProductsEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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
    @Column (name = "orden_id")
    private Long id;
    /* Relacion OneToMany con clientes: muchas ordenes pertenecen a 1 solo cliente*/
    @ManyToOne
    @JoinColumn (name = "cliente_id")
    private ClientEntity client;
    /* Relacion OneToOne con productos: 1 orden pertenece a 1 solo producto*/
    @OneToOne
    @JoinColumn (name = "producto_id")
    private ProductsEntity product;
    @Column (name = "numero_de_orden")
    private Long orderNumber;
    @Column (name = "dirección")
    private String address;
    @Column(name = "transporte")
    private String carry;
    @Column (name = "fecha_de_ingreso")
    private LocalDate entryDate;
    @Column (name = "fecha_de_entrega")
    private LocalDate deliveryDate;

    /* Bujes: tamaño y cantidad
     * Materiales de impresion y laminacion
     * Pedido: Kilos y metros
     * 
     */

}
