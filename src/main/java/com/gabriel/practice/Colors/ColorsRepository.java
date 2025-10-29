package com.gabriel.practice.Colors;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ColorsRepository extends JpaRepository <ColorsEntity, Long>{
    List <ColorsEntity> findByColor (String color);

}
