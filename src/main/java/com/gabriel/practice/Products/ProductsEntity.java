package com.gabriel.practice.Products;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.gabriel.practice.Clients.ClientEntity;
import com.gabriel.practice.Orders.OrdersEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/* Si estás trabajando con JPA, no siempre es recomendable usar @Data directamente en una @Entity, porque:
 - Genera equals y hashCode, lo cual puede causar problemas si se basan en campos como el ID (UUID, Long, etc.)
que aún no fueron asignados por la base de datos.
 - Puede haber efectos no deseados si hay relaciones (@OneToMany, etc.) por el toString(). */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductsEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "producto_id")
    private Long id;
    @Column (name = "nombre_producto", nullable = false)
    private String name;
    /*Relacion OneToOne con orden: 1 producto pertenece a 1 solo pedido */
    @OneToOne (mappedBy = "product")
    private OrdersEntity order;
    /*Relacion ManyToOne con cliente: muchos productos pertenecen a 1 solo cliente */
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private ClientEntity client;
    @Column (name = "ancho_producto")
    private String wide;
    @Column (name = "largo_producto")
    private String longitud;
    @Column (name = "cantidad_de_cilindros", nullable = false)
    private Long cilinders;
    @Column (name = "material_de_impresión", nullable = false)
    private String impMaterial;
    @Column (name = "material_de_laminación")
    private String lamMaterial;
    @Column (name = "descripción_del_producto", nullable = false)
    private String description;

    @CreationTimestamp
    @Column (name = "dia_de_creación", updatable = false)
    private LocalDateTime entryDay;
}
