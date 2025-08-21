package com.gabriel.practice.Products;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository <ProductsEntity,Long>{

    Optional <ProductsEntity> findByName(String name);

}
