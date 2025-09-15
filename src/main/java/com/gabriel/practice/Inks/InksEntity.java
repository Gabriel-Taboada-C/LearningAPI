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
    @Column(name = "marca", nullable = false)
    private String mark;
    @Column(name = "color", nullable = false)
    private String color;
    @Column(name = "codigo_de_tinta")
    private String code;
    @CreationTimestamp
    @Column(name = "fecha_de_ingreso")
    private LocalDateTime entryDate;

}
