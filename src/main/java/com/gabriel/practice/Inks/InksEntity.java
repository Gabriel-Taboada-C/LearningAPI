package com.gabriel.practice.Inks;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.gabriel.practice.Colors.ColorsEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table (name = "tintas")
public class InksEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "marca", nullable = false)
    private String mark;
    @Column(name = "codigo_de_tinta")
    private String code;
    @ManyToMany
    @JoinTable ( name = "mark_color",
        joinColumns = @JoinColumn (name = "mark_id"),
        inverseJoinColumns =  @JoinColumn (name = "color_id")
    )
    private List<ColorsEntity> colores = new ArrayList<>();
    @CreationTimestamp
    @Column(name = "fecha_de_ingreso")
    private LocalDateTime entryDate;

}
