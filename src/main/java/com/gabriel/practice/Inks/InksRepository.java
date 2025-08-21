package com.gabriel.practice.Inks;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InksRepository extends JpaRepository <InksEntity,Long>{
    Optional <InksEntity> findByName(String mark);
}
