package com.gabriel.practice.Products;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
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
    @GeneratedValue
    private Long id;
    @Column (name = "Nombre_Producto")
    private String name;
    /*private String client; one to many */
    @Column (name = "Ancho")
    private String wide;
    @Column (name = "Largo")
    private String longitud;
    @Column (name = "Colores")
    private String colors;
    @Column (name = "Materia_Prima")
    private String materials;
    @Column (name = "Descripcion_del_Producto")
    private String description;
    @Column (name = "Dia_de_Ingreso")
    private Date entryDay;
}
