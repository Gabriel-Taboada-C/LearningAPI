package com.gabriel.practice.Inks;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InksRepository extends JpaRepository <InksEntity,Long>{
    /* Este metodo este no funciona asi ya que JPA busca una variable llamada name
     * Optional <InksEntity> findByName(String mark);
     * Opcion 1 se adapta al nombre del campo real (busca usando el campo "mark"): 
    */ 
    List <InksEntity> findByMark (String mark);

    /* Se utiliza List <T> ya que se van a obtener muchos valores */
}
