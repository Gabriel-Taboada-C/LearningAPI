package com.gabriel.practice.Inks;

import java.sql.Date;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class InksEntity {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "Marca")
    private String mark;
    @Column(name = "Color")
    private String color;
    @Column(name = "Codigo_de_Tinta")
    private UUID code;
    @Column(name = "Fecha_de_Ingreso")
    private Date entryDate;

}
