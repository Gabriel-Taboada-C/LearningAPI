package com.gabriel.practice.Colors;

import java.util.ArrayList;
import java.util.List;

import com.gabriel.practice.Cilinders.CilindersEntity;
import com.gabriel.practice.Inks.InksEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table (name = "colores", uniqueConstraints = @UniqueConstraint(columnNames = "color"))
public class ColorsEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (nullable = false, unique = true)
    private String color;
    @Column (nullable = false)
    private String hexCode;
    @OneToMany  (mappedBy = "color")
    private List<CilindersEntity> cilindros = new ArrayList<>();
    @ManyToMany (mappedBy = "colores")
    private List<InksEntity> mark = new ArrayList<>();

    /* Si ambos atributos en las otras entidades se llaman “color”, entonces tendremos ambos mappedBy = "color" 
    Esto no es un error sintáctico, pero sí puede ser confuso y poco claro semánticamente.
    Para evitar esto utilizo el atributo color para la entidad de Cilindros y el atributo colores para la entidad
    de Tintas (Inks). De todas formas, JPA lo interpreta bien si cada relación apunta a una entidad distinta (CilindersEntity vs InksEntity),
    pero desde el punto de vista de mantenimiento, puede inducir errores más adelante. */
}
