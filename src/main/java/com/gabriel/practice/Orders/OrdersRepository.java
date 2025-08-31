package com.gabriel.practice.Orders;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrdersRepository extends JpaRepository <OrdersEntity, Long> {

    /* Este metodo no funciona asi:
     * Optional <OrdersEntity> findByNumber (Long orderNumber ); 
    */

    /* Opcion 2 permite mantener el nombre del método que se quiera independientemente del atributo. */
    
    // JPQL
    @Query("SELECT i FROM OrdersEntity i WHERE i.orderNumber = :orderNumber")
    Optional<OrdersEntity> findByName(@Param("orderNumber") String orderNumber);

    // O bien usando SQL nativo
    /* @Query(value = "SELECT * FROM orders WHERE orderNumber = :orderNumber", nativeQuery = true)
    Optional<OrdersEntity> findByNameNative(@Param("orderNumber") String orderNumber); */

}
