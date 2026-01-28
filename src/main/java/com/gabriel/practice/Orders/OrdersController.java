package com.gabriel.practice.Orders;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<OrdersEntity>> getOrders() {
        return ResponseEntity.ok(ordersService.getOrders());
    }
    

    @GetMapping("/{id}")
    public ResponseEntity<OrdersEntity> getOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(ordersService.getOrderById(id));
    }

    @PostMapping()
    public ResponseEntity<OrdersEntity> createOrder(@RequestBody OrdersEntity order) {
        
        OrdersEntity saved = ordersService.saveOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(ordersService.saveOrder(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrdersEntity> updateOrder (@PathVariable Long id, @RequestBody OrdersEntity order) {
        
        return ResponseEntity.ok(ordersService.updateOrder(id, order));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder (@PathVariable Long id) {
        ordersService.deleteOrder(id);

        return ResponseEntity.noContent().build(); // Retorna 204 No Content
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
