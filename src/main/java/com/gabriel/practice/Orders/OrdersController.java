package com.gabriel.practice.Orders;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping ("/orders")
public class OrdersController {

    private final OrdersService ordersService;

    public OrdersController (OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @GetMapping
    public List<OrdersEntity> getOrders() {
        return ordersService.getOrders();
    }
    

    @GetMapping("/{id}")
    public OrdersEntity getOrderById(@PathVariable Long id) {
        return ordersService.getOrderById(id);
    }

    @PostMapping("/{id}")
    public OrdersEntity createOrder(@RequestBody OrdersEntity order) {
        
        return ordersService.saveOrder(order);
    }

    @PutMapping("/{id}")
    public OrdersEntity updateOrder (@PathVariable Long id, @RequestBody OrdersEntity order) {
        
        return ordersService.updateOrder(id, order);
    }
    
    @DeleteMapping
    public String deleteOrder (@PathVariable Long id) {
        ordersService.deleteOrder(id);

        return "El producto con el id: " + id + " se elimino correctamente";
    }

   /*  
   
   ESTO ERA EL CONTROLADOR CON LOGICA (SIN SERVICE)
   
   @Autowired
    private OrdersRepository ordersRepository;

    @GetMapping("/{id}")
    public OrdersEntity getOrderById(@PathVariable Long id) {
        return ordersRepository.findById(id)
                    .orElseThrow(()-> new RuntimeException("No se encontro la orden con el id: " + id));
    }

    @GetMapping()
    public List<OrdersEntity> getOrders() {
        return ordersRepository.findAll();
    }
    
    @PostMapping("/{id}")
    public OrdersEntity createOrder(@RequestBody OrdersEntity order) {    
        return ordersRepository.save(order);
    }
    
    @PutMapping("/{id}")
    public OrdersEntity updateOrder(@PathVariable Long id, @RequestBody OrdersEntity detallesOrden) {
        OrdersEntity order = ordersRepository.findById(id)
                            .orElseThrow(()->new RuntimeException("No se encontro la orden con el id: " + id));
        order.setClient(detallesOrden.getClient());
        order.setProduct(detallesOrden.getProduct());
        order.setNumber(detallesOrden.getNumber());
        order.setAddress(detallesOrden.getAddress());
        order.setTransport(detallesOrden.getTransport());
        order.setEntryDate(detallesOrden.getEntryDate());
        order.setDeliveryDate(detallesOrden.getDeliveryDate());

        return ordersRepository.save(order);
    }

    @DeleteMapping ("/{id}")
    public String deleteOrder(@PathVariable Long id) {
        OrdersEntity order = ordersRepository.findById(id)
                    .orElseThrow(()->new RuntimeException("No se encontro la orden con el id: " + id));
        
        ordersRepository.delete(order);

        return "La orden con el id: " + id + "se elimino correctamente";
    } */
}
