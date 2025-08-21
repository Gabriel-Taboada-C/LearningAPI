package com.gabriel.practice.Orders;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository <OrdersEntity, Long> {

    Optional <OrdersEntity> findByNumber (Long number );

}
