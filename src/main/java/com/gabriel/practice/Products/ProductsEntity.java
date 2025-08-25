package com.gabriel.practice.Products;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    @Column (name = "id_Producto")
    private Long id;
    @Column (name = "Nombre_Producto", nullable = false)
    private String name;
    /*private String client; one to many */
    @Column (name = "Ancho_Producto")
    private String wide;
    @Column (name = "Largo_Producto")
    private String longitud;
    @Column (name = "Cantidad_de_Cilindros", nullable = false)
    private String cilinders;
    @Column (name = "Material_de_Impresión", nullable = false)
    private String impMaterial;
    @Column (name = "Material_de_Laminación")
    private String lamMaterial;
    @Column (name = "Descripción_del_Producto", nullable = false)
    private String description;

    @CreationTimestamp
    @Column (name = "Dia_de_Creación", updatable = false)
    private LocalDateTime entryDay;
}
