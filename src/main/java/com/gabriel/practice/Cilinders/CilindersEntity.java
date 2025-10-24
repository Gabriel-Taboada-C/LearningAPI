package com.gabriel.practice.Cilinders;

import com.gabriel.practice.Colors.ColorsEntity;
import com.gabriel.practice.Products.ProductsEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table (name = "cilindros")
public class CilindersEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String codigo;
    
    @Column
    private boolean cilindroComun;
    @Column
    private Double totalMetros = 0.0;
    @Column
    private Double maxMetros = 1000000.0;
    @ManyToOne
    @JoinColumn (name = "color_id")
    private ColorsEntity color;


    // Metodo simple para agregar los metros utilizados en el cilindro
    public void agregarMetros(Double metros) {
        if (metros != null && metros > 0 ) {
            this.totalMetros += metros;
        }
    }

    // Metodo simple para saber si el cilindro esta para recromar
    public boolean estaDesgastado () {
        return this.totalMetros > this.maxMetros;
    }

    /* Estos metodos pueden estar dentro de la entidad y no ir en un servicio aparte
    ya que los metodos modifican atributos propios, no depende de otros componentes */

}
