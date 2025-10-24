package com.gabriel.practice.Cilinders;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.gabriel.practice.Products.ProductsEntity;


public interface CilindersRepository extends JpaRepository <CilindersEntity, Long> {
    List<CilindersEntity> findByName(ProductsEntity name);
}
