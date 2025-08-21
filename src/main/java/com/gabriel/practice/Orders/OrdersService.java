package com.gabriel.practice.Orders;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class OrdersService {

    private final OrdersRepository ordersRepository;

    public OrdersService (OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    public OrdersEntity getOrderById (Long id) {
        return ordersRepository.findById(id)
                    .orElseThrow(()->new RuntimeException("La orden con el id: " + id + " no se encontro"));
    }

    public List <OrdersEntity> getOrders () {
        return ordersRepository.findAll();
    }

    public OrdersEntity saveOrder (OrdersEntity order) {
        return ordersRepository.save(order);
    }

    public OrdersEntity updateOrder (Long id, OrdersEntity updatedOrder) {
        OrdersEntity existingOrder = ordersRepository.findById(id)
                    .orElseThrow(()->new RuntimeException("La orden con el id: " + id + " no se encontro"));
        existingOrder.setClient(updatedOrder.getClient());
        existingOrder.setProduct(updatedOrder.getProduct());
        existingOrder.setNumber(updatedOrder.getNumber());
        existingOrder.setAddress(updatedOrder.getAddress());
        existingOrder.setTransport(updatedOrder.getTransport());
        existingOrder.setEntryDate(updatedOrder.getEntryDate());
        existingOrder.setDeliveryDate(updatedOrder.getDeliveryDate());

        return ordersRepository.save(existingOrder);
    }

    public void deleteOrder (Long id) {
        OrdersEntity order = ordersRepository.findById(id)
                    .orElseThrow(()->new RuntimeException("La orden con el id: " + id + " no se encontro"));
        ordersRepository.delete(order);

    }

}
