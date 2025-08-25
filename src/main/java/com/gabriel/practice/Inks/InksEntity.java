package com.gabriel.practice.Inks;

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

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class InksEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Marca", nullable = false)
    private String mark;
    @Column(name = "Color", nullable = false)
    private String color;
    @Column(name = "Codigo_de_Tinta")
    private String code;
    @CreationTimestamp
    @Column(name = "Fecha_de_Ingreso")
    private LocalDateTime entryDate;

}
